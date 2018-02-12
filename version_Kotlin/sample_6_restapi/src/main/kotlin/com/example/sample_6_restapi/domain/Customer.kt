package com.example.sample_6_restapi.domain

import javax.persistence.*


@Entity
@Table(name = "customers")
data class Customer(@Id @GeneratedValue var id: Int = -1
                    ,@Column val firstName:String = "test"
                    ,@Column val lastName:String = "test")