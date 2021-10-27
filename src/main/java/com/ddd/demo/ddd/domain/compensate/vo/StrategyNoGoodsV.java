package com.ddd.demo.ddd.domain.compensate.vo;

import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateException;
import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateFailEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * 售后补偿-非商品策略退款
 *
 * @author wangling
 * @date 2021-8-13
 */

@Data
public class StrategyNoGoodsV implements Serializable {

    /**
     * 参考额度
     */
    private BigDecimal referencePrice;

    /**
     * 补偿金额
     */
    private BigDecimal amount;

    public void init() {
        if (Objects.isNull(this.referencePrice) || this.referencePrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new CompensateException(CompensateFailEnum.REFERENCEPRICE_GREATER_THAN_ZERO);
        }
        if (Objects.isNull(this.amount) || this.amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new CompensateException(CompensateFailEnum.AMOUNT_GREATER_THAN_ZERO);
        }
    }
}
