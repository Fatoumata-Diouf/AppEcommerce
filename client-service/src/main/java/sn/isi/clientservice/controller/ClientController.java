package sn.isi.clientservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sn.isi.clientservice.entity.Client;
import sn.isi.clientservice.service.ClientServiceImplementation;

import java.util.List;


@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientServiceImplementation clientServiceImplementation;

    @PostMapping
    public Client create(@RequestBody Client client){

        return clientServiceImplementation.save(client);
    }



    @GetMapping("/{id}")
    public Client getById(@PathVariable Long id){

        return clientServiceImplementation.findById(id);
    }



    @GetMapping
    public List<Client> getAll(){

        return clientServiceImplementation.findAll();
    }



}
