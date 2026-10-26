package sn.isi.produitservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sn.isi.produitservice.entity.Produit;
import sn.isi.produitservice.exception.ProduitNotFoundException;
import sn.isi.produitservice.repository.ProduitRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProduitServiceImplementation implements IProduitService {

    private final ProduitRepository produitRepository;

    @Override
    public Produit save(Produit produit) {
        log.info("Début de l'enregistrement d'un produit : {}", produit);
        try {
            Produit saved = produitRepository.save(produit);
            log.info("Produit enregistré avec succès, id={}", saved.getId());
            return saved;
        } catch (Exception e) {
            log.error("Erreur lors de l'enregistrement du produit : {}", produit, e);
            throw e;
        }
    }

    @Override
    public Produit findById(Long id) {
        log.info("Recherche du produit avec id={}", id);
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Aucun produit trouvé avec id={}", id);
                    return new ProduitNotFoundException(id);
                });
        log.debug("Produit trouvé : {}", produit);
        return produit;
    }

    @Override
    public List<Produit> findAll() {
        log.info("Récupération de tous les produits");
        List<Produit> produits = produitRepository.findAll();
        log.info("{} produit(s) récupéré(s)", produits.size());
        return produits;
    }
}