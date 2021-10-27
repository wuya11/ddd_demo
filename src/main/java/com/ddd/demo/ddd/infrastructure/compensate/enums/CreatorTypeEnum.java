package com.ddd.demo.ddd.infrastructure.compensate.enums;

import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateException;
import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateFailEnum;

import java.util.Objects;

/**
 * 创建类型枚举类
 *
 * @author sjc
 */
public enum CreatorTypeEnum {

    /**
     * 没有值
     */
    NO_VALUE((byte) 0, ""),
    /**
     * 前台
     */
    FRONT_TYPE((byte) 1, "前台"),
    /**
     * 后台
     */
    BACKEND_TYPE((byte) 2, "后台"),
    ;

    /**
     * 编码
     */
    private final byte type;
    /**
     * 解释
     */
    private final String text;

    CreatorTypeEnum(byte type, String text) {
        this.type = type;
        this.text = text;
    }

    public byte getType() {
        return type;
    }

    public String getText() {
        return text;
    }


    public static CreatorTypeEnum getCheckEnum(Byte type) {
        if (Objects.isNull(type)) {
            return null;
        }
        CreatorTypeEnum creatorTypeEnum = null;
        for (CreatorTypeEnum temp : CreatorTypeEnum.values()) {
            if (temp.getType() == type) {
                creatorTypeEnum = temp;
                break;
            }
        }
        return creatorTypeEnum;
    }

    public static CreatorTypeEnum getSafeCreatorTypeEnum(Byte type) {
        CreatorTypeEnum checkEnum = getCheckEnum(type);
        if (Objects.isNull(checkEnum)) {
            throw new CompensateException(CompensateFailEnum.CREATE_TYPE_ENUM_NULL);
        }
        return checkEnum;
    }

}


















