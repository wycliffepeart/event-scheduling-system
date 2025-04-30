package com.ess.essserver.lendex.identification;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Identification {
    private IdentificationType type;

    private String number;

    private LocalDate idExpiryDate;

    private String issuingCountry;

    private String image;

}
