package com.ddd.demo.ddd.domain.compensate.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *  订单商品明细
 * @author wangling
 * @date 2019-12-13
 */
@Data
public class OrderGoodsV implements Serializable {
    /**
     * ogid
     */
    private Long orderGoodsId;
    /**
     * 订单编码
     */
    private Long subOid;
    /**
     * buyid
     */
    private String buyId;
    /**
     * 商品gid编码
     */
    private Long gid;
    /**
     * 商品sku编码
     */
    private Long skuId;
    /**
     * 商品gsp编码
     */
    private Long gspid;
    /**
     * 商品名称
     */
    private String subject;
    /**
     * 商品渠道编码
     */
    private Integer channelId;
    /**
     * 清仓商品批次号
     */
    private String lotNum;
    /**
     * 商品重量
     */
    private BigDecimal weight;
    /**
     * 商品卖价
     */
    private BigDecimal salePrice;
    /**
     * 商品分销价
     */
    private BigDecimal distributionPrice;
    /**
     * 商品购买价
     */
    private BigDecimal buyPrice;

    /**
     * 商品出库数量
     */
    private Integer deliveryNum;

    /**
     * 商品购买数量
     */
    private Integer buyNum;

    /**
     * 总优惠价
     */
    private BigDecimal subtotalDiscountMoney;
    /**
     * 总实付价
     */
    private BigDecimal subtotalPayMoney;

    /**
     * 购买模式
     */
    private String buyType;
}
