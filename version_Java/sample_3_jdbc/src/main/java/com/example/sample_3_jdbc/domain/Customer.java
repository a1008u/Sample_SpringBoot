package com.example.sample_3_jdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {
    private Integer id;
    private String firstName;
    private String lastName;
}