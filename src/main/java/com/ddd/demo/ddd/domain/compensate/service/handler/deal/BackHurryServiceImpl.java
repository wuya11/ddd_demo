package com.ddd.demo.ddd.domain.compensate.service.handler.deal;

import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateDealBillA;
import com.ddd.demo.ddd.infrastructure.compensate.enums.CompensateDealBillStateEnum;
import com.ddd.demo.ddd.infrastructure.compensate.enums.DealBillWayEnum;
import org.springframework.stereotype.Component;

/**
 * 马上退款-同步实现类,不存在 feedback
 *
 * @author wl
 * @date 2021-09-07
 */
@Component
public class BackHurryServiceImpl implements CompensateDealHandler {

    @Override
    public DealBillWayEnum dealBillWayEnum() {
        return DealBillWayEnum.BACK_HURRY;
    }

    @Override
    public CompensateDealBillA deal(CompensateDealBillA compensateDealBillA) {
        // 简单说明：履约单向下级单据发起履约处理，下级单据马上反馈处理的结果
        //  todo 模拟调用其他系统，发起履约单处理
        // 调用后，下级单处理成功,模拟操作如下
        compensateDealBillA.setCompensateDealBillStateEnum(CompensateDealBillStateEnum.SUCCESS);
        compensateDealBillA.setReturnId("backHurry_id");
        compensateDealBillA.setSendDealJson("ask hurry json");
        return compensateDealBillA;
    }
}






















