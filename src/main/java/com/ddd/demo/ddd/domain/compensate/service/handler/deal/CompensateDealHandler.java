package com.ddd.demo.ddd.domain.compensate.service.handler.deal;

import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateDealBillA;
import com.ddd.demo.ddd.infrastructure.compensate.enums.DealBillWayEnum;

/**
 * 补偿履约单聚合跟基础顶级接口
 *
 * @author wl
 * @date 2021-09-02
 */
public interface CompensateDealHandler {

    /**
     * 处理模式
     *
     * @return 返回处理模式
     */
    DealBillWayEnum dealBillWayEnum();

    /**
     * 补偿履约
     *
     * @param compensateDealBillA 补偿履约聚合根
     * @return 返回的状态枚举
     */
    CompensateDealBillA deal(CompensateDealBillA compensateDealBillA);

    /**
     * 下级单反馈处理
     *
     * @param compensateDealBillA 补偿履约聚合根
     * @return 返回的状态枚举
     */
    default CompensateDealBillA feedback(CompensateDealBillA compensateDealBillA) {
        return null;
    }


}
