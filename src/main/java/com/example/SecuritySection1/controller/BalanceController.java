package com.example.SecuritySection1.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SecuritySection1.model.AccountTransactions;
import com.example.SecuritySection1.repository.AccountTransactionsRepository;

import lombok.RequiredArgsConstructor;

@RestController
public class BalanceController {

	@Autowired
    private  AccountTransactionsRepository accountTransactionsRepository;

    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam long id) {
        List<AccountTransactions> accountTransactions = accountTransactionsRepository.findByCustomerIdOrderByTransactionDtDesc(id);
              
        if (accountTransactions != null) {
            return accountTransactions;
        } else {
            return null;
        }
    }
}