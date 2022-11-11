package com.algamoneyapi.resource;

import com.algamoneyapi.evento.RecursoCriadoEvent;
import com.algamoneyapi.model.Categoria;
import com.algamoneyapi.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestControllerAdvice
@RequestMapping("/categorias")
public class CategoriaResource
{
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Categoria> listar()
    {
        return categoriaRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Categoria> criar(@Validated @RequestBody Categoria categoria, HttpServletResponse response)
    {
       Categoria categoriaSalva = categoriaRepository.save(categoria);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getId()));

       return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }

    @GetMapping("/{id}")
    public Categoria buscarPeloCodigo(@PathVariable Long id)
    {
        return categoriaRepository
                .findById(id) //Retorna status 200 se ok
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND)); //Retorna erro 404
    }
}