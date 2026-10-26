package sn.isi.clientservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sn.isi.clientservice.entity.Client;
import sn.isi.clientservice.service.ClientServiceImplementation;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientServiceImplementation clientServiceImplementation;

    @PostMapping
    public Client create(@Valid @RequestBody Client client) {
        log.info("POST /clients - création d'un client");
        return clientServiceImplementation.save(client);
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable Long id) {
        log.info("GET /clients/{} - consultation d'un client", id);
        return clientServiceImplementation.findById(id);
    }

    @GetMapping
    public List<Client> getAll() {
        log.info("GET /clients - liste des clients");
        return clientServiceImplementation.findAll();
    }
}