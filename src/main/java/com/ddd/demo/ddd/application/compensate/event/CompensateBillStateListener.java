package com.ddd.demo.ddd.application.compensate.event;

import com.ddd.demo.ddd.application.compensate.service.CompensateEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * 起补偿履约单状态变化监听监听
 *
 * @author wl
 * @date 2021-9-6
 */
@Component
public class CompensateBillStateListener {

    @Autowired
    private CompensateEventService compensateEventService;

    @Async
    @TransactionalEventListener(fallbackExecution = true, value = CompensateDealBillStateEvent.class)
    public void onEvent(CompensateDealBillStateEvent compensateDealBillStateEvent) {
        compensateEventService.listenerDealBill(compensateDealBillStateEvent);
    }
}
