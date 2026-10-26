package sn.isi.clientservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients")
public class Client {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Le nom et prenom sont obligatoires")
    private String prenomNom;

    @Column(unique = true,nullable = false)
    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Le format d'email n'est pas valide")
    private String email;


}
