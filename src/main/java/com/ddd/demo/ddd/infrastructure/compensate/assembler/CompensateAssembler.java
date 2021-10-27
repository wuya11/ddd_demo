package com.ddd.demo.ddd.infrastructure.compensate.assembler;

import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateBillA;
import com.ddd.demo.ddd.domain.compensate.entity.StrategyGoodsE;
import com.ddd.demo.ddd.domain.compensate.entity.StrategySupplyAgainE;
import com.ddd.demo.ddd.domain.compensate.vo.StrategyNoGoodsV;
import com.ddd.demo.ddd.infrastructure.compensate.database.CompensateDo;
import com.ddd.demo.ddd.userinterfaces.compensate.command.CompensateBillCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.command.strategy.StrategyGoodsCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.command.strategy.StrategyNoGoodsCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.command.strategy.StrategySupplyAgainCommand;
import com.ddd.demo.ddd.userinterfaces.compensate.dto.CompensateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


/**
 * 售后补偿数据转换工具类
 *
 * @author wl
 * @date 2021/8/26
 */

@Mapper(componentModel = "spring", uses = CompensateConvert.class)
public interface CompensateAssembler {

    /**
     * 补偿单命令转换为聚合根
     *
     * @param compensateBillCommand 补偿单场景命令
     * @return 补偿单聚合根主实体对象
     */
    @Mapping(source = "dutytype", target = "dutytypeEnum")
    @Mapping(source = "repayway", target = "repaywayEnum")
    @Mapping(source = "repayAttribute", target = "replayAttributeEnum")
    CompensateBillA dtoToCompensateBillA(CompensateBillCommand compensateBillCommand);

    /**
     * 商品补偿命令转换为商品补偿实体
     *
     * @param strategyGoodsCommandList 商品补偿创建命令
     * @return 商品补偿实体
     */
    List<StrategyGoodsE> dtoToStrategyGoodsE(List<StrategyGoodsCommand> strategyGoodsCommandList);

    /**
     * 补发补偿命令转换为补发补偿实体
     *
     * @param strategySupplyAgainDtoList 补发补偿创建命令
     * @return 补偿补发实体
     */
    List<StrategySupplyAgainE> dtoToStrategySupplyAgainE(List<StrategySupplyAgainCommand> strategySupplyAgainDtoList);

    /**
     * 非商品补偿命令转换为非商品补偿值对象
     *
     * @param strategyNoGoodsDto 非商品补偿创建命令
     * @return 非商品补偿值对象
     */
    StrategyNoGoodsV dtoToStrategyNoGoodsV(StrategyNoGoodsCommand strategyNoGoodsDto);

    /**
     * 补偿单数据库对象转换为补偿单聚合根对象
     *
     * @param compensateDo 补偿单数据库对象
     * @return 补偿单聚合根对象
     */
    @Mapping(source = "dutytype", target = "dutytypeEnum")
    @Mapping(source = "repayway", target = "repaywayEnum")
    @Mapping(source = "cstate", target = "cstateEnum")
    @Mapping(source = "creatorType", target = "creatorTypeEnum")
    @Mapping(source = "strategyType", target = "strategyEnum")
    @Mapping(source = "repayAttribute", target = "replayAttributeEnum")
    @Mapping(source = "coid", target = "compensateBillId.coid")
    @Mapping(source = "shopId", target = "orderV.shopId")
    @Mapping(source = "cuid", target = "orderV.acId")
    CompensateBillA dbToCompensateBillA(CompensateDo compensateDo);

    /**
     * 补偿单聚合根对象转换为补偿单数据库对象
     *
     * @param compensateBillA 补偿单聚合根
     * @return 补偿单数据库对象
     */
    @Mapping(target = "coid", source = "compensateBillId.coid")
    @Mapping(target = "dutytype", source = "dutytypeEnum.type")
    @Mapping(target = "repayway", source = "repaywayEnum.type")
    @Mapping(target = "creatorType", source = "creatorTypeEnum.type")
    @Mapping(target = "repayAttribute", source = "replayAttributeEnum.type")
    @Mapping(target = "strategyType", source = "strategyEnum.type")
    @Mapping(target = "shopId", source = "orderV.shopId")
    @Mapping(target = "cuid", source = "orderV.acId")
    CompensateDo entityToCompensate(CompensateBillA compensateBillA);

    /**
     * 补偿单聚合根对象转换为补偿单dto
     *
     * @param compensateBillA 补偿单聚合根
     * @return 补偿单dto
     */
    @Mapping(target = "coid", source = "compensateBillId.coid")
    @Mapping(target = "dutytype", source = "dutytypeEnum.type")
    @Mapping(target = "dutytypeText", source = "dutytypeEnum.text")
    @Mapping(target = "repayway", source = "repaywayEnum.type")
    @Mapping(target = "repaywayText", source = "repaywayEnum.text")
    @Mapping(target = "creatorType", source = "creatorTypeEnum.type")
    @Mapping(target = "creatorTypeText", source = "creatorTypeEnum.text")
    @Mapping(target = "repayAttribute", source = "replayAttributeEnum.type")
    @Mapping(target = "repayAttributeText", source = "replayAttributeEnum.text")
    @Mapping(target = "strategyType", source = "strategyEnum.type")
    @Mapping(target = "strategyTypeText", source = "strategyEnum.text")
    @Mapping(target = "shopId", source = "orderV.shopId")
    @Mapping(target = "cuid", source = "orderV.acId")
    CompensateDto entityToCompensateDto(CompensateBillA compensateBillA);


}














