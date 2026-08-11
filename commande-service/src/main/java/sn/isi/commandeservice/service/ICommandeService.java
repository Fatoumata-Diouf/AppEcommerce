package sn.isi.commandeservice.service;

import sn.isi.commandeservice.dto.CommandeDTO;
import sn.isi.commandeservice.entity.Commande;

import java.util.List;

public interface ICommandeService {

    Commande save(Commande commande);

    CommandeDTO findById(Long id);

    List<Commande> findAll();
}
