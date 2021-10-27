package com.ddd.demo.ddd.domain.compensate.service;


import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateDealBillA;

/**
 * 补偿履约单聚合根领域服务
 *
 * @author wl
 * @date 2021-09-01
 */
public interface CompensateDealBillDomainService {

    /**
     * 补偿履约执行处理
     *
     * @param compensateDealBillA 补偿履约单聚合根
     * @return 补偿履约单聚合根
     */
    CompensateDealBillA deal(CompensateDealBillA compensateDealBillA);

    /**
     * 下级反馈处理
     *
     * @param compensateDealBillA 下级反馈值对象
     * @return 补偿履约单对象
     */
    CompensateDealBillA feedback(CompensateDealBillA compensateDealBillA);
}
