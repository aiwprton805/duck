package com.magistr.duck.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.magistr.duck.common.enums.Lang;
import com.magistr.duck.common.enums.TermStatus;
import com.magistr.duck.entity.Profile;
import com.magistr.duck.entity.Term;
import com.magistr.duck.entity.TermGroup;

public interface TermService {

    Optional<Term> getTerm(Integer id);

    Optional<Term> getTerm(String name, Lang lang);

    Optional<Term> getTerm(Term term);

    Optional<TermGroup> getTermGroup(Integer id);

    Optional<TermGroup> getTermGroup(Term term);

    Optional<TermGroup> getTermGroup(TermGroup termGroup);

    void saveTerm(Term term);

    void saveTermGroup(TermGroup termGroup);

    void removeTerm(Integer id);

    void removeTerm(Term term);

    void removeTermGroup(Integer id);

    void removeTermGroup(TermGroup termGroup);

    List<Term> getTerms();

    List<Term> getTerms(TermStatus status);

    List<Term> getTerms(Profile profile);

    List<Term> getTerms(Profile profile, TermStatus status);

    List<Term> getTerms(Profile profile, Lang lang);

    List<Term> getTerms(Integer profileId, TermStatus status, Lang lang);

    List<Term> getTerms(Profile profile, TermStatus status, Lang lang);

    List<TermGroup> getTermGroups();

    List<TermGroup> getLastCompletedTermGroups();

    Map<String, Term> termGroupToTermMap(TermGroup termGroup);
}
