package com.magistr.duck.dao.mybatis;

import com.magistr.duck.dao.ProposalDao;
import com.magistr.duck.entity.Proposal;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;
import java.util.Optional;

public class ProposalMybatisDao extends SqlSessionDaoSupport implements ProposalDao {

    private static final String MAPPER_NAMESPACE = "com.magistr.duck.dao.ProposalDao";

    @Override
    public void create(Proposal proposal) {
        getSqlSession().insert(MAPPER_NAMESPACE + ".create", proposal);
    }

    @Override
    public Optional<Proposal> read(Integer id) {
        return Optional.ofNullable(getSqlSession().selectOne(MAPPER_NAMESPACE + ".read", id));
    }

    @Override
    public void update(Proposal proposal) {
        getSqlSession().update(MAPPER_NAMESPACE + ".update", proposal);
    }

    @Override
    public void delete(Integer id) {
        getSqlSession().delete(MAPPER_NAMESPACE + ".delete", id);
    }

    @Override
    public List<Proposal> findByAnyName(String name) {
        return getSqlSession().selectList(MAPPER_NAMESPACE + ".findByAnyName", name);
    }

    @Override
    public List<Proposal> findByTermName(String termName) {
        return getSqlSession().selectList(MAPPER_NAMESPACE + ".findByTermName", termName);
    }

    @Override
    public List<Proposal> findAll() {
        return getSqlSession().selectList(MAPPER_NAMESPACE + ".findAll");
    }
}
//class
