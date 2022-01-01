package com.phase2.webAPI.repositories;

import com.phase2.webAPI.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
