package org.fasttrackit.expensestracker.service;

import org.fasttrackit.expensestracker.domain.Transaction;
import org.fasttrackit.expensestracker.exception.ResourceNotFoundException;
import org.fasttrackit.expensestracker.persistance.TransactionRepository;
import org.fasttrackit.expensestracker.transfer.SaveTransactionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    public static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

    public final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    public Transaction createTransaction(SaveTransactionRequest request){

        LOGGER.info("Creating transaction {}", request);

        Transaction transaction = new Transaction();
        transaction.setDescription(request.getDescription());
        transaction.setValue(request.getValue());
        transaction.setDate(request.getDate());
        transaction.setTransactionType(request.getTransactionType());

        return transactionRepository.save(transaction);

    }


    public List<Transaction> getTransactions(){
        LOGGER.info("Retrieving all transactions...");
        return transactionRepository.findAll();

    }

    public Transaction getTransaction(long id){
        LOGGER.info("Retrieving transaction {}", id);

        Optional<Transaction> transactionOptional = transactionRepository.findById(id);

        if (transactionOptional.isPresent()){
            return transactionOptional.get();
        }else {

            throw new ResourceNotFoundException("Transaction " + id + " not found.");
        }
    }


    public Transaction updateTransaction(long id, SaveTransactionRequest request){

        LOGGER.info("Updating transaction {}: {}", id, request);

        Transaction transaction = getTransaction(id);

        BeanUtils.copyProperties(request, transaction);

        return transactionRepository.save(transaction);

    }

    public void deleteTransaction(long id){
        LOGGER.info("Deleting transaction {}", id);
        transactionRepository.deleteById(id);
    }

}
