package com.ddd.demo.ddd.domain.compensate.service.handler.save.strategy;

import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateBillA;
import com.ddd.demo.ddd.domain.compensate.entity.StrategySupplyAgainE;
import com.ddd.demo.ddd.domain.compensate.service.handler.save.AbstractCompensateSave;
import com.ddd.demo.ddd.infrastructure.compensate.enums.CompensationStrategyEnum;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * 补偿单聚合根-补偿补发
 *
 * @author wl
 * @date 2021-8-31
 */
@Component
public class StrategySupplyAgainServiceImpl extends AbstractCompensateSave {

    @Override
    public boolean canApply(CompensateBillA compensateBillA) {
        return Objects.equals(compensateBillA.getStrategyEnum(), CompensationStrategyEnum.SUPPLY_AGAIN);
    }

    @Override
    public boolean check(CompensateBillA compensateBillA) {
        return super.checkCompensateConfig(compensateBillA.getRepaywayEnum(), compensateBillA.getOrderV().getShopId());
    }

    @Override
    public CompensateBillA process(CompensateBillA compensateBillA) {
        // 获取商品库存相关信息
        List<StrategySupplyAgainE> strategySupplyAgainEoList = setStockNumGoods(compensateBillA.getCompensateStrategyE().getStrategySupplyAgainList());
        compensateBillA.getCompensateStrategyE().setStrategySupplyAgainList(strategySupplyAgainEoList);
       // BigDecimal compensateMoney = strategySupplyAgainEoList.stream().map(x -> x.getGoodsInfoV().getSalePrice().multiply(BigDecimal.valueOf(x.getGoodsNum()))).reduce(BigDecimal.ZERO, BigDecimal::add);
        // 测试虚拟设置值
        BigDecimal referenceMoney = BigDecimal.ONE;
        BigDecimal compensateMoney=BigDecimal.TEN;
        compensateBillA.setReferenceMoney(referenceMoney);
        compensateBillA.setCompensateMoney(compensateMoney);
        return super.processCompensateBill(compensateBillA);
    }


    /**
     * 设置商品的库存
     */
    private List<StrategySupplyAgainE> setStockNumGoods(List<StrategySupplyAgainE> strategySupplyAgainList) {
//        List<StockSkuInfo> stockSkuInfoList = billFacade.listStockSkuInfo(goodsInfoList, wid);
//        strategySupplyAgainList.forEach(goodsInfo -> {
//            stockSkuInfoList.stream().filter(x -> Objects.equals(x.getSkuId(), goodsInfo.getSkuId())
//                    && Objects.equals(x.getChannelId(), goodsInfo.getLastChannelId()))
//                    .findAny().ifPresent(stockSkuInfo -> goodsInfo.setSaleStock(stockSkuInfo.getSaleableNum()));
//        });
//        return strategySupplyAgainList;

        // 0.基于传入的补发商品gid,调用商品中心获取商品基础信息，包括价格信息
        // 1.模拟调用远程服务获取到库存
        // 2.库存获取到了 判断需要补发的商品数量于库存的大小关系
        // 3.设置补发的金额
        return strategySupplyAgainList;
    }

}
