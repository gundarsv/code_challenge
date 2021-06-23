package com.code.challenge.persistence.repository;

import com.code.challenge.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {
}
