package com.nextalien.accounts.repository;

import com.nextalien.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Long>{

   Accounts findByCustomerId(Long customerId);
}
