package com.ddd.demo.ddd.domain.compensate.factory.impl;

import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateBillA;
import com.ddd.demo.ddd.domain.compensate.entity.CompensateStrategyE;
import com.ddd.demo.ddd.domain.compensate.factory.CompensateBillFactory;
import com.ddd.demo.ddd.domain.compensate.vo.CompensateBillId;
import com.ddd.demo.ddd.domain.compensate.vo.OrderV;
import com.ddd.demo.ddd.infrastructure.compensate.assembler.CompensateAssembler;
import com.ddd.demo.ddd.infrastructure.compensate.enums.*;
import com.ddd.demo.ddd.userinterfaces.compensate.command.CompensateApplyCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.command.CompensateBillCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.command.CompensateOfflineCommand;
import com.ddd.demo.feign.user.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

/**
 * 补偿商品聚合根-工厂实现层
 *
 * @author wl
 * @date 2021-9-9
 */
@Service
public class CompensateBillFactoryImpl implements CompensateBillFactory {

    @Autowired
    private CompensateAssembler assembler;

    @Override
    public CompensateBillA createCompensateBillA(CompensateApplyCommand compensateApplyCommand, OrderV orderV, UserResponse userResponse) {
        CompensateBillA compensateBillA = assembler.dtoToCompensateBillA(compensateApplyCommand.getCompensateBillCommand());
        compensateBillA.setOrderV(orderV);
        CompensateBillId billId = createBillId();
        compensateBillA.setCompensateBillId(billId);
        compensateBillA.setCstateEnum(CstateEnum.COMPENSATE_DOING);
        CompensateStrategyE compensateStrategyE = null;
        compensateBillA.init();
        compensateBillA.setCreatorTypeEnum(getCreatorType(userResponse));
        // 初始化补偿策略子实体
        compensateBillA.setStrategyEnum(this.getStrategyEnum(compensateApplyCommand.getCompensateBillCommand()));
        switch (compensateBillA.getStrategyEnum()) {
            case GOODS_BACK:
                compensateStrategyE = CompensateStrategyE.createStrategyGoods(billId, assembler.dtoToStrategyGoodsE(compensateApplyCommand.getCompensateStrategyCommand().getStrategyGoodsCommandList()));
                break;
            case SUPPLY_AGAIN:
                compensateStrategyE = CompensateStrategyE.createStrategySupplyAgain(billId, assembler.dtoToStrategySupplyAgainE(compensateApplyCommand.getCompensateStrategyCommand().getStrategySupplyAgainCommandList()));
                break;
            case NO_GOODS_BACK:
                compensateStrategyE = CompensateStrategyE.createStrategyNoGoods(billId, assembler.dtoToStrategyNoGoodsV(compensateApplyCommand.getCompensateStrategyCommand().getStrategyNoGoodsCommand()));
                break;
            default:
                break;
        }
        compensateBillA.setCompensateStrategyE(compensateStrategyE);
        compensateBillA.setCreator(compensateApplyCommand.getCompensateBillCommand().getActuid());
        compensateBillA.setApplyTime(LocalDateTime.now());
        return compensateBillA;
    }

    @Override
    public CompensateBillA createOfflineCompensateBillA(CompensateOfflineCommand compensateOfflineCommand, OrderV orderV) {
        // 判断订单是否为线下店铺
        this.checkShopOffline(orderV.getShopId());
        CompensateBillA compensateBillA = assembler.dtoToCompensateBillA(compensateOfflineCommand.getCompensateBillCommand());
        compensateBillA.setOrderV(orderV);
        CompensateBillId billId = createBillId();
        compensateBillA.setCompensateBillId(billId);
        compensateBillA.init();
        CompensateStrategyE compensateStrategyE = CompensateStrategyE.createStrategyGoods(billId, assembler.dtoToStrategyGoodsE(compensateOfflineCommand.getStrategyGoodsCommandList()));
        compensateBillA.setCompensateStrategyE(compensateStrategyE);
        compensateBillA.setCreatorTypeEnum(CreatorTypeEnum.BACKEND_TYPE);
        compensateBillA.setStrategyEnum(CompensationStrategyEnum.GOODS_BACK);
        compensateBillA.setCreator(compensateOfflineCommand.getCompensateBillCommand().getActuid());
        compensateBillA.setApplyTime(LocalDateTime.now());
        return compensateBillA;
    }

    private CompensateBillId createBillId() {
        // 模拟生成单据编码唯一主键
        CompensateBillId billId = new CompensateBillId();
        billId.setCoid(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        return billId;
    }

    private void checkShopOffline(int shopId) {
//        List<ShopMess> shopMesses = billFacade.listShopMess(String.valueOf(order.getShopId()));
//        ShopMess shopMess = shopMesses.get(0);
//        if (!OrderShopTypeEnum.isOffline(shopMess.getShopType())) {
//            throw new CompensateException(CompensateFailEnum.SHOP_NOT_OFFLINE);
//        }
        //todo 基于店铺编码，查询店铺接口，判断店铺是否为线下店铺
    }

    private CreatorTypeEnum getCreatorType(UserResponse userResponse) {
        //2后台管理申请,即后台来的数据
        if (Objects.equals(userResponse.getAcType(), CreatorTypeEnum.BACKEND_TYPE.getType())) {
            return CreatorTypeEnum.BACKEND_TYPE;
        } else {
            return CreatorTypeEnum.FRONT_TYPE;
        }
    }


    private CompensationStrategyEnum getStrategyEnum(CompensateBillCommand command) {
        // 策略-补发验证
        if (Objects.equals(command.getRepayway(), RepaywayEnum.SUPPLY_AGAIN.getType())) {
            return CompensationStrategyEnum.SUPPLY_AGAIN;
        } else {
            // 策略-商品补偿验证
            if (Objects.equals(command.getRepayAttribute(), ReplayAttributeEnum.GOODS_ATTRIBUTE.getType())) {
                return CompensationStrategyEnum.GOODS_BACK;
            } else {
                // 策略-非商品补偿验证
                return CompensationStrategyEnum.NO_GOODS_BACK;
            }
        }
    }


}




















