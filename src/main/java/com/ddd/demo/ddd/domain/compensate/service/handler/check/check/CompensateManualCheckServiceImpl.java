package com.ddd.demo.ddd.domain.compensate.service.handler.check.check;

import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateBillA;
import com.ddd.demo.ddd.domain.compensate.service.handler.check.AbstractCompensateCheck;
import com.ddd.demo.ddd.infrastructure.compensate.enums.CheckTypeEnum;
import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateException;
import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateFailEnum;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;

/**
 * 自动审批
 *
 * @author wl
 * @date 2021-9-1
 */
@Component
public class CompensateManualCheckServiceImpl extends AbstractCompensateCheck {

    @Override
    public CheckTypeEnum checkType() {
        return CheckTypeEnum.MANUAL_CHECK;
    }

    @Override
    public boolean canCheck(CompensateBillA compensateBillA) {
        if (super.specialCompanyTag(compensateBillA.getOrderV().getShopId())) {
            // 商城走权限验证，其他不走
            String[] allAuthAcIds = ArrayUtils.addAll(billFacade.listCompensateAuthorityAcid(), billFacade.getAfterSaleManagerAcId().toString());
            if (!ArrayUtils.contains(allAuthAcIds, compensateBillA.getCreator().toString())) {
                throw new CompensateException(CompensateFailEnum.NO_AUTHORITY_RIGHT);
            }
        }
        return true;
    }
}

