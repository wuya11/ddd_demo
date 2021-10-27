package com.wangling.base.tool.ddd.compensate;

import com.ddd.demo.ddd.infrastructure.compensate.enums.RepaywayEnum;
import com.ddd.demo.ddd.userinterfaces.compensate.command.CompensateApplyCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.command.CompensateBillCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.command.CompensateStrategyCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.command.CompensateUpdateDutyCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.command.dealbill.DealBillCreateCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.command.strategy.StrategyGoodsCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.event.RefundFeedbackEvent;
import com.wangling.base.tool.TestBase;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 售后补偿单测试
 * 直接运行或debug测试用例，可测序相关的功能，建议根据顺序测试
 * 本例仅测试了退款流程，其他两个流程未测试，可能存在流程不通或代码异常的情况，读者可自行调整。
 * 功能不是重点，主要是展示ddd的落地流程
 *
 * @author wl
 * @date 2021/10/21 9:51
 */
public class CompensateControllerTest extends TestBase {

    /**
     * step 1:  售后补偿单创建-基于退款模式  （其他模式，修改测试用例数据，可测试）
     */
    @Test
    public void testAdd() throws Exception {
        CompensateApplyCommand compensateApplyCommand = new CompensateApplyCommand();
        CompensateBillCommand compensateBillCommand = new CompensateBillCommand();
        compensateBillCommand.setActuid(31321506522838016L);
        compensateBillCommand.setDutytype((byte) 1);
        compensateBillCommand.setDescription("ces");
        compensateBillCommand.setReasonid("12");
        compensateBillCommand.setRepayAttribute((byte) 1);
        compensateBillCommand.setRepayway(RepaywayEnum.REFUND.getType());
        compensateBillCommand.setSubOid(252840521259467465L);
        compensateBillCommand.setImgids("www.test,www.biatu.tom");
        compensateApplyCommand.setCompensateBillCommand(compensateBillCommand);

        List<StrategyGoodsCommand> strategyGoodsCommandList = new ArrayList<>();
        StrategyGoodsCommand strategyGoodsCommand = new StrategyGoodsCommand();
        strategyGoodsCommand.setAmount(BigDecimal.ONE);
        strategyGoodsCommand.setGoodsNum(1);
        strategyGoodsCommand.setOgid(252840521267851982L);
        strategyGoodsCommand.setReferencePrice(BigDecimal.ONE);
        strategyGoodsCommandList.add(strategyGoodsCommand);
        StrategyGoodsCommand strategyGoodsCommand1 = new StrategyGoodsCommand();
        strategyGoodsCommand1.setAmount(BigDecimal.ONE);
        strategyGoodsCommand1.setGoodsNum(1);
        strategyGoodsCommand1.setOgid(252840521267851983L);
        strategyGoodsCommand1.setReferencePrice(BigDecimal.ONE);
        strategyGoodsCommandList.add(strategyGoodsCommand1);
        CompensateStrategyCommand compensateStrategyCommand = new CompensateStrategyCommand();
        compensateStrategyCommand.setStrategyGoodsCommandList(strategyGoodsCommandList);
        compensateApplyCommand.setCompensateStrategyCommand(compensateStrategyCommand);

        String json = objectMapper.writeValueAsString(compensateApplyCommand);
        String response = mockMvc.perform(post("/compensate/apply")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(response);
    }

    /**
     * step 2: 手动调用做补偿单审批-补偿单审批通过
     */
    @Test
    public void testManualCheck() throws Exception {
        long[] coids = new long[]{1634621164845L};
        String json = objectMapper.writeValueAsString(coids);
        String response = mockMvc.perform(post("/compensate/batch/check")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(response);
    }

    /**
     * step 3: 手动调用处理-补偿做履约处理
     */
    @Test
    public void testDeal() throws Exception {
        DealBillCreateCommand dealBillCreateCommand = new DealBillCreateCommand();
        dealBillCreateCommand.setCoid(1634621164845L);
        dealBillCreateCommand.setActuid(0L);
        String json = objectMapper.writeValueAsString(dealBillCreateCommand);
        String response = mockMvc.perform(post("/compensate/deal")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(response);
    }

    /**
     * step 4: 手动模拟-退款系统-反馈退款消息
     */
    @Test
    public void testRefundFeedback() throws Exception {
        RefundFeedbackEvent refundFeedbackEvent = new RefundFeedbackEvent();
        // 模拟数据，先查看数据表中 第3步执行成功后，记录的dealId值
        refundFeedbackEvent.setSourceId(3L);
        refundFeedbackEvent.setMsg("退款处理成功");
        refundFeedbackEvent.setState(true);
        String json = objectMapper.writeValueAsString(refundFeedbackEvent);
        String response = mockMvc.perform(post("/compensate/back/refund")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(response);
    }

    /**
     * 查询补偿单信息
     */
    @Test
    public void listBudgetPool() throws Exception {
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cstate", "99");

        String response = mockMvc.perform(get("/compensate/list")
                .params(params)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(response);
    }


    /**
     * 非过程功能测试-修改责任方
     */
    @Test
    public void testUpdateDuty() throws Exception {
        CompensateUpdateDutyCommand compensateUpdateDutyCommand = new CompensateUpdateDutyCommand();
        compensateUpdateDutyCommand.setCoid(1L);
        compensateUpdateDutyCommand.setDutyType((byte) 2);
        String json = objectMapper.writeValueAsString(compensateUpdateDutyCommand);
        String response = mockMvc.perform(patch("/compensate/duty")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(response);
    }

    /**
     * 非过程功能测试-终止补偿单
     */
    @Test
    public void testBatchCancel() throws Exception {
        long[] coids = new long[]{1634621164845L};
        String json = objectMapper.writeValueAsString(coids);
        String response = mockMvc.perform(post("/compensate/batch/cancel")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(response);
    }
}
