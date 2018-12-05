package com.magistr.duck.dao.mybatis;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.magistr.duck.common.enums.Lang;
import com.magistr.duck.dao.TermDao;
import com.magistr.duck.entity.Term;

public class TermMybatisDao extends SqlSessionDaoSupport implements TermDao {

    private static final String MAPPER_NAMESPACE = "com.magistr.duck.dao.mybatis.mappers.TermMapper";

    @Override
    public void create(Term term) {
        getSqlSession().insert(MAPPER_NAMESPACE + ".create", term);
    }

    @Deprecated
    @Override
    public Term read(Integer id) {
        throw new UnsupportedOperationException(
                "Для однозначного извлечения информации требуется использовать метод read(Integer id, Lang lang).");
    }

    @Override
    public Term read(Integer id, Lang lang) {
        return getSqlSession().selectOne(MAPPER_NAMESPACE + ".read", Map.<String, Object>of("id", id, "lang", lang));
    }

    @Override
    public void update(Term term) {
        getSqlSession().insert(MAPPER_NAMESPACE + ".update", term);
    }

    @Deprecated
    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException(
                "Для однозначного извлечения информации требуется использовать метод delete(Integer id, Lang lang).");
    }

    @Override
    public void createTranslation(Term translationTerm) {
        getSqlSession().insert(MAPPER_NAMESPACE + ".createTranslation", translationTerm);
    }

    @Override
    public void delete(Integer id, Lang lang) {
        getSqlSession().delete(MAPPER_NAMESPACE + ".delete", Map.<String, Object>of("id", id, "lang", lang));
    }

    @Override
    public Term findByName(String name, Lang lang) {
        return getSqlSession().selectOne(MAPPER_NAMESPACE + ".findByName",
                Map.<String, Object>of("name", name, "lang", lang));
    }

    @Override
    public List<Term> findAll(Lang lang) {
        return getSqlSession().selectList(MAPPER_NAMESPACE + ".findAll", lang);
    }

}
