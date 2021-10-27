package com.ddd.demo.ddd.domain.compensate.service.handler.deal;

import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateDealBillA;
import com.ddd.demo.ddd.infrastructure.compensate.acl.CompensateBusinessFacade;
import com.ddd.demo.ddd.infrastructure.compensate.enums.CompensateDealBillStateEnum;
import com.ddd.demo.ddd.infrastructure.compensate.enums.DealBillWayEnum;
import com.ddd.demo.feign.order.dto.CreateOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 订单重新补发实现类
 *
 * @author wl
 * @date 2021-09-07
 */
@Component
public class OrderRepeatServiceImpl implements CompensateDealHandler {

    @Autowired
    private CompensateBusinessFacade compensateBusinessFacade;

    @Override
    public DealBillWayEnum dealBillWayEnum() {
        return DealBillWayEnum.ORDER_REPEAT;
    }

    @Override
    public CompensateDealBillA deal(CompensateDealBillA compensateDealBillA) {
        // 简单说明：履约单申请创建订单，构建时，也传入履约单唯一编码，做为订单 对象的 sourceId,后续订单创建成功后，又发送通知，通知信息中包括 sourceId
        // 模拟调用外部系统创建订单
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        // 具体为订单构建参数的流程省去
        compensateBusinessFacade.createOrder(createOrderRequest);
        // 调用后，设置当前状态为处理中
        compensateDealBillA.setCompensateDealBillStateEnum(CompensateDealBillStateEnum.DOING);
        compensateDealBillA.setSendDealJson("ask order json");
        compensateDealBillA.setReturnId("order_id");
        return compensateDealBillA;
    }

    @Override
    public CompensateDealBillA feedback(CompensateDealBillA compensateDealBillA) {
        // 模拟接受到订单反馈消息后，处理后续逻辑
        // 获取来源编码
        // 基于来源编码获取履约单实体
        // 针对返回的结果做判断处理
        compensateDealBillA.setCompensateDealBillStateEnum(CompensateDealBillStateEnum.SUCCESS);
        // 设置下级单号
        compensateDealBillA.setReturnId(compensateDealBillA.getFeedbackStrategyV().getOrderReturnV().getSubOid().toString());
        return compensateDealBillA;
    }


}






















