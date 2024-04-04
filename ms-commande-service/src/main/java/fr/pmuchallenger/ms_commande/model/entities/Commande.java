package fr.pmuchallenger.ms_commande.model.entities;

import fr.pmuchallenger.ms_commande.model.CommandeStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "commandes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "commande_date", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "commande_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CommandeStatus commandeStatus;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommandeItem> commandeItems;
}
