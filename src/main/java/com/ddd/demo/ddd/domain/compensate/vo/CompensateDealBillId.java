package com.ddd.demo.ddd.domain.compensate.vo;

import com.ddd.demo.ddd.domain.base.BaseID;
import lombok.Data;

/**
 * 履约单聚合根唯一编码对象
 *
 * @author wangling
 * @date 2021-10-12
 */
@Data
public class CompensateDealBillId extends BaseID {

    /**
     * 处理编码
     */
    private Integer dealId;
}
