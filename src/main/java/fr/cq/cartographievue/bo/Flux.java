package fr.cq.cartographievue.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
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
@Table(name = "flux", indexes = {
        @Index(name = "idx_flux_site", columnList = "site"), // j'indexe sur site pour permettre une recherche + rapide
        @Index(name = "idx_flux_cle_logique", columnList = "cle_logique", unique = true)
})

public class Flux {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_flux")
    private Long idFlux;

    // Quand le flux est modifié la date est tracée ici
    @UpdateTimestamp
    @Column(name = "date_maj", nullable = true, columnDefinition = "DATETIME")
    private LocalDateTime dateMaj;

    // Utilise TINYINT(1) pour que MySQL stocke le booléen comme 0/1
    @Column(name = "actif", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean actif = true;

    // Identifiant logique unique du flux (ex: hash composite)
    @Column(name = "cle_logique", nullable = false)
    private String cleLogique;

    @Column(name = "site", nullable = false, length = 100)
    private String site;

    @Column(name = "categorie", nullable = true, length = 100)
    private String categorie;

    @Column(name = "app_emettrice_protocole", nullable = true, length = 255)
    private String appEmettriceProtocole;

    @Column(name = "app_emettrice_format", nullable = true, length = 150)
    private String appEmettriceFormat;

    @Column(name = "app_emettrice_param", nullable = true, columnDefinition = "TEXT") // généralement très long
    private String appEmettriceParam;

    @Column(name = "app_destinataire_protocole", nullable = true, length = 255)
    private String appDestinataireProtocole;

    @Column(name = "app_destinataire_format", nullable = true, length = 150)
    private String appDestinataireFormat;

    @Column(name = "app_destinataire_param", nullable = true, columnDefinition = "TEXT") // généralement très long
    private String appDestinataireParam;

    @Column(name = "transformation", nullable = true, length = 255)
    private String transformation;

    @Column(name = "commentaire_flux", nullable = true, columnDefinition = "TEXT")
    private String commentaireFlux;

    @Column(name = "process_flux", nullable = true, length = 255)
    private String processFlux;

    // --- Relations --- //
    // Plusieurs flux appartiennent à un import
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_import", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private FluxImport importSource;

    // Application émettrice
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_app_emettrice", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Application appEmettrice;

    // Application destinataire
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_app_destinataire", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Application appDestinataire;

    // Application intermediaire
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_app_intermediaire")
    private Application appIntermediaire;

    // Un flux peut avoir plusieurs attributs dynamiques
    @OneToMany(mappedBy = "flux", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private List<FluxAttribut> attributs = new ArrayList<>();

    // Utilitaire qui permet d'afficher qu'information que le champ renvoit bien une
    // info mais qu'on l'affiche pas dans le front,
    // n'est pas contenu dans la base de données
    @Transient
    private String champCorrespondant;

    public String getChampCorrespondant() {
        return champCorrespondant;
    }

    public void setChampCorrespondant(String champCorrespondant) {
        this.champCorrespondant = champCorrespondant;
    }

}
