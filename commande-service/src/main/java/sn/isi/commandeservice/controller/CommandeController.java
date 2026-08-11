package sn.isi.commandeservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sn.isi.commandeservice.dto.CommandeDTO;
import sn.isi.commandeservice.entity.Commande;
import sn.isi.commandeservice.service.CommandeServiceImplementation;

import java.util.List;

@RestController
@RequestMapping("/commandes")
@RequiredArgsConstructor
public class CommandeController {

    private final CommandeServiceImplementation commandeServiceImplementation;


    @PostMapping
    public Commande create(@RequestBody Commande commande) {

        return commandeServiceImplementation.save(commande);
    }


    @GetMapping("/{id}")
    public CommandeDTO getById(@PathVariable Long id) {

        return commandeServiceImplementation.findById(id);
    }


    @GetMapping
    public List<Commande> getAll() {

        return commandeServiceImplementation.findAll();
    }
}
