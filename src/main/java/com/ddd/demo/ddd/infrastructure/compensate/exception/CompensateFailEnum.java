package com.ddd.demo.ddd.infrastructure.compensate.exception;

/**
 * 售后补偿失败枚举
 *
 * @author wl
 * @date 2021-8-26
 */
public enum CompensateFailEnum {

    /**
     * 补偿单为空
     */
    COMPENSATE_IS_NULL("compensate_is_null", "补偿单对象为空"),
    /**
     * 订单号无效，查询订单对象为空
     */
    ORDER_IS_NULL("order_is_null", "订单号无效，查询订单对象为空"),

    /**
     * 人员信息查询为空
     */
    USER_IS_NULL("user_is_null", "人员编码无效，人员信息查询为空"),
    /**
     * 责任数值与枚举转换失败
     */
    DUTY_ENUM_NULL("duty_null", "责任类型转换异常"),

    /**
     * 补偿属性与枚举转换失败
     */
    REPLAY_ATTRIBUTE_ENUM_NULL("replay_Attribute_null", "补偿属性类型转换异常"),
    /**
     * 补偿发起人类型枚举转换失败
     */
    CREATE_TYPE_ENUM_NULL("create_type_null", "补偿发起人类型转换异常"),
    /**
     * 补偿方式类型枚举转换失败
     */
    REPAY_WAY_ENUM_NULL("repay_way_null", "补偿方式类型转换异常"),
    /**
     * 订单号不能为空
     */
    SUBOID_NULL("sub_oid_null", "订单号不能为空"),
    /**
     * 补偿申请人不能为空
     */
    ACTUID_NULL("actuid_null", "补偿申请人不能为空"),
    /**
     * 补偿原因不能为空
     */
    REASONID_NULL("reason_null", "补偿原因不能为空"),
    /**
     * 申请校验非商品问题时,不能传供应商追责分类
     */
    NOGOODS_SIPPLIER_REASON("nogoods_sipplier_reason", "申请校验非商品问题时,不能传供应商追责分类"),
    /**
     * 非商品属性,不能选择退款
     */
    NOGOODS_REFUND("nogoods_refund", "非商品属性,不能选择退款"),
    /**
     * 实际补偿值必须大于零
     */
    AMOUNT_GREATER_THAN_ZERO("amount_greater_than_zero", "实际补偿值必须大于零"),
    /**
     * 补偿商品编码不能为空
     */
    OGID_NULL("ogid_null", "补偿商品编码不能为空"),
    /**
     * 问题商品编码不能为空
     */
    HISTORY_OGID_NULL("history_ogid_null", "问题商品编码不能为空"),
    /**
     * 商品数量必须大于零
     */
    GOODSNUM_GREATER_THAN_ZERO("goodsnum_greater_than_zero", "补偿商品数量必须大于零"),
    /**
     * 理论补偿值不能小于零
     */
    REFERENCEPRICE_GREATER_THAN_ZERO("referenceprice_greater_than_zero", "理论补偿值不能小于零"),
    /**
     * 仓库编码必须大于零
     */
    WID_GREATER_THAN_ZERO("wid_greater_than_zero", "仓库编码必须大于零"),
    /**
     * 商品补偿明细不能为空
     */
    STRATEGY_GOODS_NULL("strategy_goods_null", "商品补偿明细不能为空"),
    /**
     * 补发补偿明细不能为空
     */
    STRATEGY_SUPPLY_AGAIN_NULL("strategy_supply_again_null", "补发补偿明细不能为空"),
    /**
     * 非商品补偿信息不能为空
     */
    STRATEGY_NO_GOODS_NULL("strategy_no_goods_null", "非商品补偿信息不能为空"),
    /**
     * 补偿单基础对象为空
     */
    COMPENSATE_ROOT_NULL("compensate_root_null", "补偿单基础对象为空"),
    /**
     * 补偿单聚合根未匹配到对于的保存实现类
     */
    COMPENSATE_SAVE_NULL("compensate_save_null", "补偿单聚合根未匹配到对应的保存实现类"),

    /**
     * 补偿履约聚合根未匹配到对应的处理实现类
     */
    COMPENSATE_DEAL_NULL("compensate_deal_null", "补偿履约聚合根未匹配到对应的处理实现类"),

    /**
     * 补偿单不满足履约处理的条件
     */
    COMPENSATE_DEAL_NO_RIGHT("compensate_deal_no_right", "补偿单不满足履约处理的条件"),

    /**
     * 补偿单聚合根未匹配到对于的保存实现类
     */
    COMPENSATE_STOCK_NULL("compensate_stock_null", "补偿单聚合根未匹配到对应的库存实现类"),
    /**
     * 补偿方式编码在补偿配件信息中不可用
     */
    STRATEGY_CONFIG_NULL("strategy_config_null", "补偿方式编码在补偿配件信息中不可用"),
    /**
     * 补偿商品列表中商品编码不能重复
     */
    PROBLEM_GID_EXIST("problem_gid_exist", "补偿商品列表中商品编码不能重复"),

    /**
     * 补偿单号不能为空
     */
    COID_NULL("coid_null", "补偿单号不能空"),
    /**
     * 补偿单状态值映射失败
     */
    CSTATE_ERROR("cstate_error", "补偿单状态值映射失败"),

    /**
     * 补偿策略类型状态值映射失败
     */
    STRATEGY_ENUM_ERROR("strategy_enum_error", "补偿策略类型状态值映射失败"),

    /**
     * 待审核才支持审批操作
     */
    CHECK_ILLEGAL("check_illegal", "待审核才支持审批操作"),
    /**
     * 当前状态不支持填写备注信息
     */
    NOTE_ILLEGAL("note_illegal", "当前状态不支持填写备注信息"),
    /**
     * 当前状态不支持修改责任类型
     */
    UPDATE_DUTY_ILLEGAL("update_duty_illegal", "当前状态不支持修改责任类型"),
    /**
     * 当前状态不支持修改图片
     */
    UPDATE_FILE_ILLEGAL("update_file_illegal", "当前状态不支持修改图片"),
    /**
     * 当前状态不支持终止操作
     */
    CANCEL_ILLEGAL("cancel_illegal", "当前状态不支持终止操作"),
    /**
     * 当前状态不支持状态变更操作
     */
    CHANGE_ILLEGAL("change_illegal", "当前状态不支持状态变更操作"),
    /**
     * 当前操作人无商城店铺补偿单审核通过的权限,请联系相关人员添加
     */
    NO_AUTHORITY_RIGHT("no_authority_right", "当前操作人无商城店铺补偿单审核通过的权限,请联系相关人员添加"),

    /**
     * 更改归属错误：非商品属性,不能修改归属为供应商
     */
    UPDATE_DUTY_ERROR("update_duty_error", "更改归属错误：非商品属性,不能修改归属为供应商"),

    /**
     * 确认补偿的状态限制
     */
    CONFIRM_ILLEGAL("confirm_illegal", "确认补偿异常，只有待补偿状态或待沟通状态才能执行确认"),

    /**
     * 补偿配置不能为空
     */
    COMPENSATE_CONFIG_NULL("compensate_config_null", "补偿配置不能为空"),

    /**
     * 订单店铺非线下店铺
     */
    SHOP_NOT_OFFLINE("shop_not_offline", "订单店铺非线下店铺"),
    /**
     * 售后补偿仓库应设置唯一
     */
    WID_MUST_SINGLE("wid_must_single", "售后补偿仓库应设置唯一");


    private final String errorCode;

    private final String errorMessage;

    CompensateFailEnum(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}