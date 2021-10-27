package com.ddd.demo.ddd.infrastructure.compensate.database;

import com.ddd.demo.ddd.infrastructure.compensate.enums.CompensateDealBillStateEnum;
import lombok.Data;

/**
 * 履约单处理日志表
 * 只是模拟记录一张日志表，实际业务存在多张表记录
 *
 * @author wl
 * @date 2021-10-14
 */

@Data
public class CompensateDealLogDo {

    /**
     * 主键
     */
    private Integer dealId;

    /**
     * 补偿单号
     */
    private Long coid;

    /**
     * 补偿下级单编号
     */
    private String returnId;

    /**
     * 履约单处理状态
     *
     * @see CompensateDealBillStateEnum
     */
    private Byte dealState;

    /**
     * 添加人acid
     */
    private Long creator;

    /**
     * 日志信息
     */
    private String msg;

    /**
     * 向下级系统发起申请时,记录的请求数据
     */
    private String sendDealJson;

}















