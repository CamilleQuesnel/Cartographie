package fr.cq.cartographievue.bo;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "bloc_note_flux", uniqueConstraints = {
        @UniqueConstraint(name = "uq_note_unique", columnNames = { "cle_logique", "type_note", "id_utilisateur" })
})
public class BlocNoteFlux {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cle_logique", length = 64, nullable = false)
    private String cleLogique;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_note", length = 12, nullable = false)
    private TypeNote type;

    @Column(name = "contenu", columnDefinition = "TEXT")
    private String contenu;

    @UpdateTimestamp
    @Column(name = "date_maj")
    private LocalDateTime dateMaj;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur proprietaire;

    public enum TypeNote {
        PUBLIC,
        PRIVE
    }
}
