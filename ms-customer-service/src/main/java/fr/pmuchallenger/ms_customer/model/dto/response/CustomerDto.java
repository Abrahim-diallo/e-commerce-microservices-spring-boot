package fr.pmuchallenger.ms_customer.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CustomerDto {

    private Long id;
    @JsonProperty("full_name")
    private String fullName;

    private String email;

    private String password;

    private String phone;

    private List<String> addresses;

    @JsonProperty("card_numbers")
    private List<String> cardNumbers;
}
