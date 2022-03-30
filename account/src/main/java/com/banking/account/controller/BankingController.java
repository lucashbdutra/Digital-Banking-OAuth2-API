package com.banking.account.controller;

import com.banking.account.entities.Account;
import com.banking.account.entities.User;
import com.banking.account.repository.UserRepository;
import com.banking.account.service.BankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("banking")
public class BankingController {

    @Autowired
    public BankingService service;

    @Autowired
    public UserRepository userRepo;

    @GetMapping
    ResponseEntity<List<Account>> findAll(){

        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Account> findById(@PathVariable Long id) {

        return ResponseEntity.ok().body(service.findById(id));

    }

    @PostMapping
    ResponseEntity<User> addAssociate(@RequestBody User user){

        return ResponseEntity.ok().body(service.addAssociate(user));
    }

    @GetMapping("/f")
    ResponseEntity<List<User>> test(){
        return ResponseEntity.ok().body(userRepo.findAll());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<List<Object>> deleteById(@PathVariable Long id){

        return ResponseEntity.ok().body(service.deleteById(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<Account> deposito(@PathVariable Long id, @RequestBody Account acc){

        return ResponseEntity.ok().body(service.deposito(id,acc));
    }

    @PutMapping("/{id}/withdraw")
    ResponseEntity<Account> withdraw(@PathVariable Long id, @RequestBody Account acc){
        return ResponseEntity.ok().body(service.withdraw(id,acc));
    }



}
