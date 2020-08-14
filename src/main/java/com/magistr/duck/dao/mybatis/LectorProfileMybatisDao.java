package com.magistr.duck.dao.mybatis;

import com.magistr.duck.dao.LectorProfileDao;
import com.magistr.duck.dto.LectorProfile;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class LectorProfileMybatisDao extends SqlSessionDaoSupport implements LectorProfileDao {

    private static final String MAPPER_NAMESPACE = "com.magistr.duck.dao.LectorProfileDao";

    @Override
    public List<LectorProfile> findAll() {
        return getSqlSession().selectList(MAPPER_NAMESPACE + ".findAll");
    }
}
