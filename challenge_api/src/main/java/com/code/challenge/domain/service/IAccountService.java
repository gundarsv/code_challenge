package com.code.challenge.domain.service;

import com.code.challenge.presentation.dtos.AccountDTO;
import org.springframework.stereotype.Component;

@Component
public interface IAccountService {
    AccountDTO getAccount(Long id);

    AccountDTO addAccount(AccountDTO accountDTO);

    AccountDTO deleteAccount(Long id);

    AccountDTO updateAccount(Long id, AccountDTO accountDTO);
}
