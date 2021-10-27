package com.ddd.demo.ddd.infrastructure.compensate.repository;

import com.ddd.demo.ddd.domain.compensate.aggregate.CompensateDealBillA;
import com.ddd.demo.ddd.domain.compensate.repository.CompensateDealBillRepository;
import com.ddd.demo.ddd.domain.compensate.vo.CompensateBillId;
import com.ddd.demo.ddd.domain.compensate.vo.CompensateDealBillId;
import com.ddd.demo.ddd.infrastructure.compensate.database.CompensateDealLogDo;
import com.ddd.demo.ddd.infrastructure.compensate.enums.CompensateDealBillStateEnum;
import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateException;
import com.ddd.demo.ddd.infrastructure.compensate.exception.CompensateFailEnum;
import com.ddd.demo.mapper.compensate.CompensateDealBillMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 补偿履约单聚合根 - 仓库层
 *
 * @author wl
 * @date 2021-10-01
 */
@Service
public class CompensateDealBillRepositoryImpl implements CompensateDealBillRepository {

    @Autowired
    private CompensateDealBillMapper mapper;


    @Override
    public CompensateDealBillA find(CompensateDealBillId compensateDealBillId) {
        CompensateDealBillA compensateDealBillA = new CompensateDealBillA();
        CompensateDealLogDo compensateDealLogDo = mapper.getCompensateDealLog(compensateDealBillId.getDealId());
        if (Objects.isNull(compensateDealLogDo)) {
            throw new CompensateException(CompensateFailEnum.COMPENSATE_IS_NULL);
        }
        compensateDealBillA.setSendDealJson(compensateDealLogDo.getSendDealJson());
        compensateDealBillA.setReturnId(compensateDealLogDo.getReturnId());
        compensateDealBillA.setActAcid(compensateDealLogDo.getCreator());
        compensateDealBillA.setCompensateDealBillId(compensateDealBillId);
        compensateDealBillA.setCompensateDealBillStateEnum(CompensateDealBillStateEnum.getSafeEnum(compensateDealLogDo.getDealState()));
        CompensateBillId compensateBillId = new CompensateBillId();
        compensateBillId.setCoid(compensateDealLogDo.getCoid());
        compensateDealBillA.setCompensateBillId(compensateBillId);
        return compensateDealBillA;
    }

    @Override
    public CompensateDealBillA save(CompensateDealBillA compensateDealBillA) {
        CompensateDealLogDo compensateDealLogDo = new CompensateDealLogDo();
        compensateDealLogDo.setCoid(compensateDealBillA.getCompensateBillId().getCoid());
        compensateDealLogDo.setCreator(compensateDealBillA.getActAcid());
        compensateDealLogDo.setDealState(compensateDealBillA.getCompensateDealBillStateEnum().getCode());
        compensateDealLogDo.setReturnId(compensateDealBillA.getReturnId());
        compensateDealLogDo.setSendDealJson(compensateDealBillA.getSendDealJson());
        compensateDealLogDo.setMsg(Strings.EMPTY);
        mapper.addCompensateDealLog(compensateDealLogDo);
        CompensateDealBillId compensateDealBillId = new CompensateDealBillId();
        compensateDealBillId.setDealId(compensateDealLogDo.getDealId());
        compensateDealBillA.setCompensateDealBillId(compensateDealBillId);
        return compensateDealBillA;
    }

    @Override
    public void changeDealBill(CompensateDealBillA compensateDealBillA) {
        CompensateDealLogDo compensateDealLogDo = new CompensateDealLogDo();
        compensateDealLogDo.setDealId(compensateDealBillA.getCompensateDealBillId().getDealId());
        compensateDealLogDo.setDealState(compensateDealBillA.getCompensateDealBillStateEnum().getCode());
        compensateDealLogDo.setMsg(StringUtils.isBlank(compensateDealBillA.getMsg()) ? Strings.EMPTY : compensateDealBillA.getMsg());
        mapper.update(compensateDealLogDo);
    }
}












