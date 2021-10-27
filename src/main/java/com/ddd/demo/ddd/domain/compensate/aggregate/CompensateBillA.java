package com.ddd.demo.ddd.domain.compensate.aggregate;

import com.ddd.demo.ddd.domain.base.BaseEntity;
import com.ddd.demo.ddd.domain.compensate.entity.CompensateStrategyE;
import com.ddd.demo.ddd.domain.compensate.service.CompensateBillDomainService;
import com.ddd.demo.ddd.domain.compensate.vo.CompensateBillId;
import com.ddd.demo.ddd.domain.compensate.vo.OrderV;
import com.ddd.demo.ddd.infrastructure.compensate.enums.*;
import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateException;
import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateFailEnum;
import com.ddd.demo.ddd.userinterfaces.compensate.command.CompensateCheckCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.command.CompensateFileLinkCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.command.CompensateUpdateDutyCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.command.strategy.CompensateStateChangeCommand;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 售后补偿单聚合根
 *
 * @author wl
 * @date 2021-8-26
 */
@Data
public class CompensateBillA extends BaseEntity {

    /**
     * 补偿编码
     */
    private CompensateBillId compensateBillId;
    /**
     * 原因编码
     */
    private String reasonid;
    /**
     * '订单编号'
     */
    private Long subOid;

    /**
     * 申请描述
     */
    private String description;
    /**
     * 附件地址链接
     */
    private String imgids;
    /**
     * 责任归属
     */
    private DutytypeEnum dutytypeEnum;
    /**
     * @see RepaywayEnum
     */
    private RepaywayEnum repaywayEnum;
    /**
     * @see ReplayAttributeEnum
     */
    private ReplayAttributeEnum replayAttributeEnum;
    /**
     * @see CreatorTypeEnum
     */
    private CreatorTypeEnum creatorTypeEnum;
    /**
     * 操作人编码
     */
    private Long actuid;
    /**
     * @see CstateEnum
     */
    private CstateEnum cstateEnum;
    /**
     * @see CompensationStrategyEnum
     */
    private CompensationStrategyEnum strategyEnum;

    /**
     * 是否完成
     */
    private Integer isComplete;

    /**
     * 申请时间
     */
    private LocalDateTime applyTime;

    /**
     * 完成时间
     */
    private LocalDateTime finishedTime;

    /**
     * 创建人
     */
    private Long creator;
    /**
     * 策略子实体
     */
    private CompensateStrategyE compensateStrategyE;

    /**
     * 订单值对象
     */
    private OrderV orderV;
    /**
     * 补偿理论金额
     */
    private BigDecimal referenceMoney;
    /**
     * 补偿实际金额
     */
    private BigDecimal compensateMoney;

    /**
     * 补偿单基础信息验证
     */
    public void init() {
        // 基础必填字段验证
        if (Objects.isNull(this.subOid)) {
            throw new CompensateException(CompensateFailEnum.SUBOID_NULL);
        }
        if (Objects.isNull(this.actuid)) {
            throw new CompensateException(CompensateFailEnum.ACTUID_NULL);
        }
        if (Objects.isNull(this.reasonid)) {
            throw new CompensateException(CompensateFailEnum.REASONID_NULL);
        }
        if (Objects.equals(this.replayAttributeEnum, ReplayAttributeEnum.NO_GOODS_ATTRIBUTE) &&
                Objects.equals(this.dutytypeEnum, DutytypeEnum.SIPPLIER_REASON)) {
            throw new CompensateException(CompensateFailEnum.NOGOODS_SIPPLIER_REASON);
        }
        if (Objects.equals(this.replayAttributeEnum, ReplayAttributeEnum.NO_GOODS_ATTRIBUTE) &&
                Objects.equals(this.repaywayEnum, RepaywayEnum.REFUND)) {
            throw new CompensateException(CompensateFailEnum.NOGOODS_REFUND);
        }
    }

    public CompensateBillA process(CompensateBillDomainService compensateBillDomainService) {
        return compensateBillDomainService.process(this);
    }

    public boolean canUpdateDuty(CompensateUpdateDutyCommand compensateUpdateDutyCommand) {
        DutytypeEnum dutytypeEnum = DutytypeEnum.getSafeDutyType(compensateUpdateDutyCommand.getDutyType());
        if (!canUpdateDuty(this.getCstateEnum(), dutytypeEnum)) {
            throw new CompensateException(CompensateFailEnum.UPDATE_DUTY_ERROR);
        }
        return true;
    }

    public boolean canUpdateFileLink(CompensateFileLinkCommand compensateFileLinkCommand) {
        if (!canChangeBillOtherInfo(this.getCstateEnum())) {
            throw new CompensateException(CompensateFailEnum.UPDATE_FILE_ILLEGAL);
        }
        // 验证传入的文件是否合规
        return true;
    }

    public boolean canCancel() {
        CstateEnum cstateEnum = this.getCstateEnum();
        // 待审核，待沟通，待补偿 可以发起终止
        boolean canCancel = Objects.equals(cstateEnum, CstateEnum.APPROVAL_PENDING) ||
                Objects.equals(cstateEnum, CstateEnum.COMPENSATE_PENDING) ||
                Objects.equals(cstateEnum, CstateEnum.COMPENSATE_PAUSE);
        if (!canCancel) {
            throw new CompensateException(CompensateFailEnum.CANCEL_ILLEGAL);
        }
        return true;
    }

    public boolean changeBillState(CompensateStateChangeCommand compensateStateChangeCommand) {
        CstateEnum.getSafeCstateEnum(compensateStateChangeCommand.getState());
        if (Objects.equals(this.getCstateEnum(), CstateEnum.COMPENSATE_DOING) ||
                Objects.equals(this.getCstateEnum(), CstateEnum.COMPENSATE_PENDING) ||
                Objects.equals(this.getCstateEnum(), CstateEnum.COMPENSATE_PAUSE)) {
            return true;
        } else {
            throw new CompensateException(CompensateFailEnum.CHANGE_ILLEGAL);
        }
    }

    public boolean check(CompensateBillDomainService compensateBillDomainService, CheckTypeEnum checkTypeEnum, CompensateCheckCommand compensateCheckCommand) {
        return compensateBillDomainService.check(this, checkTypeEnum, compensateCheckCommand);
    }

    private boolean canUpdateDuty(CstateEnum cstateEnum, DutytypeEnum dutytypeEnum) {
        if (Objects.equals(dutytypeEnum, DutytypeEnum.SIPPLIER_REASON)) {
            throw new CompensateException(CompensateFailEnum.UPDATE_DUTY_ERROR);
        }
        return canChangeBillOtherInfo(cstateEnum);
    }

    private boolean canChangeBillOtherInfo(CstateEnum cstateEnum) {
        return (!Objects.equals(cstateEnum, CstateEnum.COMPENSATE_END) &&
                !Objects.equals(cstateEnum, CstateEnum.COMPENSATE_CANCEL));
    }
}
