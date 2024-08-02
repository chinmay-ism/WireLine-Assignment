package com.chinmay.wireline.data.remote

data class PexelDto(
    val id: Int,
    val src: PexelSrcDto,
    val alt: String
)

data class PexelSrcDto(
    val small: String
)


