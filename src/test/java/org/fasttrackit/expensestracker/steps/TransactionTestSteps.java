package org.fasttrackit.expensestracker.steps;

import org.fasttrackit.expensestracker.domain.Transaction;
import org.fasttrackit.expensestracker.service.TransactionService;
import org.fasttrackit.expensestracker.transfer.SaveTransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
@Component
public class TransactionTestSteps {

    @Autowired
    private TransactionService transactionService;

    public Transaction createTransaction() {
        SaveTransactionRequest saveTransactionRequest = new SaveTransactionRequest();
        saveTransactionRequest.setValue(-50.0);
        saveTransactionRequest.setDescription("This is an expense");
        saveTransactionRequest.setDate(LocalDate.now());
        saveTransactionRequest.setTransactionType("Expense");

        Transaction transactionResponse = transactionService.createTransaction(saveTransactionRequest);

        assertThat(transactionResponse, notNullValue());
        assertThat(transactionResponse.getId(), greaterThan(0L));
        assertThat(transactionResponse.getDescription(), is(saveTransactionRequest.getDescription()));
        assertThat(transactionResponse.getValue(), is(saveTransactionRequest.getValue()));
        assertThat(transactionResponse.getDate(), is(saveTransactionRequest.getDate()));
        assertThat(transactionResponse.getTransactionType(), is(saveTransactionRequest.getTransactionType()));

        return transactionResponse;
    }

}
