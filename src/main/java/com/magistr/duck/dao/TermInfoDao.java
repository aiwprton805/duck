package com.magistr.duck.dao;

import com.magistr.duck.common.enums.Lang;
import com.magistr.duck.entity.Term;
import com.magistr.duck.entity.TermInfo;

public interface TermInfoDao extends CrudDao<Integer, TermInfo> {

    void createCommonInfo(TermInfo.TermCommonInfo commonInfo);

    TermInfo findByTermId(Integer termId, Lang lang);

    TermInfo findByTermName(String termName, Lang lang);

    default TermInfo findByTerm(Term term) {
        if (term == null)
            throw new IllegalArgumentException("Параметр term не должен быть null");
        if (term.getLang() == null)
            throw new IllegalArgumentException("Параметр term должен содержать lang отличное от null");
        if (term.getId() != null) {
            return findByTermId(term.getId(), term.getLang());
        } else if (term.getName() != null) {
            return findByTermName(term.getName(), term.getLang());
        }
        throw new IllegalArgumentException("Параметр term должен содержать id или name отличное от null");
    }
}
