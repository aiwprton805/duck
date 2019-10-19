package com.magistr.duck.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.magistr.duck.common.enums.TermStatus;
import com.magistr.duck.entity.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magistr.duck.common.enums.Lang;
import com.magistr.duck.dao.TermDao;
import com.magistr.duck.dao.TermGroupDao;
import com.magistr.duck.entity.Term;
import com.magistr.duck.entity.TermGroup;
import com.magistr.duck.service.TermService;

@Service
public class TermServiceImpl implements TermService {

    private final static Logger LOGGER = LoggerFactory.getLogger(TermServiceImpl.class);

    private final static Integer MAX_TERM_GROUPS_SIZE = 7;

    private final TermDao termDao;

    private final TermGroupDao termGroupDao;

    @Autowired
    public TermServiceImpl(TermDao termDao, TermGroupDao termGroupDao) {
        this.termDao = termDao;
        this.termGroupDao = termGroupDao;
    }

    @Override
    public Optional<Term> getTerm(Integer id) {
        return termDao.read(id);
    }

    @Override
    public Optional<Term> getTerm(String name, Lang lang) {
        return termDao.findByNameAndLang(name, lang);
    }

    @Override
    public Optional<Term> getTerm(Term term) {
        if (term.getId() != null) {
            return termDao.read(term.getId());
        } else if (term.getName() != null && term.getLang() != null) {
            return termDao.findByNameAndLang(term.getName(), term.getLang());
        }
        return Optional.empty();
    }

    @Override
    public List<Term> getTerms() {
        return termDao.findAll();
    }

    @Override
    public List<Term> getTerms(TermStatus status) {
        return termDao.findByTermStatus(status);
    }

    @Override
    public List<Term> getTerms(Profile profile) {
        return termDao.findByProfileId(profile.getId());
    }

    @Override
    public List<Term> getTerms(Profile profile, TermStatus status) {
        return termDao.findByProfileIdAndTermStatus(profile.getId(), status);
    }

    @Override
    public List<Term> getTerms(Profile profile, Lang lang) {
        return termDao.findByProfileIdAndLang(profile.getId(), lang);
    }

    @Override
    public List<Term> getTerms(Integer profileId, TermStatus status, Lang lang) {
        if(profileId == null || status == null || lang == null){
            throw new IllegalArgumentException("Arguments must been not null. profileId = " + profileId + " status = " + status + " lang = " + lang);
        }
        return termDao.findByProfileIdAndStatusAndLang(profileId, status, lang);
    }

    @Override
    public List<Term> getTerms(Profile profile, TermStatus status, Lang lang) {
        if(profile == null){
            throw new IllegalArgumentException("Argument profile must been not null.");
        }
        return getTerms(profile.getId(), status, lang);
    }

    @Override
    public Optional<TermGroup> getTermGroup(Integer id) {
        return termGroupDao.read(id);
    }

    @Override
    public Optional<TermGroup> getTermGroup(Term term) {
        if (term.getTermGroupId() != null) {
            return termGroupDao.findByTermGroupId(term.getTermGroupId());
        }
        return Optional.empty();
    }

    @Override
    public Optional<TermGroup> getTermGroup(TermGroup termGroup) {
        if (termGroup.getId() != null) {
            return termGroupDao.read(termGroup.getId());
        }
        return Optional.empty();
    }

    @Override
    public List<TermGroup> getTermGroups() {
        return termGroupDao.findAll();
    }

    @Override
    public List<TermGroup> getLastCompletedTermGroups() {
        return termGroupDao.findLastCompletedGroups(MAX_TERM_GROUPS_SIZE);
    }

    @Override
    public void saveTerm(Term term) {
        if (term.getId() == null) {
            termDao.create(term);
        } else {
            termDao.update(term);
        }
    }

    @Override
    public void saveTermGroup(TermGroup termGroup) {
        if (termGroup.getId() == null) {
            termGroupDao.create(termGroup);
        } else {
            termGroupDao.update(termGroup);
        }
    }

    @Override
    public void removeTerm(Integer id) {
        termDao.delete(id);
    }

    @Override
    public void removeTerm(Term term) {
        if (term.getId() != null) {
            termDao.delete(term.getId());
        } else {
            LOGGER.debug("TERM {} NOT BEEN REMOVED", term);
        }
    }

    @Override
    public void removeTermGroup(Integer id) {
        termGroupDao.delete(id);
    }

    @Override
    public void removeTermGroup(TermGroup termGroup) {
        if (termGroup.getId() != null) {
            termGroupDao.delete(termGroup.getId());
        } else {
            LOGGER.debug("TERM GROUP {} NOT BEEN REMOVED", termGroup);
        }
    }

    @Override
    public Map<String, Term> termGroupToTermMap(TermGroup termGroup) {
        return termGroup.getTerms().stream().collect(Collectors.toMap(t -> t.getLang().name(), t -> t));
    }
}
//class
