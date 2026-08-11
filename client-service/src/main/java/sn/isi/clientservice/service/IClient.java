package sn.isi.clientservice.service;

import sn.isi.clientservice.entity.Client;

import java.util.List;

public interface IClient {

    Client save(Client client);


    Client findById(Long id);


    List<Client> findAll();
}
