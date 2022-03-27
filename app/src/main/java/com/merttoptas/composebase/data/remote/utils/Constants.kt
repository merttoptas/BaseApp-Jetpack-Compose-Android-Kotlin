package com.merttoptas.composebase.data.remote.utils

/**
 * Created by merttoptas on 10.03.2022
 */
object Constants {
    const val BASE_URL = "https://rickandmortyapi.com/api/"
    const val CHARACTER_LIST = "character"
    const val GET_CHARACTER = "character/{id}"
    const val Location_LIST = "location"
    const val EPISODE_LIST = "episode"
    const val GET_EPISODE = "episode/{id}"
    const val MULTIPLE_EPISODE_LIST = "episode/{[0-9]}"

    //Query Parameter
    const val PARAM_PAGE = "page"
    const val PARAM_ID = "id"

    const val TABLE_NAME = "favorite"
    const val COLUMN_ID = "id"
    const val COLUMN_NAME = "name"
    const val COLUMN_IMAGE_URL = "image_url"
    const val COLUMN_CREATED = "created"
    const val PREF_ORIGIN = "origin_"
    const val PREF_LOCATION = "location_"
    const val COLUMN_STATUS = "status"
    const val COLUMN_SPECIES = "species"
    const val COLUMN_GENDER = "gender"
    const val COLUMN_TYPE = "type"
    const val COLUMN_URL = "url"
    const val COLUMN_EPISODE = "episode"
}