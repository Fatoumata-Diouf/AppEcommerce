package sn.isi.produitservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sn.isi.produitservice.entity.Produit;
import sn.isi.produitservice.repository.ProduitRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProduitServiceImplementation implements IProduitService {

    private final ProduitRepository produitRepository;

    @Override
    public Produit save(Produit produit) {
        return produitRepository.save(produit);
    }

    @Override
    public Produit findById(Long id) {
        return produitRepository.findById(id)
                .orElse(null);
    }

    @Override
    public List<Produit> findAll() {
        return produitRepository.findAll();
    }
}
