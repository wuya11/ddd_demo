package com.ddd.demo.ddd.userinterfaces.compensate.command;

import com.ddd.demo.ddd.userinterfaces.compensate.command.strategy.StrategyGoodsCommand;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 线下店铺申请补偿单请求dto
 *
 * @author wl
 * @date 2021-9-3
 */

@Data
public class CompensateOfflineCommand {

    /**
     * 售后补偿基础信息
     */
    @NotNull
    @Valid
    private CompensateBillCommand compensateBillCommand;

    /**
     * 商品属性-问题商品退款
     */
    @NotEmpty
    @Valid
    private List<StrategyGoodsCommand> strategyGoodsCommandList;
}