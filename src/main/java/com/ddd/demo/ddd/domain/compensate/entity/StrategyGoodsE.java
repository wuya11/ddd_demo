package com.ddd.demo.ddd.domain.compensate.entity;

import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateException;
import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateFailEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * 售后补偿-问题商品补偿
 *
 * @author wangling
 * @date 2021-8-26
 */
@Data
public class StrategyGoodsE implements Serializable {

    /**
     * 问题商品关系主键-唯一标识
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

    /**
     * 问题商品gid编码-中间加工生成
     */
    private Long gid;


    public void init(){
        if(Objects.isNull(this.amount)||this.amount.compareTo(BigDecimal.ZERO) <= 0){
         throw new CompensateException(CompensateFailEnum.AMOUNT_GREATER_THAN_ZERO);
        }
        if(Objects.isNull(this.ogid)){
            throw new CompensateException(CompensateFailEnum.OGID_NULL);
        }
        if(Objects.isNull(this.goodsNum)||this.goodsNum<1){
            throw new CompensateException(CompensateFailEnum.GOODSNUM_GREATER_THAN_ZERO);
        }
        if(Objects.isNull(this.referencePrice)||this.referencePrice.compareTo(BigDecimal.ZERO) < 0){
            throw new CompensateException(CompensateFailEnum.REFERENCEPRICE_GREATER_THAN_ZERO);
        }
    }
}
