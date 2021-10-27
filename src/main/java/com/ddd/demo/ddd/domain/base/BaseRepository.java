package com.ddd.demo.ddd.domain.base;

/**
 * 基础仓库接口
 * 仓库的一些通用操作
 *
 * @author wl
 * @date 2021-9-27
 */
public interface BaseRepository<T extends BaseEntity, ID extends BaseID> {

    /**
     * 保存实体
     *
     * @param t 实体
     * @return 数据字典
     */
    default T save(T t) {
        return t;
    }

    /**
     * 查询实体
     *
     * @param keyId 查询主键
     * @return 返回实体
     */
    default T find(ID keyId) {
        return null;
    }

    /**
     * 删除实体
     *
     * @param keyId 查询主键
     */
    default void remove(ID keyId) {
    }

}
