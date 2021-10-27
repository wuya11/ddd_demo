package com.ddd.demo.ddd.application.compensate.service;

import com.ddd.demo.ddd.userinterfaces.compensate.command.CompensateApplyCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.command.CompensateFileLinkCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.command.CompensateOfflineCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.command.CompensateUpdateDutyCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.command.dealbill.DealBillCreateCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.dto.CompensateDto;
import com.ddd.demo.ddd.userinterfaces.compensate.query.CompensateBillQuery;

import java.util.List;

/**
 * 补偿单业务应用层-接口层
 *
 * @author wl
 * @date 2021-8-30
 */
public interface CompensateBusinessService {

    /**
     * 保存售后补偿单申请信息-标准保存
     *
     * @param compensateApplyCommand 补偿单申请对象
     * @return 售后补偿单号
     */
    long save(CompensateApplyCommand compensateApplyCommand);

    /**
     * 保存售后补偿单申请信息-线下店铺
     *
     * @param compensateOfflineCommand 补偿单申请对象
     * @return 售后补偿单号
     */
    long saveOffline(CompensateOfflineCommand compensateOfflineCommand);


    /**
     * 补偿履约
     *
     * @param dealBillCreateCommand 补偿单处理命令
     */
    void deal(DealBillCreateCommand dealBillCreateCommand);

    /**
     * 批量审批
     *
     * @param coids 补充单号
     */
    void batchCheck(long[] coids);

    /**
     * 批量终止
     *
     * @param coids 补充单号
     */
    void batchCancel(long[] coids);


    /**
     * 更新责任方
     *
     * @param compensateUpdateDutyCommand 责任信息
     */
    void updateDuty(CompensateUpdateDutyCommand compensateUpdateDutyCommand);

    /**
     * 保存文件信息
     *
     * @param compensateFileLinkCommand 文件信息
     */
    void saveFileLink(CompensateFileLinkCommand compensateFileLinkCommand);

    /**
     * 查询补偿单列表信息-只是模拟查询，最多返回100条
     *
     * @param query 查询条件
     */
    List<CompensateDto> listCompensateDto(CompensateBillQuery query);

}
