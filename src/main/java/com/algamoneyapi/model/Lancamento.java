package com.algamoneyapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "lancamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@CrossOrigin(origins = "http://localhost:4200")
public class Lancamento
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String descricao;

    @Column(nullable = false, name = "data_vencimento")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVencimento;

    @Column(nullable = false, name = "data_pagamento")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPagamento;

    @Column(nullable = false, name = "valor")
    private BigDecimal valor;

    @Column(name = "observacao")
    private String observacao;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_pessoa")
    private Pessoa pessoa;

    /***
     * Sets e Gets
     */
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public LocalDate getDataVencimento()
    {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento)
    {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataPagamento()
    {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento)
    {
        this.dataPagamento = dataPagamento;
    }

    public BigDecimal getValor()
    {
        return valor;
    }

    public void setValor(BigDecimal valor)
    {
        this.valor = valor;
    }

    public String getObservacao()
    {
        return observacao;
    }

    public void setObservacao(String observacao)
    {
        this.observacao = observacao;
    }

    public Categoria getCategoria()
    {
        return categoria;
    }

    public void setCategoria(Categoria categoria)
    {
        this.categoria = categoria;
    }

    public Pessoa getPessoa()
    {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa)
    {
        this.pessoa = pessoa;
    }
}
