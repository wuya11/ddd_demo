/**
 * @author wangling
 * @date 2021-8-24
 * <p>
 * DDD: application 应用层
 * 相对于领域层,应用层是很薄的一层,应用层定义了软件要完成的任务,要尽量简单.
 * 它不包含任务业务规则或知识, 为下一层的领域对象协助任务、委托工作。这一层也很适合写一些任务处理,消息处理
 * 它没有反映业务情况的状态,但它可以具有反映用户或程序的某个任务的进展状态。
 *
 * 对外:为展现层提供各种应用功能(service)。
 * 对内:调用领域层（领域对象或领域服务）完成各种业务逻辑任务
 *
 * 事件(event):          对象命名为XxxEvent，跨聚合根，或部分业务处理完成后，需要通知其他模块的,本例采用 Spring event模式
 * 应用服务(service):     对象命名为XxxService，应用层的服务
 *
 * 一个应用层通常包括以下三种服务：
 *
 * 业务处理类：XxxCommandService
 * 业务查询类：XxxQueryService
 * 业务事件类：XxxEventService
 **/
package com.ddd.demo.ddd.application;
