package fr.pmuchallenger.ms_commande.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "commande_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommandeItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "commande_id")
    private Commande commande;
}
