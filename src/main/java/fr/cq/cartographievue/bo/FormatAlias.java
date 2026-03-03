package fr.cq.cartographievue.bo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "format_alias", uniqueConstraints = @UniqueConstraint(columnNames = { "cle_logique", "role" }))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormatAlias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlias;

    @Column(name = "cle_logique", nullable = false)
    private String cleLogique;

    @Column(name = "role", nullable = false, length = 20)
    private String role; // EMETTEUR ou DESTINATAIRE

    @Column(name = "format_original", nullable = false, length = 255)
    private String formatOriginal;

    @Column(name = "format_renomme", nullable = false, length = 255)
    private String formatRenomme;

    @Column(name = "date_maj")
    private LocalDateTime dateMaj;
}
