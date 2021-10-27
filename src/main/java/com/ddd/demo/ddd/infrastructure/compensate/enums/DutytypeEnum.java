package com.ddd.demo.ddd.infrastructure.compensate.enums;

import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateException;
import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateFailEnum;

import java.util.Objects;

/**
 * 责任归属枚举
 *
 * @author sjc
 */
public enum DutytypeEnum {

    /**
     * 公司原因
     */
    COMPANY_REASON((byte) 1, "公司原因"),

    /**
     * 物流原因
     */
    DELIVER_REASON((byte) 2, "物流原因"),

    /**
     * 供应商原因
     */
    SIPPLIER_REASON((byte) 3, "供应商原因");


    /**
     * 编码
     */
    private final byte type;
    /**
     * 解释
     */
    private final String text;

    DutytypeEnum(byte type, String text) {
        this.type = type;
        this.text = text;
    }

    public byte getType() {
        return type;
    }

    public String getText() {
        return text;
    }




    public static DutytypeEnum getCheckEnum(Byte type) {
        if (Objects.isNull(type)) {
            return null;
        }
        DutytypeEnum dutytypeEnum = null;
        for (DutytypeEnum temp : DutytypeEnum.values()) {
            if (temp.getType() == type) {
                dutytypeEnum = temp;
                break;
            }
        }
        return dutytypeEnum;
    }

    public static DutytypeEnum getSafeDutyType(Byte type) {
        DutytypeEnum dutytypeEnum = getCheckEnum(type);
        if (Objects.isNull(dutytypeEnum)) {
            throw new CompensateException(CompensateFailEnum.DUTY_ENUM_NULL);
        }
        return dutytypeEnum;
    }

}
