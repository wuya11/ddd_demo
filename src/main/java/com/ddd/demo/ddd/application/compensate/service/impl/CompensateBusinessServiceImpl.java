package com.ddd.demo.ddd.application.compensate.service.impl;

import com.ddd.demo.ddd.application.compensate.event.CompensateDealBillStateEvent;
import com.ddd.demo.ddd.application.compensate.event.CompensateDealBillStateModify;
import com.ddd.demo.ddd.application.compensate.service.CompensateBusinessService;
import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateBillA;
import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateDealBillA;
import com.ddd.demo.ddd.domain.compensate.factory.CompensateBillFactory;
import com.ddd.demo.ddd.domain.compensate.factory.CompensateDealBillFactory;
import com.ddd.demo.ddd.domain.compensate.repository.CompensateBillRepository;
import com.ddd.demo.ddd.domain.compensate.repository.CompensateDealBillRepository;
import com.ddd.demo.ddd.domain.compensate.service.CompensateBillDomainService;
import com.ddd.demo.ddd.domain.compensate.service.CompensateDealBillDomainService;
import com.ddd.demo.ddd.domain.compensate.vo.CompensateBillId;
import com.ddd.demo.ddd.domain.compensate.vo.OrderV;
import com.ddd.demo.ddd.infrastructure.compensate.acl.CompensateBusinessFacade;
import com.ddd.demo.ddd.infrastructure.compensate.acl.CompensateSelectFacade;
import com.ddd.demo.ddd.infrastructure.compensate.assembler.CompensateAssembler;
import com.ddd.demo.ddd.infrastructure.compensate.enums.CheckTypeEnum;
import com.ddd.demo.ddd.userinterfaces.compensate.command.*;
import com.ddd.demo.ddd.userinterfaces.compensate.command.dealbill.DealBillCreateCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.dto.CompensateDto;
import com.ddd.demo.ddd.userinterfaces.compensate.query.CompensateBillQuery;
import com.ddd.demo.feign.user.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 补偿单业务应用层服务实现
 *
 * @author wl
 * @date 2021-8-30
 */
@Service
public class CompensateBusinessServiceImpl implements CompensateBusinessService {

    @Autowired
    private CompensateBillDomainService compensateBillDomainService;
    @Autowired
    private CompensateDealBillDomainService compensateDealBillDomainService;
    @Autowired
    private CompensateBillFactory compensateBillFactory;
    @Autowired
    private CompensateDealBillFactory compensateDealBillFactory;
    @Autowired
    private CompensateSelectFacade compensateSelectFacade;
    @Autowired
    private CompensateBillRepository compensateBillRepository;
    @Autowired
    private CompensateDealBillRepository compensateDealBillRepository;
    @Autowired
    private CompensateBusinessFacade compensateBusinessFacade;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private CompensateAssembler compensateAssembler;

    @Override
    public long save(CompensateApplyCommand compensateApplyCommand) {
        // 应用层体现 业务流程：
        // 获取订单信息-基于防腐层获取
        OrderV orderV = compensateSelectFacade.getOrderResponse(compensateApplyCommand.getCompensateBillCommand().getSubOid());
        // 获取人员信息-基于防腐层获取
        UserResponse userResponse = compensateSelectFacade.getUserResponse(compensateApplyCommand.getCompensateBillCommand().getActuid());
        // 基于工厂创建实体
        CompensateBillA compensateBillA = compensateBillFactory.createCompensateBillA(compensateApplyCommand, orderV, userResponse);
        // 调用领域层处理保存的业务逻辑
        CompensateBillA compensateBillAdd = compensateBillA.process(compensateBillDomainService);
        // 调用仓库保存数据
        compensateBillRepository.save(compensateBillAdd);
        // 保存完成后，消息通知其他系统
        sendCreateMessage(compensateBillAdd);
        // 保存完成后，主动发起审核
        long coid = compensateBillAdd.getCompensateBillId().getCoid();
        check(CheckTypeEnum.AUTO_CHECK, coid, compensateBillAdd.getActuid());
        return coid;
    }

    @Override
    public long saveOffline(CompensateOfflineCommand compensateOfflineCommand) {
        // 获取订单信息-基于防腐层获取
        OrderV orderV = compensateSelectFacade.getOrderResponse(compensateOfflineCommand.getCompensateBillCommand().getSubOid());
        // 基于工厂创建实体
        CompensateBillA compensateBillA = compensateBillFactory.createOfflineCompensateBillA(compensateOfflineCommand, orderV);
        // 调用领域层处理保存的前置逻辑
        CompensateBillA compensateBillAdd = compensateBillA.process(compensateBillDomainService);
        // 调用仓库保存数据
        compensateBillRepository.save(compensateBillAdd);
        // 保存完成后，消息通知其他系统
        sendCreateMessage(compensateBillAdd);
        // 保存完成后，主动发起审核
        long coid = compensateBillAdd.getCompensateBillId().getCoid();
        check(CheckTypeEnum.AUTO_CHECK, coid, compensateBillAdd.getActuid());
        return coid;
    }

