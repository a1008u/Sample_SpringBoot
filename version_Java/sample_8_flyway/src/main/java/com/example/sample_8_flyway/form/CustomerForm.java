package com.example.sample_8_flyway.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CustomerForm {
    @NotNull
    @Size(min = 1, max = 127,message = "{Customer.firstname.fail}")
    private String firstName;

    @NotNull
    @Size(min = 1, max = 127, message = "{Customer.lastname.fail}")
    private String lastName;
}

