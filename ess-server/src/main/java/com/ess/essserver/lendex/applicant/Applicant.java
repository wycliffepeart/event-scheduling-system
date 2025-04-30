package com.ess.essserver.lendex.applicant;

import com.ess.essserver.lendex.address.Address;
import com.ess.essserver.lendex.contact.Contact;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Applicant {

    // Personal Information
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private LocalDate dateOfBirth;
    private String nationality;


    List<Address> addresses;
    List<Contact> contacts;
}
