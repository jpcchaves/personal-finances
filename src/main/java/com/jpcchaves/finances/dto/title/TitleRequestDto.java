package com.jpcchaves.finances.dto.title;

import com.jpcchaves.finances.domain.Enum.ETitleType;
import com.jpcchaves.finances.domain.model.CostCenter;
import com.jpcchaves.finances.dto.costcenter.CostCenterRequestDto;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.List;

public class TitleRequestDto {
    private Long id;
    @NotBlank(message = "A descrição é obrigatória!")
    private String description;

    private ETitleType type;
    private List<CostCenterRequestDto> costcenter;

    @NotBlank(message = "O valor é obrigatório!")
    private Double amount;
    private Date createdAt;

    private Date referenceDate;

    @NotBlank(message = "A data de expiração é obrigatória!")
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

    public List<CostCenterRequestDto> getCostcenter() {
        return costcenter;
    }

    public void setCostcenter(List<CostCenterRequestDto> costcenter) {
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
