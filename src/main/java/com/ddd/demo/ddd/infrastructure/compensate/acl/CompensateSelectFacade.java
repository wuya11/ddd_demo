package com.ddd.demo.ddd.infrastructure.compensate.acl;

import com.ddd.demo.ddd.domain.compensate.vo.OrderV;
import com.ddd.demo.feign.user.dto.UserResponse;

/**
 * 补偿单查询防腐层-接口层
 * 编写补偿单与外部系统的关系层-胶水代码，避免外部系统变化影响核心聚合根
 *
 * @author wl
 * @date 2021-8-26
 */
public interface CompensateSelectFacade {


    /**
     * 获取订单明细信息
     *
     * @param subOid 订单号
     * @return 订单明细信息
     */
    OrderV getOrderResponse(long subOid);

    /**
     * 获取人员信息
     *
     * @param acId 人员编码
     * @return 人员信息
     */
    UserResponse getUserResponse(long acId);


    /**
     * 查询店铺是否为xxx公司特殊店铺 true 是
     *
     * @param shopId 店铺编码
     * @return 验证结果
     */
    boolean specialCompanyTag(int shopId);

    /**
     * 获取售后主管acid
     * @return 售后主管acid
     */
    Long getAfterSaleManagerAcId();

    /**
     * 权限acid组
     * @return 权限acid组
     */
    String[] listCompensateAuthorityAcid();

}




















