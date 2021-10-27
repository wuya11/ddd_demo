//package com.ddd.demo.ddd.infrastructure.compensate.config;
//
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
///**
// * 售后补偿相关配置信息
// *
// * @author wl
// * @date 2021-8-30
// */
//@Component
//@Data
//@ToString
//@NoArgsConstructor
//public class CompensateConfiguration {
//
//    /**
//     * 售后主管acid
//     */
//    @Value("${aftersale.compensate.manager.acid}")
//    private Long afterSaleManagerAcId;
//
//    /**
//     * 权限acid组
//     */
//    @Value("${aftersale.compensate.authority.acids}")
//    private String[] compensateAuthorityAcid;
//
//    /**
//     * 禁止创建商城店铺补偿单的店铺列表
//     */
//    @Value("${compensate.forbid.shops}")
//    private int[] epetShopIds;
//}
