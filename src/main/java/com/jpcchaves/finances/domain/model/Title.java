package com.jpcchaves.finances.domain.model;

import com.jpcchaves.finances.domain.Enum.ETitleType;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTitle")
    private Long id;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    private ETitleType type;

    @ManyToMany
    @JoinTable(
            name = "title_costcenter",
            joinColumns = @JoinColumn(name = "idTitle"),
            inverseJoinColumns = @JoinColumn(name = "idCostcenter")
    )
    private List<CostCenter> costcenter;

    @Column(nullable = false)
    private Double amount;

    private Date createdAt;

    private Date referenceDate;

    private Date expirationDate;

    private Date paymentDate;

    @Column(columnDefinition = "TEXT")
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ETitleType getType() {
        return type;
    }

    public void setType(ETitleType type) {
        this.type = type;
    }

    public List<CostCenter> getCostcenter() {
        return costcenter;
    }

    public void setCostcenter(List<CostCenter> costcenter) {
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
