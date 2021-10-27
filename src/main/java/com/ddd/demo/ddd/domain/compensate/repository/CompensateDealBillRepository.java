package com.ddd.demo.ddd.domain.compensate.repository;

import com.ddd.demo.ddd.domain.base.BaseRepository;
import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateDealBillA;
import com.ddd.demo.ddd.domain.compensate.vo.CompensateDealBillId;

/**
 * 补偿履约单聚合根仓库 - 接口层
 *
 * @author sjc 2021-09-01
 */
public interface CompensateDealBillRepository extends BaseRepository<CompensateDealBillA, CompensateDealBillId> {

    /**
     * 下级系统反馈后，变更履约单信息
     *
     * @param compensateDealBillA 履约单实体
     */
    void changeDealBill(CompensateDealBillA compensateDealBillA);
}






















