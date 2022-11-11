package com.algamoneyapi.service;

import com.algamoneyapi.model.Pessoa;
import com.algamoneyapi.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/*
 * Classe responsável pela regra de negócios
 * */
@Service
public class PessoaService
{
    @Autowired
    private PessoaRepository pessoaRepository;

    //Método responsável por fazer a busca da pessoa pelo seu respectivo código
    public Pessoa buscarPessoaPeloCodigo(Long id)
    {
        Pessoa pessoaAtualiza = pessoaRepository.findById(id).orElse(null);

        if(pessoaAtualiza == null)
        {
            throw new EmptyResultDataAccessException(1);
        }
        return pessoaAtualiza;
    }

    //Método atualiza todos os dados da tabela pessoa
    public Pessoa atualizar(Long id,Pessoa pessoa)
    {
        Pessoa pessoaAtualiza = buscarPessoaPeloCodigo(id);

        BeanUtils.copyProperties(pessoa, pessoaAtualiza, "id");

       return pessoaRepository.save(pessoaAtualiza);
    }

    //Método para auterar o status ativo ou inativo
    public void atualizarPropriedadeAtivo(Long id, Boolean ativo)
    {
        Pessoa pessoaAtualiza = buscarPessoaPeloCodigo(id);
        pessoaAtualiza.setAtivo(ativo);
        pessoaRepository.save(pessoaAtualiza);
    }
}
