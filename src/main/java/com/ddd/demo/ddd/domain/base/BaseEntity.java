package com.ddd.demo.ddd.domain.base;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * 实体基础主键编码
 *
 * @author wangling
 * @date 2021-09-26
 */

@Data
public class BaseEntity implements Serializable {

    /**
     * 唯一键编码
     */
    private String uuid= UUID.randomUUID().toString();
}
