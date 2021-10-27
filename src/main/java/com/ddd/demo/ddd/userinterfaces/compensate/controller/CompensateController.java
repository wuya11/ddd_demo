package com.ddd.demo.ddd.userinterfaces.compensate.controller;

import com.ddd.demo.ddd.application.compensate.service.CompensateBusinessService;
import com.ddd.demo.ddd.application.compensate.service.CompensateEventService;
import com.ddd.demo.ddd.userinterfaces.compensate.command.CompensateApplyCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.command.CompensateFileLinkCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.command.CompensateOfflineCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.command.CompensateUpdateDutyCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.command.dealbill.DealBillCreateCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.dto.CompensateDto;
import com.ddd.demo.ddd.userinterfaces.compensate.event.OrderFeedbackEvent;
import com.ddd.demo.ddd.userinterfaces.compensate.event.RefundFeedbackEvent;
import com.ddd.demo.ddd.userinterfaces.compensate.query.CompensateBillQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 售后补偿单业务控制层
 *
 * @author wl
 * @date 2021-9-3
 */
@RestController
@RequestMapping("compensate")
public class CompensateController {

    @Autowired
    private CompensateBusinessService businessService;
    @Autowired
    private CompensateEventService compensateEventService;

    /**
     * 发起售后补偿申请
     *
     * @param compensateApplyCommand 售后补偿申请信息
     * @return 申请单号
     */
    @PostMapping("apply")
    public Long save(@Validated @RequestBody CompensateApplyCommand compensateApplyCommand) {
        return businessService.save(compensateApplyCommand);
    }

    /**
     * 线下店铺申请售后补偿
     *
     * @param compensateOfflineCommand 售后补偿申请信息
     * @return 补偿编号
     */
    @PostMapping("apply/offline")
    public Long saveOffline(@RequestBody @Validated CompensateOfflineCommand compensateOfflineCommand) {
        return businessService.saveOffline(compensateOfflineCommand);
    }


    /**
     * 变更归属
     *
     * @param command 更新对象
     */
    @PatchMapping("duty")
    public void updateDuty(@RequestBody @Validated CompensateUpdateDutyCommand command) {
        businessService.updateDuty(command);
    }

    /**
     * 文件链接保存
     *
     * @param compensateFileLinkCommand 文件链接对象
     */
    @PatchMapping("img")
    public void uploadFileLink(@RequestBody @Validated CompensateFileLinkCommand compensateFileLinkCommand) {
        businessService.saveFileLink(compensateFileLinkCommand);
    }

    /**
     * 确认补偿
     *
     * @param dealBillCreateCommand 确认补偿命令
     */
    @PostMapping("deal")
    public void confirmCompensate(@RequestBody @Validated DealBillCreateCommand dealBillCreateCommand) {
        businessService.deal(dealBillCreateCommand);
    }

    /**
     * 批量审核
     *
     * @param coids 补偿单号列表
     */
    @PostMapping("batch/check")
    public void batchCheckCompensate(@RequestBody @NotEmpty long[] coids) {
        businessService.batchCheck(coids);
    }

    /**
     * 批量终止
     *
     * @param coids 补偿单号列表
     */
    @PostMapping("batch/cancel")
    public void batchCancel(@RequestBody @NotEmpty long[] coids) {
        businessService.batchCancel(coids);
    }

    /**
     * 模拟接收外部系统回传回来的-订单创建消息
     * （正常应该是 基于mns消息，或者其他消息，监听到消息并处理）
     *
     * @param orderFeedbackEvent 订单反馈消息
     */
    @PostMapping("back/order")
    public void orderFeedback(@RequestBody OrderFeedbackEvent orderFeedbackEvent) {
        compensateEventService.orderFeedback(orderFeedbackEvent);
    }

    /**
     * 模拟接收外部系统回传回来的-退款反馈消息
     * （正常应该是 基于mns消息，或者其他消息，监听到消息并处理）
     *
     * @param refundFeedbackEvent 退款反馈消息
     */
    @PostMapping("back/refund")
    public void refundFeedback(@RequestBody RefundFeedbackEvent refundFeedbackEvent) {
        compensateEventService.refundFeedback(refundFeedbackEvent);
    }

    /**
     * 查询补偿单列表信息
     *
     * @param query 查询条件
     */
    @GetMapping("list")
    public List<CompensateDto> listCompensate(CompensateBillQuery query) {
        return businessService.listCompensateDto(query);
    }

}
