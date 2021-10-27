package com.ddd.demo.ddd.userinterfaces.compensate.command;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 售后补偿申请对象
 *
 * @author wl
 * @date 2021-8-26
 */
@Data
public class CompensateApplyCommand implements Serializable {

    /**
     * 售后补偿基础信息
     */
    @NotNull
    @Valid
    private CompensateBillCommand compensateBillCommand;
    /**
     * 售后补偿策略对象
     */
    @NotNull
    @Valid
    private CompensateStrategyCommand compensateStrategyCommand;

}
