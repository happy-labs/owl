package com.score.myexp.pojos;

/**
 * Created by eranga on 8/29/15.
 */
public class Expense {
    private String name;
    private Double amount;

    public Expense(String name, Double amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
