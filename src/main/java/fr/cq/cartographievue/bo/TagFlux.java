package fr.cq.cartographievue.bo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tag_flux")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagFlux {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 24, nullable = false)
    private String libelle;

    @Column(length = 20, nullable = false)
    private String couleur;

    @Column(nullable = false)
    private boolean publicTag = false;

    // Clé logique du flux – correspond à la colonne SQL `cle_logique`
    @Column(name = "cle_logique", length = 64, nullable = false)
    private String cleLogique;

    // Propriétaire (facultatif)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur proprietaire;

    @Transient
    private boolean peutSupprimer;

    public boolean isPeutSupprimer() {
        return peutSupprimer;
    }

    public void setPeutSupprimer(boolean peutSupprimer) {
        this.peutSupprimer = peutSupprimer;
    }

}
