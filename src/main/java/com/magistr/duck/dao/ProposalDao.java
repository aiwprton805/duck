package com.magistr.duck.dao;

import com.magistr.duck.entity.Proposal;

import java.util.List;

public interface ProposalDao extends CrudDao<Integer, Proposal> {

    List<Proposal> findByAnyName(String name);

    List<Proposal> findByTermName(String termName);

    List<Proposal> findAll();
}
