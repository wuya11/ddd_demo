package com.ddd.demo.feign.order.dto;

import com.ddd.demo.ddd.domain.compensate.vo.OrderGoodsV;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 订单-值对象
 *
 * @author wangling
 * @date 2021-9-16
 */
@Data
public class CreateOrderRequest implements Serializable {
    /**
     * 店铺编号-系统中转需要
     */
    private Integer shopId;
    /**
     * 订单编码
     */
    private Long subOid;
    /**
     * 店铺名称
     */
    private String shopName;
    /**
     * acid用户编码
     */
    private Long acId;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 仓库编码
     */
    private Integer wid;
    /**
     * 仓库名称
     */
    private String warehouseName;
    /**
     * 商品明细
     */
    private List<OrderGoodsV> orderGoodsList;
}
