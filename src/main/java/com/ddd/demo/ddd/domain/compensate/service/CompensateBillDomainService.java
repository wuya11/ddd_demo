package com.ddd.demo.ddd.domain.compensate.service;

import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateBillA;
import com.ddd.demo.ddd.infrastructure.compensate.enums.CheckTypeEnum;
import com.ddd.demo.ddd.userinterfaces.compensate.command.CompensateCheckCommand;


/**
 * 补偿单聚合根领域服务-接口层
 * 补偿单聚合根相关行为的核心实现服务
 *
 * @author wl
 * @date 2021-8-30
 */
public interface CompensateBillDomainService {

    /**
     * 售后补偿单聚合根-加工
     *
     * @param compensateBillA 补偿单聚合根
     * @return 返回售后补偿单号
     */
    CompensateBillA process(CompensateBillA compensateBillA);


    /**
     * 售后补偿单审批
     *
     * @param compensateBillA 补偿单聚合根
     * @param checkTypeEnum 审批模式
     * @param compensateCheckCommand 审批命令
     * @return 返回审批结果
     */
    boolean check(CompensateBillA compensateBillA, CheckTypeEnum checkTypeEnum, CompensateCheckCommand compensateCheckCommand);

}
