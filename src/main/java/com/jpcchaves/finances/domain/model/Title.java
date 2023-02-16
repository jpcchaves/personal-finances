package com.jpcchaves.finances.domain.model;

import jakarta.persistence.*;

@Entity
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTitulo")
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    private ETipoTitulo tipo;


    @ManyToMany
    @JoinTable(
            name = "titulo_centrodecusto",
            joinColumns = @JoinColumn(name = "idTitulo"),
            inverseJoinColumns = @JoinColumn(name = "idCentroDeCusto")
    )
    private List<CentroDeCusto> centrosDeCustos;

    @Column(nullable = false)
    private Double valor;

    private Date dataCadastro;

    private Date dataReferencia;

    private Date dataVencimento;

    private Date dataPagamento;

    @Column(columnDefinition = "TEXT")
    private String observacao;


}
