package com.ddd.demo.ddd.domain.compensate.service.handler.save;

import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateBillA;


/**
 * 补偿单聚合根基础顶级接口
 * 库存判断和保存都需要-本例是去掉了库存判断的逻辑
 *
 * @author wl
 * @date 2021-8-30
 */
public interface CompensateBaseHandler {

    /**
     * 校验过程信息
     *
     * @param compensateBillA 补偿单聚合根
     * @return 返回验证结果
     */
    default boolean check(CompensateBillA compensateBillA) {
        return true;
    }

    /**
     * 是否满足匹配规则
     *
     * @param compensateBillA 补偿单聚合根
     * @return 返回验证结果
     */
    boolean canApply(CompensateBillA compensateBillA);
}
