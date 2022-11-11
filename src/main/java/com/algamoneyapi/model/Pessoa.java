package com.algamoneyapi.model;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;

@Entity
@Table(name = "pessoa")
@CrossOrigin(origins = "http://localhost:4200")
public class Pessoa
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, name = "ativo")
    private Boolean ativo;

    @Embedded
    private Endereco endereco;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public Boolean getAtivo()
    {
        return ativo;
    }


    public void setAtivo(Boolean ativo)
    {
        this.ativo = ativo;
    }

    public Endereco getEndereco()
    {
        return endereco;
    }

    public void setEndereco(Endereco endereco)
    {
        this.endereco = endereco;
    }
}
