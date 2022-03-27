package com.merttoptas.composebase.data.model.dto.extension

import com.merttoptas.composebase.data.model.*
import com.merttoptas.composebase.data.model.dto.CharacterDto
import com.merttoptas.composebase.data.model.dto.LocationDto

/**
 * Created by merttoptas on 27.03.2022
 */

fun Result.toCharacterDto() = CharacterDto(
    created,
    episode,
    gender,
    id,
    image,
    location.toLocationDto(),
    name,
    origin.toLocationDto(),
    species,
    status,
    type,
    url
)

fun List<Result>.toCharacterDtoList() = map { it.toCharacterDto() }

fun FavoriteEntity.toCharacterDto() = CharacterDto(
    created,
    episode,
    gender,
    id,
    imageUrl,
    location?.toLocationDto(),
    name,
    origin?.toLocationDto(),
    species,
    status,
    type,
    url
)

fun LocationEntity.toLocationDto() = LocationDto(
    locationId = url.getIdFromUrl(),
    name = name,
    url = url
)

fun LocationResponse.toLocationDto() = LocationDto(
    locationId = url?.getIdFromUrl() ?: 0,
    name = name.toString(),
    url = url.toString()
)

fun OriginResponse.toLocationDto() = LocationDto(
    locationId = url?.getIdFromUrl() ?: 0,
    name = name.toString(),
    url = url.toString()
)

fun String.getIdFromUrl(): Int = substring(lastIndexOf("/") + 1).toIntOrNull() ?: 0