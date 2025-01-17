package com.theinkwell.server.domains.user.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PersonalProfileResponse {

    private String email;
    private String firstName;
    private String lastName;
    private LocalDate dob;

}
