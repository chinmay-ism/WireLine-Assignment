package com.chinmay.wireline.data.mappers

import com.chinmay.wireline.data.local.PexelEntity
import com.chinmay.wireline.data.remote.PexelDto
import com.chinmay.wireline.domain.Pexel

fun PexelDto.toPexelEntity(): PexelEntity {
    return PexelEntity(
        id = id,
        smallSrc = src.small,
        alt = alt
    )
}

fun PexelEntity.toPexel(): Pexel {
    return Pexel(
        id = id,
        smallSrc = smallSrc,
        alt = alt
    )
}
