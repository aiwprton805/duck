package com.magistr.duck.dao;

import com.magistr.duck.common.enums.ProposalStatus;
import com.magistr.duck.entity.Proposal;

import java.util.List;

public interface ProposalDao extends CrudDao<Integer, Proposal> {

    void deleteRejectedAndProcessed();

    List<Proposal> findByAnyName(String name);

    List<Proposal> findByTermName(String termName);

    List<Proposal> findByStatus(ProposalStatus status);

    List<Proposal> findByLectorId(Integer lectorId);

    List<Proposal> findAll();
}
