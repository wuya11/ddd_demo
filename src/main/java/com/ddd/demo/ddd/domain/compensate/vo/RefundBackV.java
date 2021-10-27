package com.ddd.demo.ddd.domain.compensate.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 补偿履约反馈退款值对象
 *
 * @author wl
 * @date 2021-09-08
 */
@Data
public class RefundBackV implements Serializable {

    /**
     * 退款批次号
     */
    private String batchNo;

    /**
     * 来源编码
     */
    private Long sourceId;

    /**
     * 退款状态
     */
    private boolean state;

    /**
     * 退款备注信息
     */
    private String msg;

}
