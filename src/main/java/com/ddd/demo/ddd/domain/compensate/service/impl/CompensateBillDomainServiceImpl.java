package com.ddd.demo.ddd.domain.compensate.service.impl;

import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateBillA;
import com.ddd.demo.ddd.domain.compensate.service.CompensateBillDomainService;
import com.ddd.demo.ddd.domain.compensate.service.handler.check.CompensateCheckHandler;
import com.ddd.demo.ddd.domain.compensate.service.handler.save.CompensateSaveHandler;
import com.ddd.demo.ddd.infrastructure.compensate.enums.CheckTypeEnum;
import com.ddd.demo.ddd.infrastructure.compensate.enums.CstateEnum;
import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateException;
import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateFailEnum;
import com.ddd.demo.ddd.userinterfaces.compensate.command.CompensateCheckCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 补偿单聚合根领域服务-接口层
 * 补偿单聚合根相关行为的核心实现服务
 *
 * @author wl
 * @date 2021-8-30
 */
@Service
public class CompensateBillDomainServiceImpl implements CompensateBillDomainService {

    @Autowired
    private List<CompensateSaveHandler> compensateSaveHandlerList;
    @Autowired
    private List<CompensateCheckHandler> compensateCheckHandlerList;


    @Override
    public CompensateBillA process(CompensateBillA compensateBillA) {
        // 基于策略模式-处理不同的补偿策略
        CompensateSaveHandler compensateSaveHandler = getCompensateSaveHandler(compensateBillA);
        compensateSaveHandler.check(compensateBillA);
        return compensateSaveHandler.process(compensateBillA);
    }

    @Override
    public boolean check(CompensateBillA compensateBillA, CheckTypeEnum checkTypeEnum, CompensateCheckCommand compensateCheckCommand) {
        compensateBillA.setCreator(compensateCheckCommand.getOperator());
        if (!Objects.equals(compensateBillA.getCstateEnum(), CstateEnum.APPROVAL_PENDING)) {
            throw new CompensateException(CompensateFailEnum.CHECK_ILLEGAL);
        }
        CompensateCheckHandler checkHandler = compensateCheckHandlerList.stream()
                .filter(x -> Objects.equals(x.checkType(), checkTypeEnum))
                .findFirst().orElse(null);
        if (Objects.nonNull(checkHandler)) {
            return checkHandler.canCheck(compensateBillA);
        }
        return false;
    }

    private CompensateSaveHandler getCompensateSaveHandler(CompensateBillA compensateBillA) {
        CompensateSaveHandler result = compensateSaveHandlerList.stream().filter(x -> x.canApply(compensateBillA)).findFirst().orElse(null);
        if (Objects.isNull(result)) {
            throw new CompensateException(CompensateFailEnum.COMPENSATE_SAVE_NULL);
        }
        return result;
    }
}
