/**
 * @author wangling
 * @date 2021-8-24
 * <p>
 * DDD: infrastructure 基础实施层
 * 向其他层提供 通用的 技术能力(比如工具类,第三方库类支持,常用基本配置,数据访问底层实现)
 * 基础实施层主要包含以下的内容:
 * 为应用层 传递消息(比如通知)
 * 为应用层 提供持久化机制(最底层的实现)
 * <p>
 * 防腐层(acl):   实体对接外部系统，实体与外部系统之间，不同领域之间，不同的参数转换，语义转换等
 * 转换层(assembler):    数据转换工具类，如Dto转换为实体，实体转换为数据表pojo对象，基于org.mapstruct.Mapper实现
 * 仓库层(repository):   仓库实现层，实体与DB之间存储的功能层
 * 异常管理(exception):   封装具体业务的异常处理信息
 * 配置模块(config):      封装配置信息，包括一些基础静态字段，基于阿波罗等获取的配置信息
 * 枚举模块(enum):      封装该模块的枚举信息
 * 数据库映射的基础数据对象(database):  命名规则为：XxxDO,数据表翻译为java基础的pojo对象
 */
package com.ddd.demo.ddd.infrastructure;