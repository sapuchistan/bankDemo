package com.example.demo.core.movement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovementRepo
    extends JpaRepository<Movement, Long>
{

  @Query("SELECT mov FROM Movement mov WHERE mov.account.accountNumber.accountNumberId=:accountNumber ")
  public Page<Movement> findByAccountNumber(@Param("accountNumber") Long accountNumber, Pageable pageable);
}
