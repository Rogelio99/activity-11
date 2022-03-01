package com.activity11.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.activity11.models.Account;

public interface AccountDAO extends JpaRepository<Account, Integer> { 
}
