package com.picpayVersaoIncial.picpayVersaoIncial.domain.user;

import com.picpayVersaoIncial.picpayVersaoIncial.domain.transaction.Transaction;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.usertype.UserType;

import java.math.BigDecimal;
import java.util.List;

@Entity(name="users")
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Nome é obrigatorio")
    private String firstName;

    @NotBlank(message = "Nome final obrogatorio")
    private String lastName;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    @Email
    private String email;

    private String password;

    @Column(precision = 19, scale = 2)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToMany(mappedBy = "sender")
    private List<Transaction> sentTransactions;

    @OneToMany(mappedBy = "receiver")
    private List<Transaction> receivedTransactions;
}
