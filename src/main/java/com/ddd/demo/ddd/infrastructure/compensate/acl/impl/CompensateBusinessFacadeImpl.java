package com.ddd.demo.ddd.infrastructure.compensate.acl.impl;

import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateBillA;
import com.ddd.demo.ddd.infrastructure.compensate.acl.CompensateBusinessFacade;
import com.ddd.demo.feign.order.OrderFeign;
import com.ddd.demo.feign.order.dto.CreateOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 补偿单聚合根防腐层-实现层
 *
 * @author wl
 * @date 2021-9-1
 */
@Service
public class CompensateBusinessFacadeImpl implements CompensateBusinessFacade {

    @Autowired
    private OrderFeign orderFeign;

    @Override
    public void deductUserPoint(String reasonId, long cuid, long coid) {
        // 模拟调用其他系统，扣减积分信息
//        TemplateReason templateReason = getTemplateReason(reasonId);
//        if (Objects.nonNull(templateReason)) {
//            UserDataPointDto userDataPointDto = new UserDataPointDto();
//            userDataPointDto.setAcId(cuid);
//            userDataPointDto.setPoint(templateReason.getIntegral());
//            userDataPointDto.setType(CompensateConfig.POINT_CUT);
//            userDataPointDto.setChgDesc("补偿单【" + coid + "】申请扣减");
//            ResponseBody<Integer, EmptyMeta> responseBody = userFeign.dataPoint(userDataPointDto);
//            if (!responseBody.isSuccess()) {
//                ErrorLogger.getInstance().log("调用用户中心扣减积分失败,补偿单号：" + coid + ",失败原因：" + responseBody.getMessage());
//            }
//        }
    }

    @Override
    public void recoveryUserPoint(String reasonId, long cuid, long coid) {
    }

    @Override
    public void sendCheckMessage(CompensateBillA compensateBillA) {
        long actuid = compensateBillA.getActuid();
        // 模拟调用其他系统发送消息
        //需要手动审核，并通知相关负责人，首先查询申请人上级
//        ResponseBody<List<UserInfo>, EmptyMeta> responseBody = userFeign.queryLeaderByAcId(actuid);
//        String message = String.format("调用用户中心接口获取上级信息失败,actuid:%d,原因:%s", actuid, responseBody.getMessage());
//        List<UserInfo> leaderInfoList = ResponseBodyUtil.safeData(responseBody, message);
//        Long notifyLeaderAcId = Long.parseLong(leaderInfoList.get(0).getAcId());
//        if (!ArrayUtils.contains(configuration.getCompensateAuthorityAcid(), notifyLeaderAcId.toString())) {
//            notifyLeaderAcId = configuration.getAfterSaleManagerAcId();
//        }
//        DingDingRequest dingDingRequest = new DingDingRequest();
//        dingDingRequest.setUserId(notifyLeaderAcId.toString());
//        String sendMessage = String.format("补偿单单号:%s,请及时审批,补偿方案:%s,金额:%s",
//                compensateBillAr.getCoidVo().getCoid(),
//                compensateBillAr.getRepaywayEnum().getText(),
//                compensateBillAr.getCompensateBillDbVo().getCompensateMoney()
//        );
//        dingDingRequest.setContent(sendMessage);
        //  dingDingFeign.send(dingDingRequest);
    }

    @Override
    public void createOrder(CreateOrderRequest createOrder) {
        orderFeign.crateOrder(createOrder);
    }

    @Async
    @Override
    public void sendCompensateCreateMns(long coid, int shopId) {
        // 调用钉钉 发送消息 测试代码不写
//        MnsSendCompensate mnsSendCompensate = new MnsSendCompensate();
//        mnsSendCompensate.setTag(AFTERSALE_SEND_CREATE);
//        MnsSendCompensate.Content content = new MnsSendCompensate.Content();
//        content.setCoid(coid);
//        content.setShopId(shopId);
//        content.setState(CstateEnum.APPROVAL_PENDING.getValue().byteValue());
//        mnsSendCompensate.setContent(content);
//        compensateMnsSender.sendCompensateCreate(mnsSendCompensate);
    }
}























