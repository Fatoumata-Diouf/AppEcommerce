package sn.isi.commandeservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sn.isi.commandeservice.client.ClientFeignClient;
import sn.isi.commandeservice.client.ProduitFeignClient;
import sn.isi.commandeservice.dto.ClientDTO;
import sn.isi.commandeservice.dto.CommandeDTO;
import sn.isi.commandeservice.dto.ProduitDTO;
import sn.isi.commandeservice.entity.Commande;
import sn.isi.commandeservice.repository.CommandeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandeServiceImplementation implements ICommandeService{

    private final CommandeRepository commandeRepository;
    private final ClientFeignClient clientFeignClient;
    private final ProduitFeignClient produitFeignClient;

    @Override
    public Commande save(Commande commande) {

        // Vérification du client
        ClientDTO client = clientFeignClient.getClientById(
                commande.getClientId()
        );

        if (client == null) {
            throw new RuntimeException(
                    "Client introuvable : " + commande.getClientId()
            );
        }


        // Vérification du produit
        ProduitDTO produit = produitFeignClient.getProduitById(
                commande.getProduitId()
        );

        if (produit == null) {
            throw new RuntimeException(
                    "Produit introuvable : " + commande.getProduitId()
            );
        }


        // Calcul du montant
        commande.setMontant(
                produit.getPrix() * commande.getQuantite()
        );


        // Enregistrement
        return commandeRepository.save(commande);
    }

    @Override
    public CommandeDTO findById(Long id) {

        Commande commande = commandeRepository.findById(id)
                .orElse(null);

        if (commande == null) {
            return null;
        }

        ClientDTO client = clientFeignClient.getClientById(
                commande.getClientId()
        );

        ProduitDTO produit = produitFeignClient.getProduitById(
                commande.getProduitId()
        );

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

        return commandeRepository.findAll();
    }
}
