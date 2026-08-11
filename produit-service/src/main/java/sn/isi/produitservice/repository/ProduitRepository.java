package sn.isi.produitservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.isi.produitservice.entity.Produit;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
}
