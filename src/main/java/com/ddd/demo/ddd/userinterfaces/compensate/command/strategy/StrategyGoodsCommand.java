package com.ddd.demo.ddd.userinterfaces.compensate.command.strategy;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 售后补偿-问题商品补偿
 *
 * @author wangling
 * @date 2021-8-26
 */
@Data
public class StrategyGoodsCommand implements Serializable {

    /**
     * 问题商品关系主键
     */
    private Long ogid;

    /**
     * 补偿金额
     */
    private BigDecimal amount;

    /**
     * 商品数量
     */
    private Integer goodsNum;

    /**
     * 理论补偿金额-参考额度
     */
    private BigDecimal referencePrice;
}
