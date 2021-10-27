package com.ddd.demo.ddd.domain.compensate.service.handler.save;

import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateBillA;
import com.ddd.demo.ddd.infrastructure.compensate.enums.RepaywayEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDateTime;

/**
 * 补偿单聚合根抽象类
 * 补偿单存在三种策略，抽象类主要是统一处理一些业务或功能相同的点
 *
 * @author wl
 * @date 2021-8-30
 */
public abstract class AbstractCompensateSave implements CompensateSaveHandler {



    /**
     * 售后补偿聚合根-加工
     *
     * @param compensateBillA 售后补偿聚合根
     */
    protected CompensateBillA processCompensateBill(CompensateBillA compensateBillA) {
        // 特殊处理字符串
//        String imgids = compensateBillAr.getImgids();
//        if (StringUtils.isNotBlank(imgids)) {
//            String[] imges = imgids.split(",");
//            String imgPath = "";
//            for (String img : imges) {
//                String temp = "\"" + img + "\",";
//                imgPath += temp;
//            }
//            imgPath = "[" + imgPath.substring(0, imgPath.length() - 1) + "]";
//            compensateBillAr.setImgids(imgPath);
//        }
        // 逻辑加工处理其他信息
        compensateBillA.setCreator(compensateBillA.getActuid());
        compensateBillA.setApplyTime(LocalDateTime.now());
        if(StringUtils.isBlank(compensateBillA.getDescription())){
            compensateBillA.setDescription(Strings.EMPTY);
        }
        if(StringUtils.isBlank(compensateBillA.getImgids())){
            compensateBillA.setImgids(Strings.EMPTY);
        }
        return compensateBillA;
    }

    /**
     * 验证补偿配置是否可用
     *
     * @param repaywayEnum 补偿方式枚举
     * @param shopId       店铺编码
     * @return 验证结果
     */
    protected boolean checkCompensateConfig(RepaywayEnum repaywayEnum, int shopId) {
        // 补偿方式是否存在配置文件
//        CompensateConfig compensateConfig = compensateConfigService.checkCompensateConfig(repaywayEnum.getType(), shopId);
//        if (Objects.isNull(compensateConfig)) {
//            throw new CompensateException(CompensateFailEnum.STRATEGY_CONFIG_NULL);
//        }
        return true;
    }

}

