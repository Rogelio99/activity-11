package com.activity11.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.activity11.models.Country;
import java.util.List;


public interface CountryDAO extends JpaRepository<Country, Integer> {
    @Query("SELECT c FROM Country c WHERE c.name LIKE %?1%")
    List<Country> findByName(String name);
}

