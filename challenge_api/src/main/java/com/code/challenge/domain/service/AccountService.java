package com.code.challenge.domain.service;

import com.code.challenge.domain.model.Account;
import com.code.challenge.persistence.repository.AccountRepository;
import com.code.challenge.presentation.dtos.AccountDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountService implements IAccountService {

    private AccountRepository accountRepository;
    private ModelMapper modelMapper;

    @Autowired
    public AccountService(AccountRepository accountRepository, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AccountDTO getAccount(Long id) {
        return modelMapper.map(accountRepository.getById(id), AccountDTO.class);
    }

    @Override
    public AccountDTO addAccount(AccountDTO accountDTO) {
        Account account = accountRepository.save(modelMapper.map(accountDTO, Account.class));
        return modelMapper.map(account, AccountDTO.class);
    }

    @Override
    public AccountDTO deleteAccount(Long id) {
        Account account = accountRepository.getById(id);
        accountRepository.delete(account);
        return modelMapper.map(account, AccountDTO.class);
    }

    @Override
    public AccountDTO updateAccount(Long id, AccountDTO accountDTO) {
        Account account = accountRepository.getById(id);
        account = modelMapper.map(accountDTO, Account.class);
        accountRepository.save(account);
        return null;
    }
}
