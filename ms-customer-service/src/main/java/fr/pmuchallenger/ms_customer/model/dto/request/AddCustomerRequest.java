package fr.pmuchallenger.ms_customer.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
/*
 * @author: Ibrahima DIALLO
 */

@Getter
@Setter
public class AddCustomerRequest {
    @NotBlank(message = "Full name is mandatory")
    @JsonProperty("full_name")
    private String fullName;

    @Email
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotBlank(message = "Phone is mandatory")
    private String phone;

    @NotEmpty(message = "Addresses is mandatory")
    private List<String> addresses;

    @NotEmpty(message = "Card numbers is mandatory")
    @JsonProperty("card_numbers")
    private List<String> cardNumbers;
}
