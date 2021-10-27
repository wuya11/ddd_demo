package com.ddd.demo.ddd.infrastructure.compensate.enums;

import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateException;
import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateFailEnum;

import java.util.Objects;

/**
 * 补偿枚举类
 *
 * @author sjc
 */
public enum RepaywayEnum {

    /**
     * 红包
     */
    REE_ENVELOPES((byte) 1, "红包"),

    /**
     * 代金券
     */
    CASH_COUPON((byte) 2, "代金券"),

    /**
     * 可提现余额
     */
    LEFT_BALANCE((byte) 6, "余额"),

    /**
     * 退款
     */
    REFUND((byte) 3, "退款"),

    /**
     * 补发
     */
    SUPPLY_AGAIN((byte) 5, "补发"),

    /**
     * 不可退余额
     */
    NON_REFUNDABLE_BALANCE((byte) 7, "不可退余额");

    /**
     * 编码
     */
    private final byte type;
    /**
     * 解释
     */
    private final String text;

    RepaywayEnum(byte type, String text) {
        this.type = type;
        this.text = text;
    }

    public byte getType() {
        return type;
    }

    public String getText() {
        return text;
    }




    public static RepaywayEnum getCheckEnum(Byte type) {
        if (Objects.isNull(type)) {
            return null;
        }
       RepaywayEnum repaywayEnum = null;
        for (RepaywayEnum temp : RepaywayEnum.values()) {
            if (temp.getType() == type) {
                repaywayEnum = temp;
                break;
            }
        }
        return repaywayEnum;
    }

    public static RepaywayEnum getSafeRepaywayEnum(Byte type) {
        RepaywayEnum repaywayEnum = getCheckEnum(type);
        if (Objects.isNull(repaywayEnum)) {
            throw new CompensateException(CompensateFailEnum.REPAY_WAY_ENUM_NULL);
        }
        return repaywayEnum;
    }

}

















