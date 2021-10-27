package com.ddd.demo.feign.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @author wl
 */
@Data
public class UserResponse implements Serializable {

    /**
     * acId
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private long acId;
    /**
     * uid
     */
    private String uid;
    /**
     * 在职情况
     */
    private byte isActive;
    /**
     * 人员信息 1用户 2员工
     */
    private byte acType;
    /**
     * 电话
     */
    private String cell;
    /**
     * 身份证
     */
    private String idCardNum;
    /**
     * 员工名
     */
    private String empName;
    /**
     * 部门名
     */
    private String depName;
    /**
     * 实名认证状态
     */
    private byte realNameStatus;
    /**
     * 钉钉id
     */
    private String ddId;
    /**
     * 用户名
     */
    private String name;
    /**
     * 工号
     */
    private String jobNumber;


    /**
     * 部门信息
     */
    private Integer depId;
}
