package com.chinmay.wireline.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PexelEntity(
    @PrimaryKey
    val id: Int,
    val smallSrc: String,
    val alt: String
)
