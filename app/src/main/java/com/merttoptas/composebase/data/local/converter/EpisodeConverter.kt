package com.merttoptas.composebase.data.local.converter

import androidx.room.TypeConverter
import com.merttoptas.composebase.utils.Utility.fromJson
import com.merttoptas.composebase.utils.Utility.toJson

/**
 * Created by merttoptas on 27.03.2022
 */

class EpisodeConverter {
    @TypeConverter
    fun toListOfStrings(stringValue: String): List<String>? {
        return stringValue.fromJson()
    }

    @TypeConverter
    fun fromListOfStrings(listOfString: List<String>?): String {
        return listOfString.toJson()
    }
}

