package sn.isi.commandeservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sn.isi.commandeservice.dto.CommandeDTO;
import sn.isi.commandeservice.entity.Commande;
import sn.isi.commandeservice.service.CommandeServiceImplementation;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/commandes")
@RequiredArgsConstructor
public class CommandeController {

    private final CommandeServiceImplementation commandeServiceImplementation;

    @PostMapping
    public Commande create(@Valid @RequestBody Commande commande) {
        log.info("POST /commandes - création d'une commande");
        return commandeServiceImplementation.save(commande);
    }

    @GetMapping("/{id}")
    public CommandeDTO getById(@PathVariable Long id) {
        log.info("GET /commandes/{} - consultation d'une commande", id);
        return commandeServiceImplementation.findById(id);
    }

    @GetMapping
    public List<Commande> getAll() {
        log.info("GET /commandes - liste des commandes");
        return commandeServiceImplementation.findAll();
    }
}