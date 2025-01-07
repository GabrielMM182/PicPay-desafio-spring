package com.picpayVersaoIncial.picpayVersaoIncial.services;

import com.picpayVersaoIncial.picpayVersaoIncial.domain.transaction.Transaction;
import com.picpayVersaoIncial.picpayVersaoIncial.domain.user.User;
import com.picpayVersaoIncial.picpayVersaoIncial.dtos.TransactionDTO;
import com.picpayVersaoIncial.picpayVersaoIncial.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private NotificationService notificationService;



    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());

        boolean isAutorized = this.authorizationService.authorizeTransaction(sender, transaction.value());
        if(!isAutorized) {
            throw new Exception("Transação não autorizada");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        this.repository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        this.notificationService.sendNotification(sender, "Transaction success");

        this.notificationService.sendNotification(receiver, "Transaction success");

        return newTransaction;
    }

}


