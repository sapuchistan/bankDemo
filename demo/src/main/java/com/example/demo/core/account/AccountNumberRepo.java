package com.example.demo.core.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountNumberRepo
    extends JpaRepository<AccountNumber, Long>
{

}
