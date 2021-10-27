package com.ddd.demo.ddd.userinterfaces.compensate.event;

import lombok.Data;

/**
 * 补偿履约-下级退款反馈对象
 *
 * @author wl
 * @date 2021-09-26
 */
@Data
public class RefundFeedbackEvent {

    /**
     * 退款单号
     */
    private String refundNo;

    /**
     * 来源编码-就是履约单唯一编码
     */
    private Long sourceId;

    /**
     * true 成功， false 失败
     */
    private boolean state;

    /**
     * 退款备注
     */
    private String msg;
}
