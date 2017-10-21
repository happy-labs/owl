package com.score.owl.pojos;

/**
 * POJO class to keep expense details
 *
 * @author eranga herath(erangaeb@gmail.com)
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
