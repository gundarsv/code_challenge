package com.code.challenge.domain.service;

import com.code.challenge.presentation.dtos.AccountDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface IAccountService {
    ResponseEntity getAccount(Long id);

    ResponseEntity addAccount(AccountDTO accountDTO);

    ResponseEntity deleteAccount(Long id);

    ResponseEntity updateAccount(Long id, AccountDTO accountDTO);

    ResponseEntity getAccounts();
}
