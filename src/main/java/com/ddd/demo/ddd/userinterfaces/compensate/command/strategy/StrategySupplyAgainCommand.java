package com.ddd.demo.ddd.userinterfaces.compensate.command.strategy;

import lombok.Data;

import java.io.Serializable;

/**
 * 售后补偿-补发
 *
 * @author wangling
 * @date 2021-8-26
 */
@Data
public class StrategySupplyAgainCommand implements Serializable {

    /**
     * 重新补偿-商品gid
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
     * 关联问题商品ogid-商品问题是才填写
     */
    private Long problemOgid;

    /**
     * 关联问题商品ogid数量-商品问题是才填写
     */
    private Integer problemGidNum;
}
