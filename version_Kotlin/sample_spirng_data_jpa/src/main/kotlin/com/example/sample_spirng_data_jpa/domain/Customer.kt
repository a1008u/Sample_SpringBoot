package com.example.sample_spirng_data_jpa.domain

import javax.persistence.*

/**
 * Hibernateを利用すると、クラス、フィールド名はキャメルケースからスネークケースへ変換される
 * プリミティブ型に対するマッピングでは、NOT NULL制約が自動的に追加される
 *
 */

@Entity
@Table(name = "customers")
data class Customer(@Id @GeneratedValue var id: Int = -1
                    ,@Column val firstName:String = "test"
                    ,@Column val lastName:String = "test")