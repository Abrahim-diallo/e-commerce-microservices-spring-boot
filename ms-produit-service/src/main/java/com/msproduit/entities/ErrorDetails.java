package com.msproduit.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {

    private Date dateMessage;
    private String message;
    private String details;
    private int statusCode;

}