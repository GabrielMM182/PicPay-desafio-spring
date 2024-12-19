package com.picpayVersaoIncial.picpayVersaoIncial.services;

import com.picpayVersaoIncial.picpayVersaoIncial.domain.user.User;
import com.picpayVersaoIncial.picpayVersaoIncial.domain.user.UserType;
import com.picpayVersaoIncial.picpayVersaoIncial.dtos.UserDTO;
import com.picpayVersaoIncial.picpayVersaoIncial.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


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

//    public User findUserById(Long id) throws Exception {
//        return this.repository.findUserById(id).orElseThrow(() -> new Exception("User not found"));
//    }
public User findUserById(Long userId) {
    return repository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found"));
}

    public User findUserByDocument(String document) throws  Exception {
        return this.repository.findUserByDocument(document).orElseThrow(() -> new Exception("Document link CPF not found"));
    }

    public User createUser(UserDTO data){
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers(){
        return  this.repository.findAll();
    }

    public void saveUser(User user){
        this.repository.save(user);
    }

}
