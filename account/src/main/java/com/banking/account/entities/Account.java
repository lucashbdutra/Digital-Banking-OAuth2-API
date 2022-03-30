package com.banking.account.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Account {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long balance;
    
    @ElementCollection(targetClass = String.class)
    private List<String> log = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user")
    @JsonIgnore
    private User user;

    public Account(Long balance){

        this.balance = balance;
    }
}
