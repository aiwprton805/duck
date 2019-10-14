package com.magistr.duck.dao.mybatis;

import com.magistr.duck.dao.UserProfileDao;
import com.magistr.duck.dto.UserProfile;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class UserProfileMybatisDao extends SqlSessionDaoSupport implements UserProfileDao {

    private static final String MAPPER_NAMESPACE = "com.magistr.duck.dao.UserProfileDao";

    @Override
    public List<UserProfile> findByRoleName(String roleName) {
        if(!roleName.equals("admin") && !roleName.equals("lector") && !roleName.equals("student") && !roleName.equals("guest")){
            throw new IllegalArgumentException("Argument roleName must have one of the following values: admin, lector, student, guest.");
        }
        return getSqlSession().selectList(MAPPER_NAMESPACE + ".findByRoleName", roleName);
    }

    @Override
    public List<UserProfile> findAll() {
        return getSqlSession().selectList(MAPPER_NAMESPACE + ".findAll");
    }
}
