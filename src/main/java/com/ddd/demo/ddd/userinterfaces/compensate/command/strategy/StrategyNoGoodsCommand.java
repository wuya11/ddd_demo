package com.ddd.demo.ddd.userinterfaces.compensate.command.strategy;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *  售后补偿-非商品策略退款
 * @author wangling
 * @date 2021-8-13
 */

@Data
public class StrategyNoGoodsCommand implements Serializable {

    /**
     * 参考额度
     */
    private BigDecimal referencePrice;

    /**
     * 补偿金额
     */
    private BigDecimal amount;

}
