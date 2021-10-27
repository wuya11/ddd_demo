package com.ddd.demo.ddd.domain.compensate.service.handler.check.check;

import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateBillA;
import com.ddd.demo.ddd.domain.compensate.service.handler.check.AbstractCompensateCheck;
import com.ddd.demo.ddd.infrastructure.compensate.acl.CompensateBusinessFacade;
import com.ddd.demo.ddd.infrastructure.compensate.enums.CheckTypeEnum;
import com.ddd.demo.ddd.infrastructure.compensate.enums.CreatorTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 自动审批
 *
 * @author wl
 * @date 2021-9-1
 */
@Component
public class CompensateAutoCheckServiceImpl extends AbstractCompensateCheck {

    @Autowired
    private CompensateBusinessFacade compensateBusinessFacade;

    @Override
    public CheckTypeEnum checkType() {
        return CheckTypeEnum.AUTO_CHECK;
    }

    @Override
    public boolean canCheck(CompensateBillA compensateBillA) {
        boolean epetTag = super.specialCompanyTag(compensateBillA.getOrderV().getShopId());
        // 商城且为后台用户类型创建的，才可以进入自动审批
        if (epetTag && Objects.equals(compensateBillA.getCreatorTypeEnum(), CreatorTypeEnum.BACKEND_TYPE)) {
            // - 补偿建议总额 < 补偿金额  不能自动审批通过
            if (compensateBillA.getReferenceMoney().compareTo(compensateBillA.getCompensateMoney()) < 0) {
                // 调用防腐层发送消息
                compensateBusinessFacade.sendCheckMessage(compensateBillA);
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}