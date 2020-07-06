package org.fasttrackit.expensestracker.persistance;

import org.fasttrackit.expensestracker.domain.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findByTransactionTypeIs(String transactionType, Pageable pageable);
    Page<Transaction> findByValueGreaterThanEqual(double minValue, Pageable pageable);
    Page<Transaction> findByDate_MonthValue(int monthValue, Pageable pageable);

}
