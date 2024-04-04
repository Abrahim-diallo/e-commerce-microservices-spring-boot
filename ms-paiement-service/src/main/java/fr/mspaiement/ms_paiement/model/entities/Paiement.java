package fr.mspaiement.ms_paiement.model.entities;

import fr.mspaiement.ms_paiement.constants.PaiementStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "paiements")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Paiement implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "commande_id", nullable = false)
    private Long commandeId;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "paiement_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaiementStatus paiementStatus;

}
