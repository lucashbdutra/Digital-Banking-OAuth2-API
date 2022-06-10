package com.banking.account.service;

import com.banking.account.entities.Account;
import com.banking.account.entities.User;
import com.banking.account.repository.AccountRepository;
import com.banking.account.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class BankingServiceTest {

    private final Long ID = 1L;

    @InjectMocks
    BankingService service;

    @Mock
    UserRepository userRepo;

    @Mock
    AccountRepository accountRepo;

    private User user;
    private Account account;
    private List<Account> list;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); //sem essa linha por algum motivo o mock nao reconhece o beforeEach
        this.user = new User("Jão", "065465654", "83898498498","1");
        this.account = new Account(0L);
        this.list = List.of(account);


    }

    @Test
    void findAll() {
        when(accountRepo.findAll()).thenReturn(list);
        List<Account> resul = service.findAll();
    }

    @Test
    void findById() {
        when(accountRepo.findById(anyLong())).thenReturn(Optional.ofNullable(account));
        Account resul = service.findById(ID);
    }

    @Test
    void addAssociate() {
        doNothing().when(accountRepo.findById(anyLong()).isEmpty());
        doNothing().when(userRepo.save(user));
        doNothing().when(accountRepo.save(account));
        when(accountRepo.findById(anyLong()).get()).thenReturn(account);
        User resul = service.addAssociate(user);
    }

    @Test
    void deleteById() {
    }

    @Test
    void deposito() {
    }

    @Test
    void withdraw() {
    }
}