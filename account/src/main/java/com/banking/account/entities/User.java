package com.banking.account.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class User {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cpf;
    private String phone;
    private String idAccount;

    @OneToOne(mappedBy = "user")
    private Account account;

    public User(String name, String cpf, String phone, String idAccount) {
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.idAccount = idAccount;
    }

    
}
