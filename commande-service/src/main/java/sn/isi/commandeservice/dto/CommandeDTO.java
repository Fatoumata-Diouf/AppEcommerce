package sn.isi.commandeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandeDTO {

    private Long id;

    private Long clientId;

    private Long produitId;

    private Integer quantite;

    private Double montant;

    private ClientDTO client;

    private ProduitDTO produit;
}
