package com.ddd.demo.ddd.infrastructure.compensate.config;

/**
 * 售后补偿静态相关变量
 * 统一管理程序中一些通用的静态变量，防止在代码中出现魔法值，防止同一个固定的变量多次声明使用
 *
 * @author wl
 * @date 2021-9-7
 */

public class CompensateConfig {

    /**
     * 海际店铺编码
     */
    public static final Integer HJ_SHOP = 77;
    /**
     * 海际平台channel_type
     */
    public static final Integer HJ_TYPE = 1;
    /**
     * 易宠平台channel_type
     */
    public static final Integer EPET_TYPE = 0;
    /**
     * 赠品标识
     */
    public static final Byte GIVEAWAY = 1;
    /**
     * 清仓商品-赠品渠道编码
     */
    public static final Integer CLEAN_GIVEAWAY_CHANNEL = 6869;
    /**
     * 清仓商品-非赠品渠道编码
     */
    public static final Integer CLEAN_NOT_GIVEAWAY_CHANNEL = 51;
    /**
     * 新增积分
     */
    public static final Integer POINT_ADD = 1;
    /**
     * 扣减积分
     */
    public static final Integer POINT_CUT = -1;

    /**
     * 商城订单号最大长度值
     */
    public static final Integer EPET_ORDER_LENGTH = 18;


}
