package com.algamoneyapi.model;

import javax.persistence.*;
import org.springframework.web.bind.annotation.CrossOrigin;


@Entity
@Table(name = "categoria")
@CrossOrigin(origins = "http://localhost:4200")
public class Categoria
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

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
}
