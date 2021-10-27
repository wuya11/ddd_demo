package com.ddd.demo.ddd.infrastructure.compensate.enums;

/**
 * 商品类型
 *
 * @author wl
 * @date 2021-8-31
 */
public enum GoodsTypeEnum {

    /**
     * 正常商品
     */
    NORMAL_GOODS(1, "正常商品"),
    /**
     * 清仓商品
     */
    CLEAN_GOODS(2, "清仓商品");

    /**
     * 编码
     */
    private final Integer type;
    /**
     * 解释
     */
    private final String text;

    GoodsTypeEnum(Integer type, String text) {
        this.type = type;
        this.text = text;
    }

    public Integer getType() {
        return type;
    }

    public String getText() {
        return text;
    }
}


















