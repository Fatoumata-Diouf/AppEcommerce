package sn.isi.produitservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sn.isi.produitservice.entity.Produit;
import sn.isi.produitservice.service.ProduitServiceImplementation;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/produits")
@RequiredArgsConstructor
public class ProduitController {

    private final ProduitServiceImplementation produitServiceImplementation;

    @PostMapping
    public Produit create(@Valid @RequestBody Produit produit) {
        log.info("POST /produits - création d'un produit");
        return produitServiceImplementation.save(produit);
    }

    @GetMapping("/{id}")
    public Produit getById(@PathVariable Long id) {
        log.info("GET /produits/{} - consultation d'un produit", id);
        return produitServiceImplementation.findById(id);
    }

    @GetMapping
    public List<Produit> getAll() {
        log.info("GET /produits - liste des produits");
        return produitServiceImplementation.findAll();
    }
}