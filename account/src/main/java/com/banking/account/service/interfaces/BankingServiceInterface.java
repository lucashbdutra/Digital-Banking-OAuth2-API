package com.banking.account.service.interfaces;

import java.util.Arrays;
import java.util.List;

import com.banking.account.entities.Account;
import com.banking.account.entities.User;

public interface BankingServiceInterface {

    List<Account> findAll();
    Account findById(Long id);
    User addAssociate(User user);
    List<Object> deleteById(Long id);
    Account deposito(Long id, Account acc);
    Account withdraw(Long id, Account acc);
  
}


