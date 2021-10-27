package com.ddd.demo.sqlprovider.compensate;

import com.ddd.demo.ddd.infrastructure.compensate.database.CompensateDo;
import com.ddd.demo.ddd.userinterfaces.compensate.query.CompensateBillQuery;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.Objects;

/**
 * 售后补偿sql管理
 *
 * @author wl
 * @date 2021-10-15
 */
public class CompensateBillSqlProvider {

    /**
     * 生成新增售后补偿sql
     *
     * @param compensateDo 售后补偿信息
     * @return 生成新增售后补偿sql
     */
    public String addCompensateSql(CompensateDo compensateDo) {
        return new SQL()
                .INSERT_INTO("as_compensate")
                .INTO_COLUMNS("coid", "sub_oid", "reference_money", "compensate_money", "cuid", "shop_id", "repayway", "dutytype", "imgids", "description", "creator_type", "reasonid", "repay_attribute", "actuid", "cstate", "strategy_type", "strategy_json")
                .INTO_VALUES("#{coid}", "#{subOid}", "#{referenceMoney}", "#{compensateMoney}", "#{cuid}", "#{shopId}", "#{repayway}", "#{dutytype}", "#{imgids}", "#{description}", "#{creatorType}", "#{reasonid}", "#{repayAttribute}", "#{actuid}", "#{cstate}", "#{strategyType}", "#{strategyJson}")
                .toString();
    }

    /**
     * 获取补偿单信息
     *
     * @param coid 补偿单号
     * @return 补偿单基础信息
     */
    public String getCompensateSql(@Param("coid") long coid) {
        return new SQL()
                .SELECT("coid,sub_oid,reference_money,compensate_money,cuid,shop_id,repayway,dutytype,imgids,description,creator_type,reasonid,repay_attribute,actuid,cstate,strategy_type,strategy_json")
                .FROM("as_compensate")
                .WHERE("coid=#{coid}")
                .toString();
    }

    /**
     * 更改补偿单状态
     *
     * @param coid  补偿单号
     * @param state 补偿单状态
     */
    public String updateCompensateStateSql(@Param("coid") long coid, @Param("state") int state) {
        return new SQL()
                .UPDATE("as_compensate")
                .SET("cstate=#{state}")
                .WHERE("coid=#{coid}")
                .toString();
    }

    /**
     * 获取补偿单信息
     *
     * @param query 查询条件  --查询条件先不写，主要是判空不会写
     * @return 补偿单基础信息
     */
    public String listCompensateDoSql(@Param("query") CompensateBillQuery query) {
        StringBuilder stringBuffer = new StringBuilder()
                .append("select coid,sub_oid,reference_money,compensate_money,cuid,shop_id,repayway,dutytype,imgids,description,creator_type,reasonid,repay_attribute,actuid,cstate,strategy_type,strategy_json")
                .append(" from as_compensate")
                .append(" where 1=1 ");
        if (Objects.nonNull(query.getCoid())) {
            stringBuffer.append(" and coid=#{query.coid}");
        }
        if (Objects.nonNull(query.getSubOid())) {
            stringBuffer.append(" and sub_oid=#{query.subOid}");
        }
        if (Objects.nonNull(query.getCstate())) {
            stringBuffer.append(" and cstate=#{query.cstate}");
        }
        if (Objects.nonNull(query.getRepayway())) {
            stringBuffer.append(" and repayway=#{query.repayway}");
        }
        return stringBuffer.append(" limit 100").toString();
    }
}
