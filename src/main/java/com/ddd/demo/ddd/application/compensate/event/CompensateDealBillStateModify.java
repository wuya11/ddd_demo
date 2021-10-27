package com.ddd.demo.ddd.application.compensate.event;


import com.ddd.demo.ddd.domain.compensate.vo.CompensateBillId;
import com.ddd.demo.ddd.domain.compensate.vo.CompensateDealBillId;
import com.ddd.demo.ddd.infrastructure.compensate.enums.CompensateDealBillStateEnum;
import lombok.Data;

/**
 * 补偿履约单状态变更接口事件源
 *
 * @author wl
 * @date 2020-10-14
 */
@Data
public class CompensateDealBillStateModify {

    /**
     * 履约单号
     */
    private CompensateDealBillId compensateDealBillId;

    /**
     * 补偿聚合根-主键编码
     */
    private CompensateBillId compensateBillId;

    /**
     * 补偿履约单状态
     */
    private CompensateDealBillStateEnum compensateDealBillStateEnum;

}
