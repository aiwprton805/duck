package com.magistr.duck.dao;

import java.util.List;

import com.magistr.duck.common.enums.Lang;
import com.magistr.duck.entity.Term;

public interface TermDao extends CrudDao<Integer, Term> {

    void createTranslation(Term translationTerm);

    Term read(Integer id, Lang lang);

    void delete(Integer id, Lang lang);

    Term findByName(String name, Lang lang);

    List<Term> findAll(Lang lang);
}
