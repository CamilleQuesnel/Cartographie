package fr.cq.cartographievue.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilisateur")
    private Long idUtilisateur;

    @Column(name = "actif", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean actif = true; // actif par défaut

    @CreationTimestamp
    @Column(name = "date_ajout", nullable = false, updatable = false)
    private LocalDateTime dateAjout;

    @UpdateTimestamp
    @Column(name = "date_maj", nullable = false)
    private LocalDateTime dateMaj;

    @Column(name = "login_ad", nullable = false, unique = true, length = 100)
    private String loginAD;

    @Column(name = "nom", nullable = false, length = 100)
    private String nom;

    @Column(name = "email", nullable = false, unique = true, length = 150) // sera utilisé pr la tracabilité dans les
                                                                           // logs
    private String email;

    @Column(name = "role", nullable = false, length = 50)
    private String role = "USER"; // valeur par défaut

    // --- Relations --- //
    // Un utilisateur peut réaliser plusieurs imports
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.PERSIST, // ou même rien du tout
            orphanRemoval = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private List<FluxImport> fluxImports = new ArrayList<>();

}
