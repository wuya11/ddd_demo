package com.ddd.demo.ddd.domain.compensate.factory.impl;

import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateDealBillA;
import com.ddd.demo.ddd.domain.compensate.factory.CompensateDealBillFactory;
import com.ddd.demo.ddd.domain.compensate.repository.CompensateDealBillRepository;
import com.ddd.demo.ddd.domain.compensate.vo.*;
import com.ddd.demo.ddd.userinterfaces.compensate.command.dealbill.DealBillCreateCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.event.OrderFeedbackEvent;
import com.ddd.demo.ddd.userinterfaces.compensate.event.RefundFeedbackEvent;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 补偿履约聚合根工厂  - 实现层
 *
 * @author sjc 2021-09-14
 */
@Service
public class CompensateDealBillFactoryImpl implements CompensateDealBillFactory {

    @Override
    public CompensateDealBillA createCompensateBillA(DealBillCreateCommand dealBillCreateCommand) {
        CompensateDealBillA compensateDealBillA = new CompensateDealBillA();
        compensateDealBillA.setActAcid(dealBillCreateCommand.getActuid());
        CompensateBillId compensateBillId = new CompensateBillId();
        compensateBillId.setCoid(dealBillCreateCommand.getCoid());
        compensateDealBillA.setCompensateBillId(compensateBillId);
        compensateDealBillA.init();
        return compensateDealBillA;
    }

    @Override
    public CompensateDealBillA orderCompensateBillA(OrderFeedbackEvent orderFeedbackEvent, CompensateDealBillRepository compensateDealBillRepository) {
        Long sourceId = orderFeedbackEvent.getSourceId();
        // 基于来源编码获取履约单实体
        CompensateDealBillId compensateDealBillId = new CompensateDealBillId();
        compensateDealBillId.setDealId(sourceId.intValue());
        CompensateDealBillA compensateDealBillA = compensateDealBillRepository.find(compensateDealBillId);
        if (Objects.isNull(compensateDealBillA)) {
            return null;
        }
        OrderReturnV orderReturnV = new OrderReturnV();
        orderReturnV.setSourceId(sourceId);
        orderReturnV.setSubOid(orderFeedbackEvent.getSubOid());
        orderReturnV.setTag(orderFeedbackEvent.getTag());
        compensateDealBillA.setMsg(orderFeedbackEvent.getMsg());
        CompensateFeedbackStrategyV feedbackStrategyV = new CompensateFeedbackStrategyV();
        feedbackStrategyV.setOrderReturnV(orderReturnV);
        compensateDealBillA.setFeedbackStrategyV(feedbackStrategyV);
        return compensateDealBillA;
    }

    @Override
    public CompensateDealBillA refundCompensateBillA(RefundFeedbackEvent refundFeedbackEvent, CompensateDealBillRepository compensateDealBillRepository) {
        Long sourceId = refundFeedbackEvent.getSourceId();
        // 基于来源编码获取履约单实体
        CompensateDealBillId compensateDealBillId = new CompensateDealBillId();
        compensateDealBillId.setDealId(sourceId.intValue());
        CompensateDealBillA compensateDealBillA = compensateDealBillRepository.find(compensateDealBillId);
        if (Objects.isNull(compensateDealBillA)) {
            return null;
        }
        RefundBackV refundBackV = new RefundBackV();
        refundBackV.setSourceId(sourceId);
        refundBackV.setBatchNo(refundFeedbackEvent.getRefundNo());
        refundBackV.setMsg(refundFeedbackEvent.getMsg());
        refundBackV.setState(refundFeedbackEvent.isState());
        compensateDealBillA.setMsg(refundFeedbackEvent.getMsg());
        CompensateFeedbackStrategyV feedbackStrategyV = new CompensateFeedbackStrategyV();
        feedbackStrategyV.setRefundBackV(refundBackV);
        compensateDealBillA.setFeedbackStrategyV(feedbackStrategyV);
        return compensateDealBillA;
    }

}
