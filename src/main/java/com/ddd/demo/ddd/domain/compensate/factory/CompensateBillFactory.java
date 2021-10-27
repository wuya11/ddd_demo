package com.ddd.demo.ddd.domain.compensate.factory;

import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateBillA;
import com.ddd.demo.ddd.domain.compensate.vo.OrderV;
import com.ddd.demo.ddd.userinterfaces.compensate.command.CompensateApplyCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.command.CompensateOfflineCommand;
import com.ddd.demo.feign.user.dto.UserResponse;

/**
 * 补偿单聚合根工厂-接口层
 *
 * @author wl
 * @date 2021-9-9
 */
public interface CompensateBillFactory {

    /**
     * 创建-补偿单聚合根
     *
     * @param compensateApplyCommand 补偿业务前端传入申请对象
     * @param orderV                 订单信息
     * @param userResponse           用户信息
     * @return 返回补偿单聚合根
     */
    CompensateBillA createCompensateBillA(CompensateApplyCommand compensateApplyCommand, OrderV orderV, UserResponse userResponse);

    /**
     * 创建-补偿单聚合根-线下模式
     *
     * @param compensateOfflineCommand 线下补偿业务前端传入dto
     * @param orderV                   订单信息
     * @return 返回补偿单聚合根
     */
    CompensateBillA createOfflineCompensateBillA(CompensateOfflineCommand compensateOfflineCommand, OrderV orderV);
}
