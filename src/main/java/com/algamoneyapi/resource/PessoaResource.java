package com.algamoneyapi.resource;

import com.algamoneyapi.evento.RecursoCriadoEvent;
import com.algamoneyapi.model.Pessoa;
import com.algamoneyapi.repository.PessoaRepository;
import com.algamoneyapi.service.PessoaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestControllerAdvice
@RequestMapping("/pessoas")
public class PessoaResource
{
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PessoaService pessoaService;

    /*
     *  Salvar
     */
    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pessoa> criar(@Validated @RequestBody Pessoa pessoa, HttpServletResponse response)
    {
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }

    /*
     *  Pesquisar por id
     */
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable Long id)
    {
        Pessoa pessoa = pessoaRepository.findById(id) //Retorna status 200 se ok
                                        .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND)); //Retorna erro 404

        return pessoa != null ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();
    }

    /*
     *  Deletar
     */
    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id)
    {
        pessoaRepository.deleteById(id);
    }

    /*
     *  Atualizar
     */
    @PutMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @Validated @RequestBody Pessoa pessoa)
    {
        Pessoa pessoaAtualiza = pessoaService.atualizar(id,pessoa);

        return ResponseEntity.ok(pessoaAtualiza);
    }

    @PutMapping("/{id}/ativo")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarPropriedadeAtivo(@PathVariable Long id, @RequestBody Boolean ativo)
    {
        pessoaService.atualizarPropriedadeAtivo(id,ativo);
    }
}