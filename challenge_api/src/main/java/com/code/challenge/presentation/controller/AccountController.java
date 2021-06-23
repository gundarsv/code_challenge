package com.code.challenge.presentation.controller;

import com.code.challenge.domain.service.IAccountService;
import com.code.challenge.presentation.dtos.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    private IAccountService accountService;

    @Autowired
    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("")
    public ResponseEntity getAccounts() {
        return accountService.getAccounts();
    }

    @GetMapping("{id}")
    public ResponseEntity getAccount(@PathVariable Long id) {
        return accountService.getAccount(id);
    }

    @PostMapping("")
    public ResponseEntity addAccount(@RequestBody @Valid AccountDTO accountDTO) {
        return accountService.addAccount(accountDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteAccount(@PathVariable Long id) {
        return accountService.deleteAccount(id);
    }

    @PutMapping("{id}")
    public ResponseEntity updateAccount(@PathVariable Long id, @RequestBody @Valid AccountDTO accountDTO) {
        return accountService.updateAccount(id, accountDTO);
    }
}
