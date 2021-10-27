package com.ddd.demo.ddd.domain.compensate.factory;

import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateDealBillA;
import com.ddd.demo.ddd.domain.compensate.repository.CompensateDealBillRepository;
import com.ddd.demo.ddd.userinterfaces.compensate.command.dealbill.DealBillCreateCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.event.OrderFeedbackEvent;
import com.ddd.demo.ddd.userinterfaces.compensate.event.RefundFeedbackEvent;

/**
 * 补偿履约聚合根工厂 - 接口层
 *
 * @author wl
 * @date 2021-09-14
 */
public interface CompensateDealBillFactory {

    /**
     * 创建补偿履约单聚合根
     *
     * @param dealBillCreateCommand 履约单创建对象
     * @return 返回补偿履约单聚合根
     */
    CompensateDealBillA createCompensateBillA(DealBillCreateCommand dealBillCreateCommand);

    /**
     * 订单系统反馈后，创建履约单对象
     *
     * @param orderFeedbackEvent 订单反馈对象
     * @param compensateDealBillRepository 仓库入参
     * @return 返回补偿履约单聚合根
     */
    CompensateDealBillA orderCompensateBillA(OrderFeedbackEvent orderFeedbackEvent, CompensateDealBillRepository compensateDealBillRepository);

    /**
     * 退款系统反馈后，创建履约单对象
     *
     * @param refundFeedbackEvent 退款反馈对象
     * @param compensateDealBillRepository 仓库入参
     * @return 返回补偿履约单聚合根
     */
    CompensateDealBillA refundCompensateBillA(RefundFeedbackEvent refundFeedbackEvent, CompensateDealBillRepository compensateDealBillRepository);

}
