package com.ddd.demo.ddd.domain.compensate.service.handler.deal;

import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateDealBillA;
import com.ddd.demo.ddd.infrastructure.compensate.enums.CompensateDealBillStateEnum;
import com.ddd.demo.ddd.infrastructure.compensate.enums.DealBillWayEnum;
import org.springframework.stereotype.Component;

/**
 * 退款实现类
 *
 * @author sjc 2021-09-07
 */
@Component
public class RefundMoneyServiceImpl implements CompensateDealHandler {

    @Override
    public DealBillWayEnum dealBillWayEnum() {
        return DealBillWayEnum.REFUND_MONEY;
    }

    @Override
    public CompensateDealBillA deal(CompensateDealBillA compensateDealBillA) {
        // 简单说明：履约单申请退款，构建时，也传入履约单唯一编码，做为退款 对象的 sourceId,后续退款成功后，又发送通知，通知信息中包括 sourceId
        // todo 模拟调用外部系统 发起退款
        // 具体退款流程本例不写
        // 调用后，设置当前状态为处理中
        compensateDealBillA.setCompensateDealBillStateEnum(CompensateDealBillStateEnum.DOING);
        compensateDealBillA.setSendDealJson("ask refund json");
        compensateDealBillA.setReturnId("refund_id");
        return compensateDealBillA;
    }

    @Override
    public CompensateDealBillA feedback(CompensateDealBillA compensateDealBillA) {
        // 模拟接受到退款反馈消息后，处理后续逻辑
        // 获取来源编码
        compensateDealBillA.setCompensateDealBillStateEnum(CompensateDealBillStateEnum.SUCCESS);
        // 设置下级单号
        compensateDealBillA.setReturnId(compensateDealBillA.getFeedbackStrategyV().getRefundBackV().getBatchNo());
        return compensateDealBillA;
    }
}






















