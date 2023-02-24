package com.jpcchaves.finances.dto.title;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.jpcchaves.finances.domain.Enum.ETitleType;
import com.jpcchaves.finances.dto.costcenter.CostCenterRequestDto;
import com.jpcchaves.finances.dto.costcenter.CostCenterResponseDto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Date;
import java.util.List;

@JsonPropertyOrder({"id", "description", "type", "note", "amount", "createdAt", "referenceDate", "expirationDate", "paymentDate", "costcenter"})
public class TitleResponseDto {
    private Long id;
    private String description;

    private ETitleType type;
    private List<CostCenterResponseDto> costcenter;

    private Double amount;
    private Date createdAt;

    private Date referenceDate;

    private Date expirationDate;

    private Date paymentDate;
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ETitleType getType() {
        return type;
    }

    public void setType(ETitleType type) {
        this.type = type;
    }

    public List<CostCenterResponseDto> getCostcenter() {
        return costcenter;
    }

    public void setCostcenter(List<CostCenterResponseDto> costcenter) {
        this.costcenter = costcenter;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getReferenceDate() {
        return referenceDate;
    }

    public void setReferenceDate(Date referenceDate) {
        this.referenceDate = referenceDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
