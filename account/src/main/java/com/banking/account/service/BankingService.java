package com.banking.account.service;

import com.banking.account.entities.Account;
import com.banking.account.entities.User;
import com.banking.account.repository.AccountRepository;
import com.banking.account.repository.UserRepository;
import com.banking.account.service.interfaces.BankingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BankingService implements BankingServiceInterface {

    @Autowired
    public AccountRepository accountRepo;

    @Autowired
    public UserRepository userRepo;

    @Override
    public List<Account> findAll() {

        return accountRepo.findAll();
    }

    @Override
    public Account findById(Long id) {

        Optional<Account> obj =   accountRepo.findById(id);

        if(obj.isPresent()){

            return obj.get();
        }

        return null;
    }

    @Override
    public User addAssociate(User user) {

        Long id = Long.valueOf(user.getIdAccount());

        if(accountRepo.findById(id).isEmpty()){

            userRepo.save(user);
            Account account = new Account(0L);
            accountRepo.save(account);

            if(accountRepo.findById(id).get() == account){

                user.setAccount(account);
                account.setUser(user);
                accountRepo.save(account);
                userRepo.save(user);
            } else{
                userRepo.delete(user);
                accountRepo.delete(account);
            }

        }
        return user;
        //Problemas pra salvar no repositório, ocorre pois estou tentando salvar um objeto relacionado a outro que nao está salvo ou seja persistido pelo hibernate
        //ao que tudo indica para mecher com objetos que estão relacionados ambos tem que estar salvos no JPA.

    }

    @Override //Delete está dando um problema para excluir objetos relacionados com outros
    public List<Object> deleteById(Long id) {

        if(accountRepo.findById(id).isPresent()){

            Account account = accountRepo.findById(id).get();
            List<Object> obj = Arrays.asList(account.getUser(), account);
            userRepo.delete(account.getUser());
            accountRepo.deleteById(id);

            return obj;

        }
        return null;
    }

    @Override
    public Account deposito(Long id, Account account) {

        Optional<Account> acc = accountRepo.findById(id);

        if(acc.isPresent()){

            Account obj = acc.get();
            if(account.getBalance() != null){
                obj.setBalance(account.getBalance());
                accountRepo.save(obj);
                return obj;
            }

        }

        return null;
    }

    @Override
    public Account withdraw(Long id, Account account) {

        Optional<Account> acc = accountRepo.findById(id);

        if(acc.isPresent()) {

            Account obj = acc.get();

            if (obj.getBalance().equals(account.getBalance()) || obj.getBalance() > account.getBalance()) {

                obj.setBalance(obj.getBalance()-account.getBalance());
                accountRepo.save(obj);
                return obj;

            }
        }
        return null;
    }
}
