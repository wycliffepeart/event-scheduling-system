package com.ess.essserver.lendex.identification;

public enum IdentificationType {
    NATIONAL_ID,           // National Identification Card (expected with the new NIDS system)
    PASSPORT,              // Passport
    DRIVER_LICENSE,        // Driver's License
    VOTER_ID,              // Electoral/Voter Registration ID
    STUDENT_ID,            // Student ID (for younger applicants)
    TAXPAYER_REGISTRATION, // Taxpayer Registration Number (TRN card)
    WORK_ID,               // Company/Employer-issued Work ID
    OTHER                  // Any other ID not explicitly listed

}
