package com.ddd.demo.ddd.infrastructure.compensate.acl.impl;

import com.ddd.demo.ddd.domain.compensate.vo.OrderV;
import com.ddd.demo.ddd.infrastructure.compensate.acl.CompensateSelectFacade;
import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateException;
import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateFailEnum;
import com.ddd.demo.feign.order.OrderFeign;
import com.ddd.demo.feign.user.UserFeign;
import com.ddd.demo.feign.user.dto.UserResponse;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 补偿单聚合根防腐层-实现层
 *
 * @author wl
 * @date 2021-8-26
 */
@Service
public class CompensateSelectFacadeImpl implements CompensateSelectFacade {

    @Autowired
    private OrderFeign orderFeign;
    @Autowired
    private UserFeign userFeign;

    @Override
    public OrderV getOrderResponse(long subOid) {
        OrderV orderResponse = orderFeign.getOrderResponse(subOid);
        if (Objects.isNull(orderResponse)) {
            throw new CompensateException(CompensateFailEnum.ORDER_IS_NULL);
        }
        // todo 判断订单是否满足售后补偿的条件
        return orderResponse;
    }

    @Override
    public UserResponse getUserResponse(long acId) {
        UserResponse userResponse = userFeign.getUserResponse(acId);
        if (Objects.isNull(userResponse)) {
            throw new CompensateException(CompensateFailEnum.USER_IS_NULL);
        }
        return userResponse;
    }

    @Override
    public boolean specialCompanyTag(int shopId) {
        // 本身数据来源于阿波罗配置，demo 设置为固定值
        int[] specialShops = new int[]{22, 4, 7, 8, 3002, 20002};
        return ArrayUtils.contains(specialShops, shopId);
    }

    @Override
    public Long getAfterSaleManagerAcId() {
        // 本身数据来源于阿波罗配置，demo 设置为固定值
        return 434232342424L;
    }

    @Override
    public String[] listCompensateAuthorityAcid() {
        // 本身数据来源于阿波罗配置，demo 设置为固定值
        return new String[]{"2131313133213", "33333", "1"};
    }
}




















