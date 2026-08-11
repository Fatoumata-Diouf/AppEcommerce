package sn.isi.produitservice.service;

import sn.isi.produitservice.entity.Produit;

import java.util.List;

public interface IProduitService {

    Produit save(Produit produit);

    Produit findById(Long id);

    List<Produit> findAll();
}