    private void check(CheckTypeEnum checkTypeEnum, long coid, long acid) {
        CompensateCheckCommand checkCommand = new CompensateCheckCommand();
        checkCommand.setCoid(coid);
        checkCommand.setOperator(acid);
        // 获取历史补偿单信息
        CompensateBillId compensateBillId = new CompensateBillId();
        compensateBillId.setCoid(coid);
        CompensateBillA compensateBillA = compensateBillRepository.find(compensateBillId);
        boolean check = compensateBillA.check(compensateBillDomainService, checkTypeEnum, checkCommand);
        if (check) {
            // 调用仓库层保存数据
            compensateBillRepository.passCheck(checkCommand);
            // 调用补偿单履约模块
            DealBillCreateCommand dealBillCreateCommand = new DealBillCreateCommand();
            dealBillCreateCommand.setCoid(coid);
            dealBillCreateCommand.setActuid(acid);
            this.deal(dealBillCreateCommand);
        }
    }

    @Override
    public void deal(DealBillCreateCommand dealBillCreateCommand) {
        CompensateDealBillA compensateDealBillA = compensateDealBillFactory.createCompensateBillA(dealBillCreateCommand);
        CompensateDealBillA compensateDealResult = compensateDealBillA.deal(compensateDealBillDomainService);
        // 调用仓库层保存数据
        compensateDealBillRepository.save(compensateDealResult);
        // 补偿履约处理后，发起通知事件，通知其他模块履约单完成情况
        this.sendDealBillEvent(compensateDealResult);
    }

    private void sendDealBillEvent(CompensateDealBillA compensateDealBillA) {
        CompensateDealBillStateModify compensateDealBillStateModify = new CompensateDealBillStateModify();
        compensateDealBillStateModify.setCompensateBillId(compensateDealBillA.getCompensateBillId());
        compensateDealBillStateModify.setCompensateDealBillId(compensateDealBillA.getCompensateDealBillId());
        compensateDealBillStateModify.setCompensateDealBillStateEnum(compensateDealBillA.getCompensateDealBillStateEnum());
        applicationContext.publishEvent(new CompensateDealBillStateEvent(compensateDealBillStateModify));
    }

    private void sendCreateMessage(CompensateBillA compensateBillA) {
        int shopId = compensateBillA.getOrderV().getShopId();
        long coid = compensateBillA.getCompensateBillId().getCoid();
        //发送创建成功消息
        compensateBusinessFacade.sendCompensateCreateMns(coid, shopId);
        if (compensateSelectFacade.specialCompanyTag(shopId)) {
            // 特殊店铺发起扣减积分的处理
            compensateBusinessFacade.deductUserPoint(compensateBillA.getReasonid(),
                    compensateBillA.getOrderV().getAcId(), coid);
        }
    }

    @Override
    public void batchCheck(long[] coids) {
        for (long coid : coids) {
            check(CheckTypeEnum.MANUAL_CHECK, coid, 0);
        }
    }

    @Override
    public void batchCancel(long[] coids) {
        for (long coid : coids) {
            this.cancel(coid, 0);
        }
    }

    private void cancel(long coid, long acid) {
        // 获取历史补偿单信息
        CompensateBillId compensateBillId = new CompensateBillId();
        compensateBillId.setCoid(coid);
        CompensateBillA compensateBillA = compensateBillRepository.find(compensateBillId);
        boolean cancel = compensateBillA.canCancel();
        if (cancel) {
            CompensateCancelCommand compensateCancelCommand = new CompensateCancelCommand();
            compensateCancelCommand.setCoid(coid);
            compensateCancelCommand.setOperator(acid);
            compensateBillRepository.cancel(compensateCancelCommand);
            if (compensateSelectFacade.specialCompanyTag(compensateBillA.getOrderV().getShopId())) {
                // 特殊店铺发起恢复积分的处理
                compensateBusinessFacade.recoveryUserPoint(compensateBillA.getReasonid(),
                        compensateBillA.getOrderV().getAcId(), coid);
            }
        }
    }

    @Override
    public void updateDuty(CompensateUpdateDutyCommand compensateUpdateDutyCommand) {
        // 获取历史补偿单信息
        CompensateBillId compensateBillId = new CompensateBillId();
        compensateBillId.setCoid(compensateUpdateDutyCommand.getCoid());
        CompensateBillA compensateBillA = compensateBillRepository.find(compensateBillId);
        compensateBillA.canUpdateDuty(compensateUpdateDutyCommand);
        // 调用仓库层保存
        compensateBillRepository.updateBillDuty(compensateUpdateDutyCommand);
    }

    @Override
    public void saveFileLink(CompensateFileLinkCommand compensateFileLinkCommand) {
        // 获取历史补偿单信息
        CompensateBillId compensateBillId = new CompensateBillId();
        compensateBillId.setCoid(compensateFileLinkCommand.getCoid());
        CompensateBillA compensateBillA = compensateBillRepository.find(compensateBillId);
        compensateBillA.canUpdateFileLink(compensateFileLinkCommand);
        // 调用仓库层保存
        compensateBillRepository.updateBillFileLink(compensateFileLinkCommand);
    }

    @Override
    public List<CompensateDto> listCompensateDto(CompensateBillQuery query) {
        List<CompensateBillA> compensateBillList = compensateBillRepository.listCompensateBillA(query);
        if (CollectionUtils.isEmpty(compensateBillList)) {
            return Collections.emptyList();
        }
        return compensateBillList.stream().map(
                compensateBillA -> {
                    CompensateDto compensateDto = compensateAssembler.entityToCompensateDto(compensateBillA);
                    return compensateDto;
                }
        ).collect(Collectors.toList());

    }
}
