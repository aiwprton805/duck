package com.magistr.duck.dao;

import com.magistr.duck.dto.LectorProfile;

import java.util.List;

public interface LectorProfileDao {
    List<LectorProfile> findAll();
}
