package com.algamoneyapi.resource;

import com.algamoneyapi.evento.RecursoCriadoEvent;
import com.algamoneyapi.model.Lancamento;
import com.algamoneyapi.repository.LancamentoRepository;
import com.algamoneyapi.repository.filter.LancamentoFilter;
import com.algamoneyapi.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestControllerAdvice
@RequestMapping("/lancamentos")
public class LancamentoResource
{
    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private LancamentoService lancamentoService;


    /**
     *  Lista lançamentos através de parâmetros
     * */
    @GetMapping
    public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable)
    {
        return lancamentoRepository.filtrar(lancamentoFilter, pageable);
    }

//    public  List<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable)
//    {
//        return lancamentoRepository.filtrar(lancamentoFilter);
//    }

    /**
     *  Listar lançamentos por id
     * */
    @GetMapping("/{id}")
    public Lancamento buscarPeloCodigo(@PathVariable Long id)
    {
        return lancamentoRepository
                .findById(id) //Retorna status 200 se ok
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     *  Criar novo lançamento
     * */
    @PostMapping
    public ResponseEntity<Lancamento> criar(@RequestBody Lancamento lancamento, HttpServletResponse response)
    {
        //na classe LancamentoService verifica se a pessoa esta ativa ou inativa e salva
        Lancamento lancamentoSalvar = lancamentoService.savar(lancamento);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvar.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvar);
    }
}
