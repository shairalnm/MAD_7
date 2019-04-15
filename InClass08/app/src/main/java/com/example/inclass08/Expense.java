package com.example.inclass08;

import java.io.Serializable;

public class Expense implements Serializable {
    private String ExpenseName;
    private String Category;
    private String Amount;
    private String date;
    private String key;


    public Expense(String expenseName, String category, String amount, String date, String key) {
        this.ExpenseName = expenseName;
        this.Category = category;
        this.Amount = amount;
        this.date = date;
        this.key = key;
    }

    public Expense() {
    }

    public String getExpenseName() {
        return ExpenseName;
    }

    public void setExpenseName(String expenseName) {
        ExpenseName = expenseName;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "ExpenseName='" + ExpenseName + '\'' +
                ", Category='" + Category + '\'' +
                ", Amount='" + Amount + '\'' +
                ", date='" + date + '\'' +
                ", key=" + key +
                '}';
    }
}
