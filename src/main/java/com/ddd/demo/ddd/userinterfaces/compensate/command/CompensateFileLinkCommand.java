package com.ddd.demo.ddd.userinterfaces.compensate.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 上传文件的链接地址
 *
 * @author wl
 * @date 2021-9-2
 */
@Data
public class CompensateFileLinkCommand {

    /**
     * 补偿单号
     */
    @NotNull(message = "补偿单号不能为空")
    private Long coid;

    /**
     * 文件地址
     */
    @NotBlank(message = "文件地址不能为空")
    private String fileLink;

}
