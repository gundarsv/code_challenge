package com.code.challenge.domain.service;

import com.code.challenge.domain.model.Account;
import com.code.challenge.infrastructure.exception.AccountServiceBadRequestException;
import com.code.challenge.infrastructure.exception.AccountServiceEntityNotFoundException;
import com.code.challenge.infrastructure.exception.AccountServiceException;
import com.code.challenge.persistence.repository.IAccountRepository;
import com.code.challenge.presentation.dtos.AccountDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AccountService implements IAccountService {

    private IAccountRepository accountRepository;

    private ModelMapper modelMapper;

    @Autowired
    public AccountService(IAccountRepository accountRepository, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity getAccount(Long id) {
        try {
            Optional<Account> accountInDb = accountRepository.findById(id);

            if (!accountInDb.isPresent()) {
                throw new AccountServiceEntityNotFoundException(String.format("Account with id: %s does not exist", id));
            }

            return ResponseEntity.ok(accountInDb.get());
        } catch (AccountServiceEntityNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new AccountServiceException(String.format(String.format("Something went wrong and account was not found: %s", ex.getMessage())));
        }
    }

    @Override
    public ResponseEntity addAccount(AccountDTO accountDTO) {
        try {
            Optional<Account> accountInDb = accountRepository.findById(accountDTO.getId());

            if (accountInDb.isPresent()) {
                throw new AccountServiceBadRequestException(String.format("Account with id: %s already exists", accountDTO.getId()));
            }

            Account accountToAdd = modelMapper.map(accountDTO, Account.class);
            AccountDTO result = modelMapper.map(accountRepository.save(accountToAdd), AccountDTO.class);

            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (AccountServiceBadRequestException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new AccountServiceException(String.format(String.format("Account was not added ex: %s", ex.getMessage())));
        }
    }

    @Override
    public ResponseEntity deleteAccount(Long id) {
        try {
            Optional<Account> accountInDb = accountRepository.findById(id);

            if (!accountInDb.isPresent()) {
                throw new AccountServiceEntityNotFoundException(String.format("Account with id: %s does not exist", id));
            }

            accountRepository.deleteById(id);

            return ResponseEntity.ok(String.format("Account with id:%s  was deleted", id));
        } catch (AccountServiceEntityNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new AccountServiceException(String.format(String.format("Something went wrong and account was not deleted: %s", ex.getMessage())));
        }
    }

    @Override
    public ResponseEntity updateAccount(Long id, AccountDTO accountDTO) {
        try {
            if (id != accountDTO.getId()) {
                throw new AccountServiceBadRequestException("Id`s must be the same");
            }

            Optional<Account> accountInDb = accountRepository.findById(id);

            if (!accountInDb.isPresent()) {
                throw new AccountServiceEntityNotFoundException(String.format("Account with id: %s does not exist", id));
            }

            Account account = modelMapper.map(accountDTO, Account.class);
            AccountDTO updatedAccount = modelMapper.map(accountRepository.save(account), AccountDTO.class);

            return ResponseEntity.ok(updatedAccount);
        } catch (AccountServiceBadRequestException ex) {
            throw ex;
        } catch (AccountServiceEntityNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new AccountServiceException(String.format(String.format("Something went wrong and account was not updated: %s", ex.getMessage())));
        }
    }

    @Override
    public ResponseEntity getAccounts() {
        try {
            List<AccountDTO> accounts = accountRepository
                    .findAll()
                    .stream()
                    .map(account -> modelMapper.map(account, AccountDTO.class))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(accounts);
        } catch (Exception ex) {
            throw new AccountServiceException(String.format(String.format("Something went wrong and accounts are not returned: %s", ex.getMessage())));
        }
    }
}
