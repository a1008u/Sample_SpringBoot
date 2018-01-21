package com.example.sample_layering.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
 * @AllArgsConstructor
 * 全フィールドを引数にもつコンストラクタを生成する
 */
@Data
@AllArgsConstructor
public class Customer {
    private Integer id;
    private String firstName;
    private String lastName;
}