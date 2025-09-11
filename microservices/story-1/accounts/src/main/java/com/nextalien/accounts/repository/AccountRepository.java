package com.nextalien.accounts.repository;

import com.nextalien.accounts.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Long>{

   Optional<Accounts> findByCustomerId(Long customerId);

   @Transactional
   @Modifying
   void deleteByCustomerId(Long customerId);

//    @Transactional
//    @Modifying
//    @Query("DELETE FROM Accounts a WHERE a.customerId = ?1")
//    void deleteByCustomerId(Long customerId);
}
