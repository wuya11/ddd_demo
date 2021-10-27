package com.ddd.demo.ddd.userinterfaces.compensate.command;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 补偿单基础对象
 *
 * @author wl
 * @date 2021-9-2
 */
@Data
public class CompensateBaseBillCommand {

    /**
     * 补偿单号
     */
    @NotNull(message = "补偿单号不能为空")
    private Long coid;

    /**
     * 操作人
     */
    @NotNull(message = "操作人编码不能为空")
    private Long operator;
}
