package sn.isi.commandeservice.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sn.isi.commandeservice.client.ClientFeignClient;
import sn.isi.commandeservice.client.ProduitFeignClient;
import sn.isi.commandeservice.dto.ClientDTO;
import sn.isi.commandeservice.dto.CommandeDTO;
import sn.isi.commandeservice.dto.ProduitDTO;
import sn.isi.commandeservice.entity.Commande;
import sn.isi.commandeservice.exception.ClientNotFoundException;
import sn.isi.commandeservice.exception.CommandeNotFoundException;
import sn.isi.commandeservice.exception.ProduitNotFoundException;
import sn.isi.commandeservice.repository.CommandeRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommandeServiceImplementation implements ICommandeService {

    private final CommandeRepository commandeRepository;
    private final ClientFeignClient clientFeignClient;
    private final ProduitFeignClient produitFeignClient;

    @Override
    public Commande save(Commande commande) {
        log.info("Début de création d'une commande : clientId={}, produitId={}, quantite={}",
                commande.getClientId(), commande.getProduitId(), commande.getQuantite());

        ClientDTO client = fetchClient(commande.getClientId());
        ProduitDTO produit = fetchProduit(commande.getProduitId());

        commande.setMontant(produit.getPrix() * commande.getQuantite());
        log.debug("Montant calculé : {} ({} x {})",
                commande.getMontant(), produit.getPrix(), commande.getQuantite());

        Commande saved = commandeRepository.save(commande);
        log.info("Commande enregistrée avec succès : id={}, montant={}", saved.getId(), saved.getMontant());
        return saved;
    }

    @Override
    public CommandeDTO findById(Long id) {
        log.info("Recherche de la commande id={}", id);

        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Aucune commande trouvée avec id={}", id);
                    return new CommandeNotFoundException(id);
                });

        ClientDTO client = fetchClient(commande.getClientId());
        ProduitDTO produit = fetchProduit(commande.getProduitId());

        log.info("Commande id={} reconstituée avec ses informations client et produit", id);

        return new CommandeDTO(
                commande.getId(),
                commande.getClientId(),
                commande.getProduitId(),
                commande.getQuantite(),
                commande.getMontant(),
                client,
                produit
        );
    }

    @Override
    public List<Commande> findAll() {
        log.info("Récupération de toutes les commandes");
        List<Commande> commandes = commandeRepository.findAll();
        log.info("{} commande(s) récupérée(s)", commandes.size());
        return commandes;
    }

    // Méthodes private

    private ClientDTO fetchClient(Long clientId) {
        log.debug("Appel au client-service pour clientId={}", clientId);
        try {
            return clientFeignClient.getClientById(clientId);
        } catch (FeignException.NotFound e) {
            log.warn("Client introuvable dans client-service : clientId={}", clientId);
            throw new ClientNotFoundException(clientId);
        }

    }

    private ProduitDTO fetchProduit(Long produitId) {
        log.debug("Appel au produit-service pour produitId={}", produitId);
        try {
            return produitFeignClient.getProduitById(produitId);
        } catch (FeignException.NotFound e) {
            log.warn("Produit introuvable dans produit-service : produitId={}", produitId);
            throw new ProduitNotFoundException(produitId);
        }
    }
}