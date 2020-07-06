package org.fasttrackit.expensestracker.service;

public class GetTransactionRequest {


    private String transactionType;
    private Double minValue;
    private Integer monthValue;

    @Override
    public String toString() {
        return "GetTransactionRequest{" +
                "transactionType='" + transactionType + '\'' +
                ", minValue=" + minValue +
                ", monthValue=" + monthValue +
                '}';
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public Integer getMonthValue() {
        return monthValue;
    }

    public void setMonthValue(Integer monthValue) {
        this.monthValue = monthValue;
    }
}
