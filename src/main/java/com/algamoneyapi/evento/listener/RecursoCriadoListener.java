package com.algamoneyapi.evento.listener;

import com.algamoneyapi.evento.RecursoCriadoEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent>
{

    @Override
    public void onApplicationEvent(RecursoCriadoEvent recursoCriadoEvent)
    {
        HttpServletResponse response = recursoCriadoEvent.getResponse();
        Long id = recursoCriadoEvent.getId();

        adicionarHeaderLocation(response, id);
    }

    public void adicionarHeaderLocation(HttpServletResponse response, Long id)
    {
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(id)
                .toUri();

        response.setHeader("Location", uri.toASCIIString());
    }
}
