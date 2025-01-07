package com.picpayVersaoIncial.picpayVersaoIncial.repositories;

import com.picpayVersaoIncial.picpayVersaoIncial.domain.user.User;
import com.picpayVersaoIncial.picpayVersaoIncial.domain.user.UserType;
import com.picpayVersaoIncial.picpayVersaoIncial.dtos.UserDTO;
import jakarta.persistence.EntityManager;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    EntityManager entityManager; // serve para adicionar tais dados criados em nosso bd de teste

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("Should create and get user COMMON successfully from db")
    void findUserCommonByDocumentSuccess() {
        String document = "99999999901";
        BigDecimal amount = BigDecimal.valueOf(10);
        UserType userType = UserType.COMMON;
        UserDTO data = new UserDTO("gabriel", "morais", document, "gabriel@email.com", "123", amount, userType);
        this.createUser(data);

        Optional<User> result = this.userRepository.findUserByDocument(document);

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getDocument()).isEqualTo(document);
    }

    @Test
    @DisplayName("Should not create and get User from db that is empty")
    void findUserByDocumentIsEmpty() {
        String document = "99999999901";

        Optional<User> result = this.userRepository.findUserByDocument(document);
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Should return empty Optional when user does not exist")
    void findUserByDocumentErrorInDocument() {
        String document = "nonexistent_document";

        Optional<User> result = this.userRepository.findUserByDocument(document);

        assertThat(result.isPresent()).isFalse();
    }

    @Test
    @DisplayName("Should create and get user MERCHANT successfully from db")
    void findUserMerchantByDocumentSuccess() {
        String document = "99999999902";
        BigDecimal amount = BigDecimal.valueOf(10);
        UserType userType = UserType.MERCHANT;
        UserDTO data = new UserDTO("gabriel", "morais", document, "gabriel@email.com", "123", amount, userType);
        this.createUser(data);

        Optional<User> result = this.userRepository.findUserByDocument(document);

        assertThat(result.isPresent()).isTrue();
    }



    @Test
    @DisplayName("Should throw DataIntegrityViolationException when creating a duplicated user")
    void findUserMerchantByDocumentDuplicatedError() {
        String document = "99999999902";
        BigDecimal amount = BigDecimal.valueOf(10);
        UserType userType = UserType.MERCHANT;

        //UserDTO data = new UserDTO("gabriel", "morais", document, "gabriel@email.com", "123", amount, userType);
        UserDTO data = new UserDTO("gabriel", "morais", document, "gabriel@email.com", "123", new BigDecimal(10), UserType.COMMON);
        this.createUser(data);

        UserDTO duplicateData = new UserDTO("gabriel", "morais", document, "gabriel@email.com", "123", new BigDecimal(10), UserType.COMMON);

        assertThrows(ConstraintViolationException.class, () -> this.createUser(duplicateData));
    }



    private User createUser(UserDTO data){
        User newUser = new User(data);
        this.entityManager.persist(newUser);
        return newUser;
    }
}