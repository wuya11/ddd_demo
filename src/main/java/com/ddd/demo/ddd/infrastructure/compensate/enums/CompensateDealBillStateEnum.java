package com.ddd.demo.ddd.infrastructure.compensate.enums;

import java.util.Objects;

/**
 * 履约单处理结果枚举
 *
 * @author wl
 * @date '2021-09-01
 */
public enum CompensateDealBillStateEnum {

    /**
     * 成功
     */
    SUCCESS((byte) 1, "成功"),
    /**
     * 失败
     */
    FAILED((byte) 2, "失败"),

    /**
     * 处理中
     */
    DOING((byte) 3, "处理中");

    /**
     * 编码
     */
    private final byte code;
    /**
     * 解释
     */
    private final String text;

    CompensateDealBillStateEnum(byte code, String text) {
        this.code = code;
        this.text = text;
    }

    public static CompensateDealBillStateEnum getSafeEnum(Byte code) {
        for (CompensateDealBillStateEnum typeEnum : CompensateDealBillStateEnum.values()) {
            if (Objects.equals(typeEnum.getCode(), code)) {
                return typeEnum;
            }
        }
        return null;
    }

    public byte getCode() {
        return code;
    }

    public String getText() {
        return text;
    }


}















