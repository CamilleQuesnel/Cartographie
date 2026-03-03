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
@Table(name = "application", indexes = {
                @Index(name = "idx_application_nom", columnList = "nom")// j'indexe sur site pour permettre une
                                                                        // recherche + rapide
})
public class Application {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_application")
        private Long idApplication;

        @Column(name = "nom", nullable = false, length = 150)
        private String nom;

        @Column(name = "nom_affichage", nullable = true, length = 255) // modifiable uniquement par l'administrateur
        private String nomAffichage;

        @Column(name = "description_app", nullable = true, columnDefinition = "TEXT") // champ utilisé lors de la
                                                                                      // correspondance du flux pour
                                                                                      // expliciter le choix du nom
        private String descriptionApp;

        @CreationTimestamp
        @Column(name = "date_creation", updatable = false)
        private LocalDateTime dateCreation;

        @UpdateTimestamp
        @Column(name = "date_maj", columnDefinition = "DATETIME")
        private LocalDateTime dateMaj;

        // --- Relations --- //
        // Flux où cette application est émettrice
        @OneToMany(mappedBy = "appEmettrice")
        @ToString.Exclude
        @EqualsAndHashCode.Exclude
        @JsonIgnore
        private List<Flux> fluxEmis = new ArrayList<>();

        // Flux où cette application est destinataire
        @OneToMany(mappedBy = "appDestinataire")
        @ToString.Exclude
        @EqualsAndHashCode.Exclude
        @JsonIgnore
        private List<Flux> fluxRecus = new ArrayList<>();

}
