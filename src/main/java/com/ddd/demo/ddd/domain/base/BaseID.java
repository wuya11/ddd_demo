package com.ddd.demo.ddd.domain.base;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * 实体主键编码的基础值对象
 * 应用于实体主键编码 如 compensateId extends BaseID
 *
 * @author wangling
 * @date 2021-09-26
 */

@Data
public class BaseID implements Serializable {

    /**
     * 主键id
     */
    private String uuid = UUID.randomUUID().toString();
}
