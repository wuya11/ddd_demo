package com.ddd.demo.ddd.domain.compensate.service.handler.check;

import com.ddd.demo.ddd.infrastructure.compensate.acl.CompensateSelectFacade;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 补偿单审批聚合根抽象类
 * 补偿单存在多种审批模式，抽象类主要是统一处理一些业务或功能相同的点
 *
 * @author wl
 * @date 2021-9-1
 */
public abstract class AbstractCompensateCheck implements CompensateCheckHandler {

    @Autowired
    protected CompensateSelectFacade billFacade;

    /**
     * 判断是否为特殊店铺
     *
     * @param shopId 店铺编码
     * @return 判断结果
     */
    protected boolean specialCompanyTag(int shopId) {
        return billFacade.specialCompanyTag(shopId);
    }
}

