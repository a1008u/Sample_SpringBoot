package com.example.sample_4_jdbc.domain;

import com.sun.istack.internal.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {
    @Nullable
    private Integer id;
    private String firstName;
    private String lastName;
}
