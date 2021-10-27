package com.ddd.demo.ddd.infrastructure.compensate.enums;

/**
 * 履约单处理模式
 *
 * @author wl
 * @date '2021-09-01
 */
public enum DealBillWayEnum {

    /**
     * 补发订单
     */
    ORDER_REPEAT((byte) 1, "补发订单"),
    /**
     * 退款
     */
    REFUND_MONEY((byte) 2, "退款"),

    /**
     * 马上处理
     */
    BACK_HURRY((byte) 3, "马上处理");

    /**
     * 编码
     */
    private final byte code;
    /**
     * 解释
     */
    private final String text;

    DealBillWayEnum(byte code, String text) {
        this.code = code;
        this.text = text;
    }

    public byte getCode() {
        return code;
    }

    public String getText() {
        return text;
    }


}















