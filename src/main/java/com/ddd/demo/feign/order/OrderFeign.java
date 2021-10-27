package com.ddd.demo.feign.order;

import com.ddd.demo.ddd.domain.compensate.vo.OrderGoodsV;
import com.ddd.demo.ddd.domain.compensate.vo.OrderV;
import com.ddd.demo.feign.order.dto.CreateOrderRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单中心接口封装-模拟处理
 *
 * @author wl
 * @date 2019/12/17 13:39
 */
//@FeignClient(name = "order", url = "order/xxxx/xxxx/", fallbackFactory = OrderFeignFallbackFactory.class)
public interface OrderFeign {

    /**
     * 获取订单明细信息-模拟直接返回结果
     *
     * @param subOid 订单编码
     * @return 订单明细信息
     */
//    @RequestMapping(value = "/v1/order/info/{subOid}", method = RequestMethod.GET)
    default OrderV getOrderResponse(@PathVariable("subOid") long subOid) {
        OrderV orderV=new OrderV();
        orderV.setShopId(14);
        orderV.setAcId(123131312L);
        orderV.setSubOid(252840521259467465L);
        List<OrderGoodsV> orderGoodsVList=new ArrayList<>(2);
        OrderGoodsV orderGoodsV=new OrderGoodsV();
        orderGoodsV.setBuyNum(2);
        orderGoodsV.setBuyPrice(BigDecimal.TEN);
        orderGoodsV.setDeliveryNum(2);
        orderGoodsV.setSalePrice(BigDecimal.TEN);
        orderGoodsV.setOrderGoodsId(252840521267851982L);
        orderGoodsVList.add(orderGoodsV);
        OrderGoodsV orderGoodsV1=new OrderGoodsV();
        orderGoodsV1.setBuyNum(4);
        orderGoodsV1.setBuyPrice(BigDecimal.TEN);
        orderGoodsV1.setDeliveryNum(4);
        orderGoodsV1.setSalePrice(BigDecimal.TEN);
        orderGoodsV1.setOrderGoodsId(252840521267851983L);
        orderGoodsVList.add(orderGoodsV1);
        orderV.setOrderGoodsList(orderGoodsVList);
        return orderV;
    }

    /**
     * 调用订单系统-创建订单-模拟
     *
     * @return 订单明细信息
     */
//    @RequestMapping(value = "/v1/order/info/{subOid}", method = RequestMethod.GET)
    default void crateOrder(@RequestBody CreateOrderRequest createOrderRequest) {
    }

}









