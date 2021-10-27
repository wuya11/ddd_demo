package com.ddd.demo.ddd.domain.compensate.vo;

import lombok.Data;

/**
 * 补偿回执反馈处理值对象
 *
 * @author wl
 * @date 2021-09-08
 */
@Data
public class CompensateFeedbackStrategyV {

    /**
     * 订单反馈值对象
     */
    private OrderReturnV orderReturnV;

    /**
     * 退款反馈值对象
     */
    private RefundBackV refundBackV;
}















