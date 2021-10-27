package com.ddd.demo.ddd.infrastructure.compensate.enums;

import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateException;
import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateFailEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 补偿策略定义
 *
 * @author wl
 * @date 2019-12-16
 */
public enum CompensationStrategyEnum {

    /**
     * 补发
     */
    SUPPLY_AGAIN((byte) 1, "补偿补发"),
    /**
     * 商品-退款
     */
    GOODS_BACK((byte) 2, "商品-退款"),
    /**
     * 非商品-退款
     */
    NO_GOODS_BACK((byte) 3, "非商品-退款");

    CompensationStrategyEnum(Byte type, String text) {
        this.type = type;
        this.text = text;
    }

    /**
     * 基于数值获取文本
     *
     * @param value 数值
     * @return 文本
     */
    public static String getTextByValue(Byte value) {
        if (Objects.nonNull(value)) {
            for (CompensationStrategyEnum typeEnum : CompensationStrategyEnum.values()) {
                if (Objects.equals(typeEnum.getType(), value)) {
                    return typeEnum.getText();
                }
            }
        }
        return StringUtils.EMPTY;
    }

    public static CompensationStrategyEnum getSafeStrategyEnum(Byte code) {
        CompensationStrategyEnum compensationStrategyEnum = null;
        for (CompensationStrategyEnum typeEnum : CompensationStrategyEnum.values()) {
            if (Objects.equals(typeEnum.getType(), code)) {
                compensationStrategyEnum = typeEnum;
                break;
            }
        }
        if (Objects.isNull(compensationStrategyEnum)) {
            throw new CompensateException(CompensateFailEnum.STRATEGY_ENUM_ERROR);
        }
        return compensationStrategyEnum;
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
