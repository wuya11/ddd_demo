package com.ddd.demo.mapper.compensate;

import com.ddd.demo.ddd.infrastructure.compensate.database.CompensateDo;
import com.ddd.demo.ddd.userinterfaces.compensate.query.CompensateBillQuery;
import com.ddd.demo.sqlprovider.compensate.CompensateBillSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 售后补偿单数据层mapper接口
 *
 * @author wl
 * @date 2021-10-18
 */
@Mapper
@Repository
public interface CompensateBillMapper {


    /**
     * 新增售后补偿单信息
     *
     * @param compensateDo 售后补偿单对象
     */
    @InsertProvider(type = CompensateBillSqlProvider.class, method = "addCompensateSql")
    @Options(useGeneratedKeys = true, keyProperty = "coid", keyColumn = "coid")
    void add(CompensateDo compensateDo);

    /**
     * 获取补偿单信息
     *
     * @param coid 补偿单号
     * @return 补偿单基础信息
     */
    @SelectProvider(type = CompensateBillSqlProvider.class, method = "getCompensateSql")
    CompensateDo getCompensate(@Param("coid") long coid);

    /**
     * 更改补偿单状态
     *
     * @param coid 补偿单号
     * @param state 补偿单状态
     */
    @UpdateProvider(type = CompensateBillSqlProvider.class, method = "updateCompensateStateSql")
    void updateCompensateState(@Param("coid") long coid, @Param("state") int state);

    /**
     * 获取补偿单信息
     *
     * @param query 查询条件
     * @return 补偿单基础信息
     */
    @SelectProvider(type = CompensateBillSqlProvider.class, method = "listCompensateDoSql")
    List<CompensateDo> listCompensateDo(@Param("query") CompensateBillQuery query);

}
