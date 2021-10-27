/**
 * @author wangling
 * @date 2021-8-24
 * <p>
 * DDD: domain 领域层
 * 领域层主要负责表达业务概念,业务状态信息和业务规则。是一个纯内存化的操作。
 * Domain层是整个系统的核心层,几乎全部的业务逻辑会在该层实现。领域层不关注数据是如何落地存储的，领域层也不直接调用底层仓库接口保存数据。
 * 领域模型层主要包含以下的内容:
 *
 * 实体(Entities):  对象命名为XxxE，具有唯一标识的对象,所有实体统一用E作为后缀，如personE
 * 工厂(factory):   接口命名规则为XxxFactory，创建复杂的实体，聚合根，只做创建处理
 * 值对象(vo):      对象命名为XxxV，无需唯一标识的对象,所有值对像统一同V作为后缀  如personV,实体的主键编码以Id结尾
 * 领域服务(Domain Services):    接口命名规则为XxxDomainService，一些行为无法归类到实体对象或值对象上,本质是一些操作,而非事物(与本例中domain/service包下的含义不同)
 * 仓储(Repository):    接口命名规则为XxxRepository，创建复杂对象,隐藏创建细节,提供查找和持久化对象的方法。本层仅编写仓库的接口。具体实现再基础层
 * 聚合/聚合根(Aggregates,Aggregate Roots):    对象命名为XxxA，聚合是指一组具有内聚关系的相关对象的集合,每个聚合都有一个root和boundary,所有聚合统一用A作为后缀，如personA
 *
 */
package com.ddd.demo.ddd.domain;