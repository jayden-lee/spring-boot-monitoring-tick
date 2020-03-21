package com.jayden.monitoring.domain

import javax.persistence.*

@Entity
@Table
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column
    var name: String? = null
)
