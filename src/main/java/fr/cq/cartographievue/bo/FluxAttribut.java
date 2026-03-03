package fr.cq.cartographievue.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "flux_attribut")
@EqualsAndHashCode
public class FluxAttribut {// Gère l'arrivée des futures colonnes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_attribut", nullable = false)
    private Long idAttribut;

    @Column(name = "nom", unique = true) // si un nouveau flux arrive le nom de la colonne est stocké ici
    private String nom;

    @Column(name = "valeur", nullable = true) // permet de stocké ce qu'il y a dans la colonne peu importe le type
    private String valeur;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_attribut", nullable = false) // essaie de deviner le type en fonction des données contenues
                                                      // dans valeur
    private TypeAttribut typeAttribut;

    // --- Relations --- //
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_flux", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Flux flux;
}
