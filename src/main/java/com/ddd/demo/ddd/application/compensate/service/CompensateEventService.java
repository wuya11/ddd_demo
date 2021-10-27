package com.ddd.demo.ddd.application.compensate.service;

import com.ddd.demo.ddd.application.compensate.event.CompensateDealBillStateEvent;
import com.ddd.demo.ddd.userinterfaces.compensate.event.OrderFeedbackEvent;
import com.ddd.demo.ddd.userinterfaces.compensate.event.RefundFeedbackEvent;

/**
 * 履约单接受消息应用层-接口层
 * 一般本系统基于消息模式，接收其他系统发送过来的消息
 *
 * @author wl
 * @date 2021-10-14
 */
public interface CompensateEventService {

    /**
     * 订单创建后回馈消息
     *
     * @param orderFeedbackEvent 订单回馈事件
     */
    void orderFeedback(OrderFeedbackEvent orderFeedbackEvent);

    /**
     * 退款单处理后回馈消息
     *
     * @param refundFeedbackEvent 订单回馈事件
     */
    void refundFeedback(RefundFeedbackEvent refundFeedbackEvent);

    /**
     * 监听下级履约单处理结果
     *
     * @param billStateEvent 履约单处理状态变化事件
     */
    void listenerDealBill(CompensateDealBillStateEvent billStateEvent);

}
