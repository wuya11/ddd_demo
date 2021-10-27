package com.ddd.demo.ddd.domain.compensate.service.handler.check;

import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateBillA;
import com.ddd.demo.ddd.infrastructure.compensate.enums.CheckTypeEnum;


/**
 * 售后补偿审批顶级接口
 * 补偿单审批存在自动/主动审批
 *
 * @author wl
 * @date 2021-9-1
 */
public interface CompensateCheckHandler {

    /**
     * 补偿单审批处理
     *
     * @param compensateBillA 售后补偿单聚合根
     */
    boolean canCheck(CompensateBillA compensateBillA);

    /**
     * 审批模式
     *
     * @return 返回审批类型
     */
    CheckTypeEnum checkType();
}
