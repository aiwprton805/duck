package com.magistr.duck.configuration;

import com.magistr.duck.dao.mybatis.*;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfiguration {

    @Bean
    public CommentMybatisDao commentMybatisDao(SqlSessionFactory sqlSessionFactory) {
        var dao = new CommentMybatisDao();
        dao.setSqlSessionFactory(sqlSessionFactory);
        return dao;
    }

    @Bean
    public ProfileGroupMybatisDao profileGroupMybatisDao(SqlSessionFactory sqlSessionFactory) {
        var dao = new ProfileGroupMybatisDao();
        dao.setSqlSessionFactory(sqlSessionFactory);
        return dao;
    }

    @Bean
    public ProfileMybatisDao profileMybatisDao(SqlSessionFactory sqlSessionFactory) {
        var dao = new ProfileMybatisDao();
        dao.setSqlSessionFactory(sqlSessionFactory);
        return dao;
    }

    @Bean
    public ProposalMybatisDao proposalMybatisDao(SqlSessionFactory sqlSessionFactory) {
        var dao = new ProposalMybatisDao();
        dao.setSqlSessionFactory(sqlSessionFactory);
        return dao;
    }

    @Bean
    public TermGroupMybatisDao termGroupMybatisDao(SqlSessionFactory sqlSessionFactory) {
        var dao = new TermGroupMybatisDao();
        dao.setSqlSessionFactory(sqlSessionFactory);
        return dao;
    }

    @Bean
    public TermMybatisDao termMybatisDao(SqlSessionFactory sqlSessionFactory) {
        var dao = new TermMybatisDao();
        dao.setSqlSessionFactory(sqlSessionFactory);
        return dao;
    }

    @Bean
    public TokenMybatisDao tokenMybatisDao(SqlSessionFactory sqlSessionFactory) {
        var dao = new TokenMybatisDao();
        dao.setSqlSessionFactory(sqlSessionFactory);
        return dao;
    }

    @Bean
    public UserMybatisDao userMybatisDao(SqlSessionFactory sqlSessionFactory) {
        var dao = new UserMybatisDao();
        dao.setSqlSessionFactory(sqlSessionFactory);
        return dao;
    }

    @Bean
    public UserProfileMybatisDao userProfileMybatisDao(SqlSessionFactory sqlSessionFactory) {
        var dao = new UserProfileMybatisDao();
        dao.setSqlSessionFactory(sqlSessionFactory);
        return dao;
    }

    @Bean
    public LectorProfileMybatisDao lectorProfileMybatisDao(SqlSessionFactory sqlSessionFactory) {
        var dao = new LectorProfileMybatisDao();
        dao.setSqlSessionFactory(sqlSessionFactory);
        return dao;
    }
}
