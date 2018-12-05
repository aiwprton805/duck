package com.magistr.duck.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magistr.duck.common.enums.Lang;
import com.magistr.duck.dao.TermDao;
import com.magistr.duck.dao.TermInfoDao;
import com.magistr.duck.entity.Term;
import com.magistr.duck.entity.TermInfo;
import com.magistr.duck.service.TermService;

@Service
public class TermServiceImpl implements TermService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private TermDao termDao;

    @Autowired
    private TermInfoDao termInfoDao;

    @Override
    public Term getTerm(Integer id, Lang lang) {
        return termDao.read(id, lang);
    }

    @Override
    public Term getTerm(String name, Lang lang) {
        return termDao.findByName(name, lang);
    }

    @Override
    public TermInfo getTermInfo(Term term) {
        return termInfoDao.findByTerm(term);
    }

    @Override
    public TermInfo getTermInfo(Integer termId, Lang lang) {
        return termInfoDao.findByTermId(termId, lang);
    }

    @Override
    public TermInfo getTermInfo(String termName, Lang lang) {
        return termInfoDao.findByTermName(termName, lang);
    }

    @Override
    public List<Term> getTerms(Lang lang) {
        return termDao.findAll(lang);
    }

    @Override
    public void saveTerm(Term term) {
        if (term.getId() == null) {
            termInfoDao.createCommonInfo(term.getTermInfo().getCommonInfo());
            termInfoDao.create(term.getTermInfo());
            termInfoDao.create(term.getTranslation().getTermInfo());
            termDao.createTranslation(term.getTranslation());
            termDao.create(term);
        } else {
            termDao.update(term);
        }
    }

    @Override
    public void saveTermInfo(TermInfo termInfo) {
        if (termInfo.getId() == null) {
            termInfoDao.create(termInfo);
        } else {
            termInfoDao.update(termInfo);
        }
    }

    @Override
    public void clearTermInfo(TermInfo termInfo) {
        termInfo.getCommonInfo().setPicturesDirName(null);
        termInfo.getCommonInfo().setAuthor(null);
        termInfo.setGrammar(null);
        termInfo.setPurview(null);
        termInfo.setDescription(null);
        termInfo.setExamples(null);
        termInfoDao.update(termInfo);
    }

    @Override
    public void removeTerm(Term term) {
        termDao.delete(term.getId(), term.getLang());
    }

    @Override
    public void removeTerm(Integer id, Lang lang) {
        termDao.delete(id, lang);
    }

}
