package com.ddd.demo.ddd.userinterfaces.compensate.event;

import lombok.Data;

/**
 * 补偿履约-下级订单反馈对象
 *
 * @author wl
 * @date 2021-09-26
 */
@Data
public class OrderFeedbackEvent {

    /**
     * 订单号
     */
    private Long subOid;

    /**
     * 来源编码-就是履约单唯一编码
     */
    private Long sourceId;

    /**
     * 订单标签
     */
    private Integer tag;
    /**
     * 备注信息
     */
    private String msg;
}
