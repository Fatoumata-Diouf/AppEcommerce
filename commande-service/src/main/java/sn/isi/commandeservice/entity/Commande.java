package sn.isi.commandeservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "commandes")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "L'id du client est obligatoire")
    private Long clientId;

    @Column(nullable = false)
    @NotBlank(message = "L'id du produit est obligatoire")
    private Long produitId;

    @Column(nullable = false)
    @NotBlank(message = "La quantité est obligatoire")
    @Min(1)
    private Integer quantite;

    private Double montant;



}
