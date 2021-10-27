package com.ddd.demo.ddd.application.compensate.service.impl;

import com.ddd.demo.ddd.application.compensate.event.CompensateDealBillStateEvent;
import com.ddd.demo.ddd.application.compensate.event.CompensateDealBillStateModify;
import com.ddd.demo.ddd.application.compensate.service.CompensateEventService;
import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateBillA;
import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateDealBillA;
import com.ddd.demo.ddd.domain.compensate.factory.CompensateDealBillFactory;
import com.ddd.demo.ddd.domain.compensate.repository.CompensateBillRepository;
import com.ddd.demo.ddd.domain.compensate.repository.CompensateDealBillRepository;
import com.ddd.demo.ddd.domain.compensate.service.CompensateDealBillDomainService;
import com.ddd.demo.ddd.infrastructure.compensate.enums.CompensateDealBillStateEnum;
import com.ddd.demo.ddd.infrastructure.compensate.enums.CstateEnum;
import com.ddd.demo.ddd.userinterfaces.compensate.command.strategy.CompensateStateChangeCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.event.OrderFeedbackEvent;
import com.ddd.demo.ddd.userinterfaces.compensate.event.RefundFeedbackEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 补偿履约单业务应用层服务实现
 *
 * @author wl
 * @date 2021-10-14
 */
@Service
public class CompensateEventServiceImpl implements CompensateEventService {

    @Autowired
    private CompensateDealBillDomainService compensateDealBillDomainService;
    @Autowired
    private CompensateDealBillFactory compensateDealBillFactory;
    @Autowired
    private CompensateDealBillRepository compensateDealBillRepository;
    @Autowired
    private CompensateBillRepository compensateBillRepository;
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void orderFeedback(OrderFeedbackEvent orderFeedbackEvent) {
        // 创建实体
        CompensateDealBillA compensateDealBillA = compensateDealBillFactory.orderCompensateBillA(orderFeedbackEvent, compensateDealBillRepository);
        // 领域层纯内存处理
        CompensateDealBillA compensateDealBillResult = compensateDealBillA.feedback(compensateDealBillDomainService);
        // 数据保存
        compensateDealBillRepository.changeDealBill(compensateDealBillResult);
        // 发送消息
        this.sendDealBillEvent(compensateDealBillResult);
    }

    @Override
    public void refundFeedback(RefundFeedbackEvent refundFeedbackEvent) {
        CompensateDealBillA compensateDealBillA = compensateDealBillFactory.refundCompensateBillA(refundFeedbackEvent, compensateDealBillRepository);
        CompensateDealBillA compensateDealBillResult = compensateDealBillA.feedback(compensateDealBillDomainService);
        compensateDealBillRepository.changeDealBill(compensateDealBillResult);
        this.sendDealBillEvent(compensateDealBillResult);
    }

    @Override
    public void listenerDealBill(CompensateDealBillStateEvent billStateEvent) {
        CompensateDealBillStateModify compensateBillStateModify = (CompensateDealBillStateModify) billStateEvent.getSource();
        // 补偿履约单状态与补偿单状态做转换
        CstateEnum cstateEnum = CstateEnum.COMPENSATE_DOING;
        if (Objects.equals(CompensateDealBillStateEnum.FAILED, compensateBillStateModify.getCompensateDealBillStateEnum())) {
            cstateEnum = CstateEnum.COMPENSATE_PAUSE;
        }
        if (Objects.equals(CompensateDealBillStateEnum.SUCCESS, compensateBillStateModify.getCompensateDealBillStateEnum())) {
            cstateEnum = CstateEnum.COMPENSATE_END;
        }
        // 调用仓库获取历史数据
        CompensateBillA compensateBillA = compensateBillRepository.find(compensateBillStateModify.getCompensateBillId());
        CompensateStateChangeCommand compensateStateChangeCommand = new CompensateStateChangeCommand();
        compensateStateChangeCommand.setState(cstateEnum.getValue());
        compensateStateChangeCommand.setCoid(compensateBillStateModify.getCompensateBillId().getCoid());
        // 调用领域层判断是否可以修改状态
        compensateBillA.changeBillState(compensateStateChangeCommand);
        // 调用仓库保存数据状态
        compensateBillRepository.changeState(compensateStateChangeCommand);
    }

    private void sendDealBillEvent(CompensateDealBillA compensateDealBillA) {
        CompensateDealBillStateModify compensateDealBillStateModify = new CompensateDealBillStateModify();
        compensateDealBillStateModify.setCompensateBillId(compensateDealBillA.getCompensateBillId());
        compensateDealBillStateModify.setCompensateDealBillId(compensateDealBillA.getCompensateDealBillId());
        compensateDealBillStateModify.setCompensateDealBillStateEnum(compensateDealBillA.getCompensateDealBillStateEnum());
        applicationContext.publishEvent(new CompensateDealBillStateEvent(compensateDealBillStateModify));
    }
}
