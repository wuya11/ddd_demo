package com.ddd.demo.ddd.userinterfaces.compensate.command;

import com.ddd.demo.ddd.infrastructure.compensate.enums.CstateEnum;
import lombok.Data;

/**
 * 补偿单状态变更对象
 *
 * @author wl
 * @date 2021-9-2
 */
@Data
public class CompensateUpdateStateCommand extends CompensateBaseBillCommand {

    /**
     * 补偿单状态
     */
    private CstateEnum cstateEnum;
}
