package com.magistr.duck.service;

import java.util.List;

import com.magistr.duck.common.enums.Lang;
import com.magistr.duck.entity.Term;
import com.magistr.duck.entity.TermInfo;

public interface TermService {

    Term getTerm(Integer id, Lang lang);

    Term getTerm(String name, Lang lang);

    TermInfo getTermInfo(Term term);

    TermInfo getTermInfo(Integer termId, Lang lang);

    TermInfo getTermInfo(String termName, Lang lang);

    List<Term> getTerms(Lang lang);

    void saveTerm(Term term);

    void saveTermInfo(TermInfo termInfo);

    void clearTermInfo(TermInfo termInfo);

    void removeTerm(Term term);

    void removeTerm(Integer id, Lang lang);
}
