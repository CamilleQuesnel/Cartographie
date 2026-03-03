package fr.cq.cartographievue.bo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "protocole_alias", uniqueConstraints = @UniqueConstraint(columnNames = { "cle_logique", "role" }))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProtocoleAlias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlias;

    @Column(name = "cle_logique", nullable = false)
    private String cleLogique;

    @Column(name = "role", nullable = false, length = 20)
    private String role; // EMETTEUR ou DESTINATAIRE

    @Column(name = "protocole_original", nullable = false, length = 255)
    private String protocoleOriginal;

    @Column(name = "protocole_renomme", nullable = false, length = 255)
    private String protocoleRenomme;

    @Column(name = "date_maj")
    private LocalDateTime dateMaj;
}
