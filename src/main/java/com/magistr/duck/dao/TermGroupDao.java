package com.magistr.duck.dao;

import java.util.List;
import java.util.Optional;

import com.magistr.duck.entity.TermGroup;

public interface TermGroupDao extends CrudDao<Integer, TermGroup> {

    Optional<TermGroup> findByTermGroupId(Integer termGroupId);

    List<TermGroup> findAll();

    List<TermGroup> findLastCompletedGroups(Integer limit);
}
