package com.magistr.duck.dao;

import java.util.List;
import java.util.Optional;

import com.magistr.duck.common.enums.Lang;
import com.magistr.duck.common.enums.TermStatus;
import com.magistr.duck.entity.Term;

public interface TermDao extends CrudDao<Integer, Term> {

    Optional<Term> findByNameAndLang(String name, Lang lang);

    List<Term> findByTermStatus(TermStatus status);

    List<Term> findByProfileId(Integer profileId);

    List<Term> findByProfileIdAndTermStatus(Integer profileId, TermStatus status);

    List<Term> findByProfileIdAndLang(Integer profileId, Lang lang);

    List<Term> findAll();
}
