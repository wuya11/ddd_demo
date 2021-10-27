package com.ddd.demo.ddd.domain.compensate.entity;

import com.ddd.demo.ddd.domain.compensate.vo.CompensateBillId;
import com.ddd.demo.ddd.domain.compensate.vo.StrategyNoGoodsV;
import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateException;
import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateFailEnum;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * 售后补偿策略实体
 *
 * @author wl
 * @date 2021-8-26
 */
@Data
public class CompensateStrategyE implements Serializable {

    /**
     * 补偿编码
     */
    private CompensateBillId compensateBillId;
    /**
     * 补发策略对象
     */
    private List<StrategySupplyAgainE> strategySupplyAgainList;
    /**
     * 商品属性-问题商品退款
     */
    private List<StrategyGoodsE> strategyGoodsList;
    /**
     * 非商品属性-退款策略
     */
    private StrategyNoGoodsV strategyNoGoodsV;

    public static CompensateStrategyE createStrategyGoods(CompensateBillId coid, List<StrategyGoodsE> strategyGoodsEoList) {
        if (CollectionUtils.isEmpty(strategyGoodsEoList)) {
            throw new CompensateException(CompensateFailEnum.STRATEGY_GOODS_NULL);
        }
        // 问题商品不能出现重复的ogid
        long[] ogids = strategyGoodsEoList.stream().mapToLong(StrategyGoodsE::getOgid).distinct().toArray();
        if (ogids.length < strategyGoodsEoList.size()) {
            throw new CompensateException(CompensateFailEnum.PROBLEM_GID_EXIST);
        }
        strategyGoodsEoList.forEach(StrategyGoodsE::init);
        CompensateStrategyE compensateStrategyE = new CompensateStrategyE();
        compensateStrategyE.setCompensateBillId(coid);
        compensateStrategyE.setStrategyGoodsList(strategyGoodsEoList);
        return compensateStrategyE;
    }

    public static CompensateStrategyE createStrategySupplyAgain(CompensateBillId coid, List<StrategySupplyAgainE> strategySupplyAgainEoList) {
        if (CollectionUtils.isEmpty(strategySupplyAgainEoList)) {
            throw new CompensateException(CompensateFailEnum.STRATEGY_SUPPLY_AGAIN_NULL);
        }
        strategySupplyAgainEoList.forEach(StrategySupplyAgainE::init);
        // 问题商品不能出现重复的ogid
        long[] gids = strategySupplyAgainEoList.stream().mapToLong(StrategySupplyAgainE::getSubgid).distinct().toArray();
        if (gids.length < strategySupplyAgainEoList.size()) {
            throw new CompensateException(CompensateFailEnum.PROBLEM_GID_EXIST);
        }
        // 前端传入的wid 在list中应设置唯一
        long[] wids = strategySupplyAgainEoList.stream().mapToLong(StrategySupplyAgainE::getWid).distinct().toArray();
        if (wids.length > 1) {
            throw new CompensateException(CompensateFailEnum.WID_MUST_SINGLE);
        }
        CompensateStrategyE compensateStrategyE = new CompensateStrategyE();
        compensateStrategyE.setCompensateBillId(coid);
        compensateStrategyE.setStrategySupplyAgainList(strategySupplyAgainEoList);
        return compensateStrategyE;
    }

    public static CompensateStrategyE createStrategyNoGoods(CompensateBillId coid, StrategyNoGoodsV strategyNoGoodsVo) {
        if (Objects.isNull(strategyNoGoodsVo)) {
            throw new CompensateException(CompensateFailEnum.STRATEGY_NO_GOODS_NULL);
        }
        strategyNoGoodsVo.init();
        CompensateStrategyE compensateStrategyEo = new CompensateStrategyE();
        compensateStrategyEo.setCompensateBillId(coid);
        compensateStrategyEo.setStrategyNoGoodsV(strategyNoGoodsVo);
        return compensateStrategyEo;
    }
}
