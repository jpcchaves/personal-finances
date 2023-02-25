package com.jpcchaves.finances.dto.dashboard;

import com.jpcchaves.finances.domain.model.Title;
import com.jpcchaves.finances.dto.title.TitleResponseDto;

import java.util.List;

public class DashboardResponseDto {

    private Double amountToPay;
    private Double amountToReceive;
    private Double balance;
    private List<TitleResponseDto> payingTitles;
    private List<TitleResponseDto> receivingTitles;

    public DashboardResponseDto() {
    }

    public DashboardResponseDto(Double amountToPay, Double amountToReceive, Double balance, List<TitleResponseDto> payingTitles, List<TitleResponseDto> receivingTitles) {
        this.amountToPay = amountToPay;
        this.amountToReceive = amountToReceive;
        this.balance = balance;
        this.payingTitles = payingTitles;
        this.receivingTitles = receivingTitles;
    }

    public Double getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(Double amountToPay) {
        this.amountToPay = amountToPay;
    }

    public Double getAmountToReceive() {
        return amountToReceive;
    }

    public void setAmountToReceive(Double amountToReceive) {
        this.amountToReceive = amountToReceive;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<TitleResponseDto> getPayingTitles() {
        return payingTitles;
    }

    public void setPayingTitles(List<TitleResponseDto> payingTitles) {
        this.payingTitles = payingTitles;
    }

    public List<TitleResponseDto> getReceivingTitles() {
        return receivingTitles;
    }

    public void setReceivingTitles(List<TitleResponseDto> receivingTitles) {
        this.receivingTitles = receivingTitles;
    }
}
