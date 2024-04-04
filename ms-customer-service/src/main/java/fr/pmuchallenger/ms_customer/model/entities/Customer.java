package fr.pmuchallenger.ms_customer.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "full_name", nullable = false)
    @NotBlank(message = "Full name is required")
    private String fullName;

    @Column(name = "email", unique = true, nullable = false)
    @Email(message = "Invalid email address")
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone")
    private String phone;

    @ElementCollection
    @CollectionTable(name = "customer_addresses", joinColumns = @JoinColumn(name = "customer_id"))
    @Column(name = "address")
    private List<String> addresses;

    @ElementCollection
    @CollectionTable(name = "customer_card_numbers", joinColumns = @JoinColumn(name = "customer_id"))
    @Column(name = "card_number")
    private List<String> cardNumbers;
}
