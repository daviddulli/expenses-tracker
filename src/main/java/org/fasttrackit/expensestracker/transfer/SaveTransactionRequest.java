package org.fasttrackit.expensestracker.transfer;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

public class SaveTransactionRequest {


    @NotNull
    private String description;
    @NotNull
    private Double value;
    @NotNull
    private LocalDate date;
    @NotNull
    private String transactionType;

    @Override
    public String toString() {
        return "SaveTransactionRequest{" +
                "description='" + description + '\'' +
                ", value=" + value +
                ", date=" + date +
                ", transactionType='" + transactionType + '\'' +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
