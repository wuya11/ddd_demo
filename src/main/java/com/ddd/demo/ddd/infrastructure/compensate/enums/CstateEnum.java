package com.ddd.demo.ddd.infrastructure.compensate.enums;

import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateException;
import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateFailEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 补偿单状态
 *
 * @author wl
 * @date 2019-12-16
 */
public enum CstateEnum {
    /**
     * 待审核
     */
    APPROVAL_PENDING(99, "待审核"),
    /**
     * 补偿中
     */
    COMPENSATE_DOING(1, "补偿中"),
    /**
     * 待沟通
     */
    COMPENSATE_PAUSE(3, "待沟通"),
    /**
     * 终止
     */
    COMPENSATE_CANCEL(100, "终止"),
    /**
     * 待补偿-审批通过
     */
    COMPENSATE_PENDING(4, "待补偿"),
    /**
     * 完成
     */
    COMPENSATE_END(2, "完成");

    CstateEnum(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    public static CstateEnum getCstateEnumByCode(Integer code) {
        CstateEnum cstateEnum = null;
        if (Objects.nonNull(code)) {
            for (CstateEnum temp : CstateEnum.values()) {
                if (temp.getValue().equals(code)) {
                    cstateEnum = temp;
                    break;
                }
            }
        }
        return cstateEnum;
    }

    public static CstateEnum getSafeCstateEnum(Integer code) {
        CstateEnum cstateEnum = getCstateEnumByCode(code);
        if (Objects.isNull(cstateEnum)) {
            throw new CompensateException(CompensateFailEnum.CSTATE_ERROR);
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
            for (CstateEnum typeEnum : CstateEnum.values()) {
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
