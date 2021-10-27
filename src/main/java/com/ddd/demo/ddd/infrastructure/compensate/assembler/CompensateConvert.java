package com.ddd.demo.ddd.infrastructure.compensate.assembler;


import com.ddd.demo.ddd.infrastructure.compensate.enums.*;

/**
 * 售后补偿数据转换工具类
 *
 * @author wl
 * @date 2021/8/26
 */

public class CompensateConvert {

    public static DutytypeEnum dutytypeEnumConvert(Byte value) {
        return DutytypeEnum.getSafeDutyType(value);
    }

    public static RepaywayEnum repaywayEnumConvert(Byte value) {
        return RepaywayEnum.getSafeRepaywayEnum(value);
    }

    public static ReplayAttributeEnum replayAttributeEnumConvert(Byte value) {
        return ReplayAttributeEnum.getSafeReplayAttributeEnum(value);
    }

    public static CstateEnum cstateEnumConvert(Integer value) {
        return CstateEnum.getCstateEnumByCode(value);
    }

    public static CompensationStrategyEnum compensationStrategyEnumConvert(Byte value) {
        return CompensationStrategyEnum.getSafeStrategyEnum(value);
    }


    public static CreatorTypeEnum creatorTypeEnumConvert(Byte value) {
        return CreatorTypeEnum.getSafeCreatorTypeEnum(value);
    }
}
