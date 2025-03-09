package com.ess.essserver.module.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    private int id;

    private String username;

    private String firstName;

    private String lastName;

    private String role;

    private LocalDate dateOfBirth;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
