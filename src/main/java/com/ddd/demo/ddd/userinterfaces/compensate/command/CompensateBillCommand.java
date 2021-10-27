package com.ddd.demo.ddd.userinterfaces.compensate.command;

import lombok.Data;

import java.io.Serializable;

/**
 * 售后补偿申请基础对象
 *
 * @author wl
 * @date 2021-8-26
 */
@Data
public class CompensateBillCommand implements Serializable {

    /**
     * 原因编码
     */
    private String reasonid;
    /**
     * '订单编号'
     */
    private Long subOid;

    /**
     * 申请描述
     */
    private String description;

    /**
     * 附件地址链接
     */
    private String imgids;
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
