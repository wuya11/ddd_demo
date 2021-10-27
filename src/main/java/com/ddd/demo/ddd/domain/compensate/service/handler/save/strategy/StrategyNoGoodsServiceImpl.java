package com.ddd.demo.ddd.domain.compensate.service.handler.save.strategy;

import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateBillA;
import com.ddd.demo.ddd.domain.compensate.service.handler.save.AbstractCompensateSave;
import com.ddd.demo.ddd.infrastructure.compensate.enums.CompensationStrategyEnum;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 补偿单聚合根-非商品原因补偿实现类
 *
 * @author wl
 * @date 2021-8-30
 */
@Component
public class StrategyNoGoodsServiceImpl extends AbstractCompensateSave {

    @Override
    public boolean canApply(CompensateBillA compensateBillA) {
        return Objects.equals(compensateBillA.getStrategyEnum(), CompensationStrategyEnum.NO_GOODS_BACK);
    }

    @Override
    public boolean check(CompensateBillA compensateBillA) {
        return super.checkCompensateConfig(compensateBillA.getRepaywayEnum(), compensateBillA.getOrderV().getShopId());
    }

    @Override
    public CompensateBillA process(CompensateBillA compensateBillA) {
        compensateBillA.setReferenceMoney(compensateBillA.getCompensateStrategyE().getStrategyNoGoodsV().getReferencePrice());
        compensateBillA.setCompensateMoney(compensateBillA.getCompensateStrategyE().getStrategyNoGoodsV().getAmount());
        return super.processCompensateBill(compensateBillA);
    }
}
