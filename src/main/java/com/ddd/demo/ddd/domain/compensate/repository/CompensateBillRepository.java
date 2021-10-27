package com.ddd.demo.ddd.domain.compensate.repository;

import com.ddd.demo.ddd.domain.base.BaseRepository;
import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateBillA;
import com.ddd.demo.ddd.domain.compensate.vo.CompensateBillId;
import com.ddd.demo.ddd.userinterfaces.compensate.command.CompensateCancelCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.command.CompensateCheckCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.command.CompensateFileLinkCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.command.CompensateUpdateDutyCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.command.strategy.CompensateStateChangeCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.query.CompensateBillQuery;

import java.util.List;

/**
 * 补偿单聚合根仓库-接口层
 *
 * @author wl
 * @date 2021-8-26
 */
public interface CompensateBillRepository extends BaseRepository<CompensateBillA, CompensateBillId> {

    /**
     * 修改补偿单责任方信息
     *
     * @param compensateUpdateDutyCommand 修改责任方信息
     */
    void updateBillDuty(CompensateUpdateDutyCommand compensateUpdateDutyCommand);

    /**
     * 修改补偿单附件信息
     *
     * @param compensateFileLinkCommand 补偿单数据表基础对象
     */
    void updateBillFileLink(CompensateFileLinkCommand compensateFileLinkCommand);

    /**
     * 设置单据为终止
     *
     * @param compensate 补偿单数据表基础对象
     */
    void cancel(CompensateCancelCommand compensate);

    /**
     * 变更单据状态
     *
     * @param compensateStateChangeCommand 补偿单数据表基础对象
     */
    void changeState(CompensateStateChangeCommand compensateStateChangeCommand);

    /**
     * 设置补偿单审批通过
     *
     * @param checkCommand 单据编码
     */
    void passCheck(CompensateCheckCommand checkCommand);

    /**
     * 查询实体信息
     *
     * @param query 查询条件
     */
    List<CompensateBillA> listCompensateBillA(CompensateBillQuery query);
}
