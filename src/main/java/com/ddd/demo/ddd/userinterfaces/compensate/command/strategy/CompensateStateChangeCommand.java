package com.ddd.demo.ddd.userinterfaces.compensate.command.strategy;

import com.ddd.demo.ddd.userinterfaces.compensate.command.CompensateBaseBillCommand;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 补偿单状态变更对象
 *
 * @author wl
 * @date 2021-9-2
 */
@Data
public class CompensateStateChangeCommand extends CompensateBaseBillCommand {

    /**
     * 补偿单状态变更
     */
    @NotNull(message = "补偿单状态不能为空")
    private Integer state;
}
