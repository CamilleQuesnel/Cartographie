package fr.cq.cartographievue.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "import")
public class FluxImport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_import")
    private Long idImport;

    @CreationTimestamp
    @Column(name = "date_import", nullable = false, updatable = false)
    private LocalDateTime dateImport;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false)
    private Statut statut;

    @Column(name = "log_execution", nullable = false, columnDefinition = "TEXT")
    private String logExecution;

    // --- Relations --- //
    // L’import est réalisé par un utilisateur
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_utilisateur", foreignKey = @ForeignKey(name = "fk_import_utilisateur"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Utilisateur utilisateur;

    // Un import contient plusieurs flux
    @OneToMany(mappedBy = "importSource", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private List<Flux> fluxList = new ArrayList<>();

}
