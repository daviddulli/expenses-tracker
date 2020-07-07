package org.fasttrackit.expensestracker.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.expensestracker.domain.Transaction;
import org.fasttrackit.expensestracker.service.TransactionService;
import org.fasttrackit.expensestracker.transfer.SaveTransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/transactions")
public class TransactionController {


    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody SaveTransactionRequest request){
        Transaction transaction = transactionService.createTransaction(request);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable long id){
        Transaction transaction = transactionService.getTransaction(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }



    @GetMapping
    public ResponseEntity<List<Transaction>> getTransactions(){
        List<Transaction> transactions = transactionService.getTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable long id, @Valid @RequestBody SaveTransactionRequest request){
        Transaction transaction = transactionService.updateTransaction(id, request);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTransaction(@PathVariable long id){
        transactionService.deleteTransaction(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
