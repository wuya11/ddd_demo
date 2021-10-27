package com.ddd.demo.ddd.domain.compensate.entity;

import com.ddd.demo.ddd.domain.compensate.vo.GoodsInfoV;
import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateException;
import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateFailEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * 售后补偿-补发
 *
 * @author wangling
 * @date 2021-8-26
 */
@Data
public class StrategySupplyAgainE implements Serializable {

    /**
     * 重新补偿-商品gid-唯一标识
     */
    private Long subgid;

    /**
     * 仓库编码
     */
    private Integer wid;

    /**
     * 重新补偿-商品数量
     */
    private Integer goodsNum;

    /**
     * 理论补偿金额
     */
    private BigDecimal referenceMoney;
    /**
     * 实际补偿金额
     */
    private BigDecimal amount;

    /**
     * 重新补偿-商品基础信息值对象
     */
    private GoodsInfoV goodsInfoV;

    /**
     * 商品补偿基础验证-非商品问题补发
     */
    public void init() {
        if (Objects.isNull(this.subgid)) {
            throw new CompensateException(CompensateFailEnum.OGID_NULL);
        }
        if (Objects.isNull(this.goodsNum) || this.goodsNum < 1) {
            throw new CompensateException(CompensateFailEnum.GOODSNUM_GREATER_THAN_ZERO);
        }
        if (Objects.isNull(this.wid) || this.wid < 1) {
            throw new CompensateException(CompensateFailEnum.WID_GREATER_THAN_ZERO);
        }
    }
}
