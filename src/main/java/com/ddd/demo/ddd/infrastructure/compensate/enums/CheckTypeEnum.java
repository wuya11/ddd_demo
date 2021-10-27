package com.ddd.demo.ddd.infrastructure.compensate.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 补偿单审批模式
 *
 * @author wl
 * @date 2021-9-6
 */
public enum CheckTypeEnum {

    /**
     * 自动审批
     */
    AUTO_CHECK(1, "自动审批"),
    /**
     * 手动审批
     */
    MANUAL_CHECK(2, "手动审批");

    CheckTypeEnum(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    public static CheckTypeEnum getCstateEnumByCode(Integer code) {
        CheckTypeEnum cstateEnum = null;
        if (Objects.nonNull(code)) {
            for (CheckTypeEnum temp : CheckTypeEnum.values()) {
                if (temp.getValue().equals(code)) {
                    cstateEnum = temp;
                    break;
                }
            }
        }
        return cstateEnum;
    }

    /**
     * 基于数值获取文本
     *
     * @param value 数值
     * @return 文本
     */
    public static String getTextByValue(Integer value) {
        if (Objects.nonNull(value)) {
            for (CheckTypeEnum typeEnum : CheckTypeEnum.values()) {
                if (Objects.equals(typeEnum.getValue(), value)) {
                    return typeEnum.getText();
                }
            }
        }
        return StringUtils.EMPTY;
    }

    /**
     * 数值
     */
    private final Integer value;
    /**
     * 文本
     */
    private final String text;

    public Integer getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

}
