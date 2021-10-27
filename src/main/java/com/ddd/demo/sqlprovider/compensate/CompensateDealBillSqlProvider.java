package com.ddd.demo.sqlprovider.compensate;

import com.ddd.demo.ddd.infrastructure.compensate.database.CompensateDealLogDo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * 售后补偿履约单sql管理
 *
 * @author wl
 * @date 2021-10-19
 */
public class CompensateDealBillSqlProvider {

    /**
     * 生成新增售后补偿履约sql
     *
     * @param compensateDealLogDo 售后补偿履约信息
     * @return 生成新增售后补偿履约sql
     */
    public String addCompensateDealLogSql(CompensateDealLogDo compensateDealLogDo) {
        return new SQL()
                .INSERT_INTO("as_compensate_deal")
                .INTO_COLUMNS("coid", "return_id", "deal_state", "creator", "msg", "send_deal_json")
                .INTO_VALUES("#{coid}", "#{returnId}", "#{dealState}", "#{creator}", "#{msg}", "#{sendDealJson}")
                .toString();
    }

    /**
     * 获取补偿履约单信息
     *
     * @param dealId 补偿履约单号
     * @return 获取补偿履约单信息sql
     */
    public String getCompensateDealLogSql(@Param("dealId") int dealId) {
        return new SQL()
                .SELECT("coid,return_id,deal_id,deal_state,creator,msg,send_deal_json")
                .FROM("as_compensate_deal")
                .WHERE("deal_id=#{dealId}")
                .toString();
    }

    /**
     * 更改补偿单状态
     *
     * @param compensateDealLogDo 履约单数据表对象
     */
    public String updateSql(CompensateDealLogDo compensateDealLogDo) {
        return new SQL()
                .UPDATE("as_compensate_deal")
                .SET("deal_state=#{dealState}")
                .SET("msg=#{msg}")
                .WHERE("deal_id=#{dealId}")
                .toString();
    }
}
