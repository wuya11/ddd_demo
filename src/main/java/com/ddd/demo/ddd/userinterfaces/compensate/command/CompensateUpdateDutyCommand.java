package com.ddd.demo.ddd.userinterfaces.compensate.command;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 责任方类型修改对象
 *
 * @author wl
 * @date 2021-9-2
 */
@Data
public class CompensateUpdateDutyCommand {

    /**
     * 补偿单号
     */
    @NotNull(message = "补偿单号不能为空")
    private Long coid;

    /**
     * 责任方类型
     */
    @NotNull(message = "责任方类型不能为空")
    private Byte dutyType;
}
