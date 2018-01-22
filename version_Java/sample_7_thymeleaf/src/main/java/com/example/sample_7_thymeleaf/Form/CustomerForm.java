package com.example.sample_7_thymeleaf.Form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CustomerForm {
    @NotNull
    @Size(min = 1, max = 127)
    private String firstName;
    @NotNull
    @Size(min = 1, max = 127)
    private String lastName;
}