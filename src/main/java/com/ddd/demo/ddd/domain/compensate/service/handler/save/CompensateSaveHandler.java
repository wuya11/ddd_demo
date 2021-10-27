package com.ddd.demo.ddd.domain.compensate.service.handler.save;

import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateBillA;


/**
 * 补偿单聚合根保存顶级接口
 * 补偿单存在三种策略，具有三种保存方式，抽象顶级接口是为了后续扩展，达到基于接口编程的目的
 *
 *
 * @author wl
 * @date 2021-8-30
 */
public interface CompensateSaveHandler extends CompensateBaseHandler {

    /**
     * 保存售后补偿单聚合根
     *
     * @param compensateBillA 补偿单聚合根
     * @return 返回售后补偿单号
     */
    CompensateBillA process(CompensateBillA compensateBillA);
}
