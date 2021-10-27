package com.ddd.demo.ddd.userinterfaces.compensate.dto;

import com.ddd.demo.ddd.infrastructure.compensate.enums.CompensationStrategyEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 售后补偿列表对象
 *
 * @author wangling
 * @date 2021-10-22
 */
@Data
public class CompensateDto implements Serializable {

    /**
     * 补偿编号
     */
    private Long coid;

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
    private Byte dutytype;

    /**
     * 责任归属文本
     */
    private String dutytypeText;
    /**
     * 补偿方式
     */
    private Integer repayway;
    /**
     * 补偿文本
     */
    private String repaywayText;
    /**
     * 补偿属性 1商品 0非商品
     */
    private Byte repayAttribute;
    /**
     * 补偿属性文本
     */
    private String repayAttributeText;
    /**
     * 添加人类型（1前台、2后台）
     */
    private Byte creatorType;
    /**
     * 添加人类型文本
     */
    private String creatorTypeText;
    /**
     * 操作人编码
     */
    private Long actuid;

    /**
     * 补偿用户编号
     */
    private Long cuid;
    /**
     * 店铺编号
     */
    private Integer shopId;
    /**
     * 补偿建议总额
     */
    private BigDecimal referenceMoney;

    /**
     * 补偿总额
     */
    private BigDecimal compensateMoney;

    /**
     * 补偿单状态（99待审核，1补偿中，4待补偿，2完成,100终止）
     */
    private Integer cstate;

    /**
     * 补偿单状态文本
     */
    private String cstateText;

    /**
     * 是否完成
     */
    private Byte isComplete;

    /**
     * 申请时间
     */
    private LocalDateTime applyTime;

    /**
     * 完成时间
     */
    private LocalDateTime finishedTime;

    /**
     * 补偿策略类型
     *
     * @see CompensationStrategyEnum
     */
    private Byte strategyType;

    /**
     * 补偿策略文本
     */
    private String strategyTypeText;

    /**
     * 补偿策略信息-实际为其他明细表存储，测试demo设置为一个json字符串
     */
    private String strategyJson;
}
