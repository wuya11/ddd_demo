package com.ddd.demo.ddd.domain.compensate.aggregate;

import com.ddd.demo.ddd.domain.base.BaseEntity;
import com.ddd.demo.ddd.domain.compensate.service.CompensateDealBillDomainService;
import com.ddd.demo.ddd.domain.compensate.vo.CompensateBillId;
import com.ddd.demo.ddd.domain.compensate.vo.CompensateDealBillId;
import com.ddd.demo.ddd.domain.compensate.vo.CompensateFeedbackStrategyV;
import com.ddd.demo.ddd.infrastructure.compensate.enums.CompensateDealBillStateEnum;
import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateException;
import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateFailEnum;
import lombok.Data;

import java.util.Objects;

/**
 * 补偿履约单聚合根
 *
 * @author wl
 * @date 2021-10-12
 */
@Data
public class CompensateDealBillA extends BaseEntity {

    /**
     * 履约单号
     */
    private CompensateDealBillId compensateDealBillId;

    /**
     * 补偿聚合根-主键编码
     */
    private CompensateBillId compensateBillId;

    /**
     * 下级单号
     */
    private String returnId;

    /**
     * 下级系统发起申请-请求json对象
     */
    private String sendDealJson;

    /**
     * 履约单处理结果
     */
    private CompensateDealBillStateEnum compensateDealBillStateEnum;

    /**
     * 下级反馈参数值对象
     */
    private CompensateFeedbackStrategyV feedbackStrategyV;

    /**
     * 下级单反馈备注信息
     */
    private String msg;

    /**
     * 操作人acid
     */
    private Long actAcid;

    public void init() {
        if (Objects.isNull(compensateBillId)) {
            throw new CompensateException(CompensateFailEnum.COID_NULL);
        }
        if (Objects.isNull(actAcid)) {
            throw new CompensateException(CompensateFailEnum.ACTUID_NULL);
        }
        this.compensateDealBillStateEnum = CompensateDealBillStateEnum.DOING;
    }

    public CompensateDealBillA deal(CompensateDealBillDomainService compensateDealBillDomainService) {
        return compensateDealBillDomainService.deal(this);
    }

    public CompensateDealBillA feedback(CompensateDealBillDomainService compensateDealBillDomainService) {
        return compensateDealBillDomainService.feedback(this);
    }

}





















