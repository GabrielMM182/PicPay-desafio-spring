package com.picpayVersaoIncial.picpayVersaoIncial.domain.user;

import com.picpayVersaoIncial.picpayVersaoIncial.domain.transaction.Transaction;
import com.picpayVersaoIncial.picpayVersaoIncial.dtos.UserDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor // Construtor padrão necessário para o Hibernate
@AllArgsConstructor // Construtor com todos os argumentos
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //@NotBlank(message = "Nome é obrigatorio")
    private String firstName;

    //@NotBlank(message = "Nome final obrigatorio")
    private String lastName;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    @Email
    private String email;

    private String password;

    @Column(precision = 19, scale = 2)
    public BigDecimal balance;

    @Enumerated(EnumType.STRING)
    public UserType userType;

    public User(UserDTO data){
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.balance = data.balance();
        this.userType = data.userType();
        this.document = data.document();
        this.email = data.email();
        this.password = data.password();
    }

}