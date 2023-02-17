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




}
