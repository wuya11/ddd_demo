package com.ddd.demo.ddd.infrastructure.compensate.acl;

import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateBillA;
import com.ddd.demo.feign.order.dto.CreateOrderRequest;

/**
 * 补偿单业务交互防腐层-接口层
 * 编写补偿单与外部系统的关系层-胶水代码，避免外部系统变化影响核心聚合根
 *
 * @author wl
 * @date 2021-9-1
 */
public interface CompensateBusinessFacade {

    /**
     * 扣减用户积分信息
     *
     * @param reasonId 原因编码
     * @param cuid     购买人编码
     * @param coid     补偿单号
     */
    void deductUserPoint(String reasonId, long cuid, long coid);

    /**
     * 恢复用户积分信息
     *
     * @param reasonId 原因编码
     * @param cuid     购买人编码
     * @param coid     补偿单号
     */
    void recoveryUserPoint(String reasonId, long cuid, long coid);

    /**
     * 发送售后补偿单审批通知消息
     *
     * @param compensateBillA 单据信息
     */
    void sendCheckMessage(CompensateBillA compensateBillA);

    /**
     * 发送补偿单创建消息
     *
     * @param coid   补偿单号
     * @param shopId 店铺编码
     */
    void sendCompensateCreateMns(long coid, int shopId);

    /**
     * 创建订单
     *
     * @param createOrder 创建订单对象
     */
    void createOrder(CreateOrderRequest createOrder);
}
