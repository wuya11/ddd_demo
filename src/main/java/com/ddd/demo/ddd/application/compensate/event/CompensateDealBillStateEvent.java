package com.ddd.demo.ddd.application.compensate.event;

import org.springframework.context.ApplicationEvent;

/**
 * 补偿履约单状态变更
 *
 * @author wl
 * @date 2021-9-7
 */
public class CompensateDealBillStateEvent extends ApplicationEvent {

    public CompensateDealBillStateEvent(CompensateDealBillStateModify compensateDealBillStateModify) {
        super(compensateDealBillStateModify);
    }
}
