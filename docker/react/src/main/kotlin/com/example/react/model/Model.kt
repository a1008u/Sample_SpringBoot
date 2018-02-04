package com.example.react.model

//@Table

// 初期値を入れている理由；https://qiita.com/ARBALEST000/items/0e0ef5074ae110120ac7#body%E3%81%AE%E4%BD%9C%E6%88%90
data class Employee (var id: Int = 0,
                     var name: String = "",
                     var age: Int = 0,
                     var department: String = "",
                     var salary: Double = 0.0)

data class EmployeeUpdateReq(val department: String = ""
                             , val salary: Double = 0.0)