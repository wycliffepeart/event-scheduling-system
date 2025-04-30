package com.ess.essserver.lendex.address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    // Street Information
    private String streetAddress;
    private String streetNumber;
    private String apartmentNumber;

    // Area Information
    private String city;
    private String district;
    private String state;
    private String locality;

    // Postal and Country Details
    private String postalCode;
    private String country;

    // Additional Information
    private String landmark;
    private AddressType addressType;
    private String POBox;

    // Geolocation (Optional)
    private Double latitude;
    private Double longitude;

    private boolean isPrimary;
}
