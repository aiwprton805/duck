package com.magistr.duck.dao.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.magistr.duck.common.enums.Lang;
import com.magistr.duck.dao.TermInfoDao;
import com.magistr.duck.entity.TermInfo;
import com.magistr.duck.entity.TermInfo.TermCommonInfo;

public class TermInfoMybatisDao extends SqlSessionDaoSupport implements TermInfoDao {

    private static final String MAPPER_NAMESPACE = "com.magistr.duck.dao.mybatis.mappers.TermInfoMapper";

    @Override
    public void create(TermInfo termInfo) {
        getSqlSession().insert(MAPPER_NAMESPACE + ".create", termInfo);
    }

    @Override
    public TermInfo read(Integer id) {
        return getSqlSession().selectOne(MAPPER_NAMESPACE + ".read", id);
    }

    @Override
    public void update(TermInfo termInfo) {
        getSqlSession().update(MAPPER_NAMESPACE + ".update", termInfo);

    }

    @Override
    public void delete(Integer id) {
        getSqlSession().delete(MAPPER_NAMESPACE + ".delete", id);
    }

    @Override
    public void createCommonInfo(TermCommonInfo commonInfo) {
        getSqlSession().insert(MAPPER_NAMESPACE + ".createCommonInfo", commonInfo);
    }

    @Override
    public TermInfo findByTermId(Integer termId, Lang lang) {
        return getSqlSession().selectOne(MAPPER_NAMESPACE + ".findByTermId",
                Map.<String, Object>of("termId", termId, "lang", lang));
    }

    @Override
    public TermInfo findByTermName(String termName, Lang lang) {
        return getSqlSession().selectOne(MAPPER_NAMESPACE + ".findByTermName",
                Map.<String, Object>of("termName", termName, "lang", lang));
    }

}
