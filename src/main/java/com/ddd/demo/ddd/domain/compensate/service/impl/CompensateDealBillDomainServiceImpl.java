package com.ddd.demo.ddd.domain.compensate.service.impl;

import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateBillA;
import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateDealBillA;
import com.ddd.demo.ddd.domain.compensate.repository.CompensateBillRepository;
import com.ddd.demo.ddd.domain.compensate.service.CompensateDealBillDomainService;
import com.ddd.demo.ddd.domain.compensate.service.handler.deal.CompensateDealHandler;
import com.ddd.demo.ddd.infrastructure.compensate.enums.CstateEnum;
import com.ddd.demo.ddd.infrastructure.compensate.enums.DealBillWayEnum;
import com.ddd.demo.ddd.infrastructure.compensate.enums.RepaywayEnum;
import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateException;
import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateFailEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 补偿履约单聚合根相关行为的核心实现服务
 *
 * @author sjc 2021-09-01
 */
@Service
public class CompensateDealBillDomainServiceImpl implements CompensateDealBillDomainService {

    @Autowired
    private List<CompensateDealHandler> compensateDealHandlerList;
    @Autowired
    private CompensateBillRepository repository;

    @Override
    public CompensateDealBillA deal(CompensateDealBillA compensateDealBillA) {
        // 获取历史补偿单信息，查看当前是否满足处理条件
        CompensateBillA compensateBillA = repository.find(compensateDealBillA.getCompensateBillId());
        if (!canDeal(compensateBillA.getCstateEnum())) {
            throw new CompensateException(CompensateFailEnum.COMPENSATE_DEAL_NO_RIGHT);
        }
        // 本例默认处理为 马上下级单处理成功,以下逻辑为测试建议代码，实际流程是基于配置的，偏复杂
        DealBillWayEnum dealBillWayEnum = DealBillWayEnum.BACK_HURRY;
        if (Objects.equals(compensateBillA.getRepaywayEnum(), RepaywayEnum.REFUND)) {
            dealBillWayEnum = DealBillWayEnum.REFUND_MONEY;
        }
        if (Objects.equals(compensateBillA.getRepaywayEnum(), RepaywayEnum.SUPPLY_AGAIN)) {
            dealBillWayEnum = DealBillWayEnum.ORDER_REPEAT;
        }
        CompensateDealHandler compensateDealHandler = getCompensateDealHandler(dealBillWayEnum);
        return compensateDealHandler.deal(compensateDealBillA);
    }

    private boolean canDeal(CstateEnum cstateEnum) {
        // 待沟通或者待补偿 才可以处理
        return (Objects.equals(cstateEnum, CstateEnum.COMPENSATE_PENDING) ||
                Objects.equals(cstateEnum, CstateEnum.COMPENSATE_PAUSE));
    }

    @Override
    public CompensateDealBillA feedback(CompensateDealBillA compensateDealBillA) {
        // 获取历史补偿单信息，查看当前是否满足处理条件
        CompensateBillA compensateBillA = repository.find(compensateDealBillA.getCompensateBillId());
        if (!Objects.equals(compensateBillA.getCstateEnum(), CstateEnum.COMPENSATE_DOING)) {
            throw new CompensateException(CompensateFailEnum.COMPENSATE_DEAL_NO_RIGHT);
        }
        DealBillWayEnum dealBillWayEnum = DealBillWayEnum.BACK_HURRY;
        if (Objects.nonNull(compensateDealBillA.getFeedbackStrategyV().getOrderReturnV())) {
            dealBillWayEnum = DealBillWayEnum.ORDER_REPEAT;
        }
        if (Objects.nonNull(compensateDealBillA.getFeedbackStrategyV().getRefundBackV())) {
            dealBillWayEnum = DealBillWayEnum.REFUND_MONEY;
        }
        CompensateDealHandler compensateDealHandler = getCompensateDealHandler(dealBillWayEnum);
        return compensateDealHandler.feedback(compensateDealBillA);
    }

    private CompensateDealHandler getCompensateDealHandler(DealBillWayEnum dealBillWayEnum) {
        CompensateDealHandler result = null;
        for (CompensateDealHandler compensateDealHandler : compensateDealHandlerList) {
            if (Objects.equals(dealBillWayEnum, compensateDealHandler.dealBillWayEnum())) {
                result = compensateDealHandler;
                break;
            }
        }
        if (Objects.isNull(result)) {
            throw new CompensateException(CompensateFailEnum.COMPENSATE_DEAL_NULL);
        }
        return result;
    }

}
