package com.algamoneyapi.service;

import com.algamoneyapi.model.Lancamento;
import com.algamoneyapi.model.Pessoa;
import com.algamoneyapi.repository.LancamentoRepository;
import com.algamoneyapi.repository.PessoaRepository;
import com.algamoneyapi.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LancamentoService
{
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public Lancamento savar(Lancamento lancamento)
    {
            //Obtém o id da pessoa
            Pessoa pessoa = pessoaRepository.findById(lancamento.getPessoa().getId())
                    .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

            //Verifica se a pessoa está ativa é false
            if(pessoa == null || pessoa.getAtivo() == false)
            {
                throw new PessoaInexistenteOuInativaException();
            }

        return lancamentoRepository.save(lancamento);
    }
}
