package com.magistr.duck.dao.mybatis;

import com.magistr.duck.common.enums.Lang;
import com.magistr.duck.common.enums.TermStatus;
import com.magistr.duck.dao.TermDao;
import com.magistr.duck.entity.Term;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TermMybatisDao extends SqlSessionDaoSupport implements TermDao {

    private static final String MAPPER_NAMESPACE = "com.magistr.duck.dao.TermDao";

    @Override
    public void create(Term term) {
        getSqlSession().insert(MAPPER_NAMESPACE + ".create", term);
    }

    @Override
    public Optional<Term> read(Integer id) {
        return Optional.ofNullable(getSqlSession().selectOne(MAPPER_NAMESPACE + ".read", id));
    }

    @Override
    public void update(Term term) {
        getSqlSession().update(MAPPER_NAMESPACE + ".update", term);
    }

    @Override
    public void delete(Integer id) {
        getSqlSession().delete(MAPPER_NAMESPACE + ".delete", id);
    }

    @Override
    public Optional<Term> findByNameAndLang(String name, Lang lang) {
        Integer langId = lang.ordinal();
        return Optional.ofNullable(getSqlSession().selectOne(MAPPER_NAMESPACE + ".findByNameAndLang",
                Map.of("name", name, "langId", langId)));
    }

    @Override
    public List<Term> findByTermStatus(TermStatus status) {
        Integer statusId = status.ordinal();
        return getSqlSession().selectList(MAPPER_NAMESPACE + ".findByTermStatus", statusId);
    }

    @Override
    public List<Term> findByProfileId(Integer profileId) {
        return getSqlSession().selectList(MAPPER_NAMESPACE + ".findByProfileId", profileId);
    }

    @Override
    public List<Term> findByProfileIdAndTermStatus(Integer profileId, TermStatus status) {
        Integer statusId = status.ordinal();
        return getSqlSession().selectList(MAPPER_NAMESPACE + ".findByProfileIdAndTermStatus",
                Map.of("profileId", profileId, "statusId", statusId));
    }

    @Override
    public List<Term> findByProfileIdAndLang(Integer profileId, Lang lang) {
        Integer langId = lang.ordinal();
        return getSqlSession().selectList(MAPPER_NAMESPACE + ".findByProfileIdAndLang",
                Map.of("profileId", profileId, "langId", langId));
    }

    @Override
    public List<Term> findAll() {
        return getSqlSession().selectList(MAPPER_NAMESPACE + ".findAll");
    }

}
//class
