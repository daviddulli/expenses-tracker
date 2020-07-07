package org.fasttrackit.expensestracker;

import org.fasttrackit.expensestracker.domain.Transaction;
import org.fasttrackit.expensestracker.exception.ResourceNotFoundException;
import org.fasttrackit.expensestracker.service.TransactionService;
import org.fasttrackit.expensestracker.steps.TransactionTestSteps;
import org.fasttrackit.expensestracker.transfer.SaveTransactionRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class TransactionServiceIntegrationTest {


    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionTestSteps transactionTestSteps;

    @Test
    void createTransaction_whenValidRequest_thenTransactionIsCreated(){
        transactionTestSteps.createTransaction();

    }


    @Test
    void updateTransaction_whenExistingTransaction_thenTransactionIsUpdated(){
        Transaction transaction = transactionTestSteps.createTransaction();

        SaveTransactionRequest saveTransactionRequest = new SaveTransactionRequest();
        saveTransactionRequest.setDescription(transaction.getDescription() + " updated.");
        saveTransactionRequest.setValue(transaction.getValue() + 50);
        saveTransactionRequest.setDate(transaction.getDate());
        saveTransactionRequest.setTransactionType(transaction.getTransactionType() + " updated");


        Transaction updateTransaction = transactionService.updateTransaction(transaction.getId(), saveTransactionRequest);

        assertThat(updateTransaction, notNullValue());
        assertThat(updateTransaction.getId(), is(transaction.getId()));
        assertThat(updateTransaction.getDescription(), is(saveTransactionRequest.getDescription()));
        assertThat(updateTransaction.getValue(), is(saveTransactionRequest.getValue()));
        assertThat(updateTransaction.getDate(), is(saveTransactionRequest.getDate()));
        assertThat(updateTransaction.getTransactionType(), is(saveTransactionRequest.getTransactionType()));
    }

    @Test
    void createTransaction_whenMissingName_thenExceptionIsThrown(){

        SaveTransactionRequest saveTransactionRequest = new SaveTransactionRequest();
        saveTransactionRequest.setValue(50.0);
        saveTransactionRequest.setDate(LocalDate.now());
        saveTransactionRequest.setTransactionType("Income");

        try{
            transactionService.createTransaction(saveTransactionRequest);
        }catch (Exception e){
            assertThat(e, notNullValue());
            assertThat("Unexpected exception type", e instanceof TransactionSystemException);

        }


    }

    @Test
    void getTransaction_whenTransactionExists_thenGetTransaction(){
        Transaction transaction = transactionTestSteps.createTransaction();

        Transaction response = transactionService.getTransaction(transaction.getId());
        assertThat(response, notNullValue());
        assertThat(response.getId(), is(transaction.getId()));
        assertThat(response.getValue(), is(transaction.getValue()));
        assertThat(response.getDescription(), is(transaction.getDescription()));
        assertThat(response.getDate(), is(transaction.getDate()));
        assertThat(response.getTransactionType(), is(transaction.getTransactionType()));
    }

    @Test
    void getTransaction_whenNonExistingTransaction_thenThrowResourceNotFoundException(){

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> transactionService.getTransaction(99999));


    }


}
