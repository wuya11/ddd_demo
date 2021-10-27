package com.ddd.demo.ddd.infrastructure.compensate.enums;

import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateException;
import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateFailEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 补偿属性
 *
 * @author wl
 * @date 2019-12-16
 */
public enum ReplayAttributeEnum {

    /**
     * 商品
     */
    GOODS_ATTRIBUTE((byte) 1, "商品"),
    /**
     * 非商品
     */
    NO_GOODS_ATTRIBUTE((byte) 2, "非商品");

    ReplayAttributeEnum(Byte type, String text) {
        this.type = type;
        this.text = text;
    }

    /**
     * 基于数值获取文本
     *
     * @param value 数值
     * @return 文本
     */
    public static String getTextByValue(Integer value) {
        if (Objects.nonNull(value)) {
            for (ReplayAttributeEnum typeEnum : ReplayAttributeEnum.values()) {
                if (Objects.equals(typeEnum.getType(), value)) {
                    return typeEnum.getText();
                }
            }
        }
        return StringUtils.EMPTY;
    }

    /**
     * 获取安全的补偿商品属性
     *
     * @param value 数值
     * @return 文本
     */
    public static ReplayAttributeEnum getSafeReplayAttributeEnum(Byte value) {
        ReplayAttributeEnum replayAttributeEnum = null;
        if (Objects.nonNull(value)) {
            for (ReplayAttributeEnum typeEnum : ReplayAttributeEnum.values()) {
                if (Objects.equals(typeEnum.getType(), value)) {
                    replayAttributeEnum = typeEnum;
                    break;
                }
            }
        }
        if (Objects.isNull(replayAttributeEnum)) {
            throw new CompensateException(CompensateFailEnum.REPLAY_ATTRIBUTE_ENUM_NULL);
        }
        return replayAttributeEnum;
    }

    /**
     * 数值
     */
    private final Byte type;
    /**
     * 文本
     */
    private final String text;

    public Byte getType() {
        return type;
    }

    public String getText() {
        return text;
    }

}
