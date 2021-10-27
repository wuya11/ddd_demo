package com.ddd.demo.ddd.userinterfaces.compensate.query;

import lombok.Data;

import java.io.Serializable;

/**
 * 售后补偿申综合查询对象
 *
 * @author wl
 * @date 2021-10-22
 */
@Data
public class CompensateBillQuery implements Serializable {

    /**
     * 原因编码
     */
    private String reasonid;
    /**
     * 订单编号
     */
    private Long subOid;

    /**
     * 补偿单号
     */
    private Long coid;

    /**
     * 补偿单状态
     */
    private Byte cstate;
    /**
     * 责任归属
     */
    private Byte dutytype;
    /**
     * 补偿方式
     */
    private Byte repayway;
    /**
     * 补偿属性 1商品 0非商品
     */
    private Byte repayAttribute;
    /**
     * 操作人编码
     */
    private Long actuid;
}
