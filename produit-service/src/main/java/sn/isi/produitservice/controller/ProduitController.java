package sn.isi.produitservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sn.isi.produitservice.entity.Produit;
import sn.isi.produitservice.service.ProduitServiceImplementation;

import java.util.List;

@RestController
@RequestMapping("/produits")
@RequiredArgsConstructor
public class ProduitController {

    private final ProduitServiceImplementation produitServiceImplementation;

    @PostMapping
    public Produit create(@RequestBody Produit produit) {
        return produitServiceImplementation.save(produit);
    }

    @GetMapping("/{id}")
    public Produit getById(@PathVariable Long id) {
        return produitServiceImplementation.findById(id);
    }

    @GetMapping
    public List<Produit> getAll() {
        return produitServiceImplementation.findAll();
    }
}
