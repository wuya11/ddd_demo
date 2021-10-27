package com.ddd.demo.ddd.domain.compensate.service.handler.save.strategy;

import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateBillA;
import com.ddd.demo.ddd.domain.compensate.entity.StrategyGoodsE;
import com.ddd.demo.ddd.domain.compensate.service.handler.save.AbstractCompensateSave;
import com.ddd.demo.ddd.domain.compensate.vo.OrderGoodsV;
import com.ddd.demo.ddd.infrastructure.compensate.enums.CompensationStrategyEnum;
import com.ddd.demo.ddd.infrastructure.compensate.enums.RepaywayEnum;
import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * 补偿单聚合根-商品原因补偿实现类
 *
 * @author wl
 * @date 2021-8-30
 */
@Component
public class StrategyGoodsServiceImpl extends AbstractCompensateSave {


    @Override
    public boolean canApply(CompensateBillA compensateBillA) {
        return Objects.equals(compensateBillA.getStrategyEnum(), CompensationStrategyEnum.GOODS_BACK);
    }

    @Override
    public boolean check(CompensateBillA compensateBillA) {
        super.checkCompensateConfig(compensateBillA.getRepaywayEnum(), compensateBillA.getOrderV().getShopId());
        this.checkGoodsStrategy(compensateBillA);
        return true;
    }


    private void checkGoodsStrategy(CompensateBillA compensateBillA) {
        List<StrategyGoodsE> strategyGoodsEoList = compensateBillA.getCompensateStrategyE().getStrategyGoodsList();
        // 商品ogid与订单明细合规性验证
        List<OrderGoodsV> goodsList = compensateBillA.getOrderV().getOrderGoodsList();
        strategyGoodsEoList.forEach(
                strategyGoodsEo -> {
                    // ogid是否存在
                    OrderGoodsV goodsSingle = goodsList.stream().filter(x -> (Objects.equals(x.getOrderGoodsId(), strategyGoodsEo.getOgid()))).findFirst().orElse(null);
                    if (Objects.isNull(goodsSingle)) {
                        throw new CompensateException("校验失败:退款商品在订单中不存在,ogid:" + strategyGoodsEo.getOgid());
                    }
                    // 校验退款商品数量
                    strategyGoodsEo.setGid(goodsSingle.getGid());
                    if (goodsSingle.getDeliveryNum() < strategyGoodsEo.getGoodsNum()) {
                        throw new CompensateException("校验失败:退款商品数量不应大于订单中的商品数量,gid:" + strategyGoodsEo.getGid());
                    }
                }
        );
        // 补偿方式为退款需验证可退金额
        if (Objects.equals(compensateBillA.getRepaywayEnum(), RepaywayEnum.REFUND)) {
            this.refundMoneyCheck(strategyGoodsEoList, compensateBillA.getSubOid());
        }
    }

    /**
     * 商品退款-可退金额验证
     *
     * @param strategyGoodsEoList 申请商品对象
     * @param subOid              订单号
     */
    private void refundMoneyCheck(List<StrategyGoodsE> strategyGoodsEoList, long subOid) {
        // 代码注释  大致逻辑为 基于订单号，获取当前订单明细中的可退金额
        // demo 就不编写这一块了
//        List<RefundAmountResponseDto> goodsAfterSaleRefundList = refundSearchService.getRefundAmount(refundAmountRequestDtoList);
//        if (CollectionUtils.isEmpty(goodsAfterSaleRefundList)) {
//            throw new CompensateException("校验失败:查询可退金额返回对象为空");
//        }
//        strategyGoodsEoList.forEach(
//                strategyGoodsEo -> {
//                    RefundAmountResponseDto refundAmountResponseDto = goodsAfterSaleRefundList
//                            .stream().filter(x -> Objects.equals(x.getOgid(), strategyGoodsEo.getOgid())).findFirst().orElse(null);
//                    if (Objects.isNull(refundAmountResponseDto)) {
//                        throw new CompensateException("校验失败:退款商品查询可退金额失败,gid:" + strategyGoodsEo.getGid());
//                    }
//                    if (strategyGoodsEo.getAmount().compareTo(refundAmountResponseDto.getCanRefundMoney()) > 0) {
//                        throw new CompensateException("校验失败:退款商品退款金额不可大于可退金额,gid:" + strategyGoodsEo.getGid() + ",可退金额：" + refundAmountResponseDto.getCanRefundMoney());
//                    }
//                }
//        );
    }

    @Override
    public CompensateBillA process(CompensateBillA compensateBillA) {
        List<StrategyGoodsE> strategyGoodsEoList = compensateBillA.getCompensateStrategyE().getStrategyGoodsList();
        BigDecimal referenceMoney = strategyGoodsEoList.stream().map(StrategyGoodsE::getReferencePrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal compensateMoney = strategyGoodsEoList.stream().map(StrategyGoodsE::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        compensateBillA.setReferenceMoney(referenceMoney);
        compensateBillA.setCompensateMoney(compensateMoney);
        return super.processCompensateBill(compensateBillA);
    }
}

