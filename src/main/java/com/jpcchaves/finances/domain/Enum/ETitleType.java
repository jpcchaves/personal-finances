package com.jpcchaves.finances.domain.Enum;

public enum ETitleType {

    PAYINGORDER,
    RECEIVINGORDER;
    private String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
