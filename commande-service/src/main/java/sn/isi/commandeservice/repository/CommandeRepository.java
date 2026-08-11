package sn.isi.commandeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.isi.commandeservice.entity.Commande;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
}
