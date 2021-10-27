package com.ddd.demo.ddd.domain.compensate.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 补发订单反馈值对象
 *
 * @author wl
 * @date 2021-09-08
 */
@Data
public class OrderReturnV implements Serializable {

    /**
     * 中台订单号
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
     * 订单备注信息
     */
    private String msg;

}
