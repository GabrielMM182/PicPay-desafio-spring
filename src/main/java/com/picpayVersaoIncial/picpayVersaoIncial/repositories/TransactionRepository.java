package com.picpayVersaoIncial.picpayVersaoIncial.repositories;

import com.picpayVersaoIncial.picpayVersaoIncial.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
