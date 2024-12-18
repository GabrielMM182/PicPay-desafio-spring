package com.picpayVersaoIncial.picpayVersaoIncial.services;

import com.picpayVersaoIncial.picpayVersaoIncial.domain.user.User;
import com.picpayVersaoIncial.picpayVersaoIncial.domain.user.UserType;
import com.picpayVersaoIncial.picpayVersaoIncial.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() == UserType.MERCHANT) {
            throw  new Exception("User cannot be Merchant in this situation");
        }

        if(sender.getBalance().compareTo(amount) < 0 ) {
            throw new Exception("Amount equals 0 you don't have money");
        }
    }

    public User findUserById(Long id) throws Exception {
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("User not found"));
    }

    public User findUserByDocument(String document) throws  Exception {
        return this.repository.findUserByDocument(document).orElseThrow(() -> new Exception("Document link CPF not found"));
    }

    public void saveUser(User user){
        this.repository.save(user);
    }

}
