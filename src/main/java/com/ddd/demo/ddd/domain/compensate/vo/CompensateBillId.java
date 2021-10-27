package com.ddd.demo.ddd.domain.compensate.vo;

import com.ddd.demo.ddd.domain.base.BaseID;
import lombok.Data;

/**
 * 补偿单聚合根唯一编码对象
 *
 * @author wangling
 * @date 2021-8-25
 */
@Data
public class CompensateBillId extends BaseID {

    /**
     * 补偿编号
     */
    private Long coid;
}
