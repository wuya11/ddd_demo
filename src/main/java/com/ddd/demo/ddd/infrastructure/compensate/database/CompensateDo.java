package com.ddd.demo.ddd.infrastructure.compensate.database;

import com.ddd.demo.ddd.infrastructure.compensate.enums.CompensationStrategyEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 售后补偿数据表对象
 *
 * @author wangling
 * @date 2019-12-13
 */
@Data
public class CompensateDo implements Serializable {

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
     * 补偿方式
     */
    private Integer repayway;
    /**
     * 补偿属性 1商品 0非商品
     */
    private Byte repayAttribute;
    /**
     * 添加人类型（1前台、2后台）
     */
    private Byte creatorType;
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
     * @see CompensationStrategyEnum
     */
    private Byte strategyType;

    /**
     * 补偿策略信息-实际为其他明细表存储，测试demo设置为一个json字符串
     */
    private String strategyJson;
}
