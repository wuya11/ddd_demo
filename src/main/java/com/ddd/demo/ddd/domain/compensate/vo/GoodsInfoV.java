package com.ddd.demo.ddd.domain.compensate.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 补发补偿-商品值对象
 *
 * @author wangling
 * @date 2021-8-31
 */
@Data
public class GoodsInfoV {

    /**
     * 核算主体编码
     */
    private Integer writeOffId;

    /**
     * sku编码
     */
    private Long skuId;
    /**
     * gid编码
     */
    private Long goodsGid;
    /**
     * gspid编码
     */
    private Long goodsGspid;
    /**
     * 1:普通商品；2：清仓商品
     */
    private Integer goodsType;
    /**
     * 商品标题
     */
    private String goodsTitle;

    /**
     * 商品卖价
     */
    private BigDecimal salePrice;
    /**
     * 商品买价
     */
    private BigDecimal buyPrice;

    /**
     * 渠道编码
     */
    private Integer channelId;
    /**
     * 可售库存数量-中转需要
     */
    private Integer saleStock;
}
