package org.fasttrackit.expensestracker.persistance;

import org.fasttrackit.expensestracker.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {


}
