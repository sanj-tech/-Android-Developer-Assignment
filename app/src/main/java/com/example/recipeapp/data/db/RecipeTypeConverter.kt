package com.example.recipeapp.data.db

import androidx.room.TypeConverters
import androidx.room.TypeConverter

@TypeConverters
class RecipeTypeConverter {

    @TypeConverter
    fun anyToStringConverter(attribute: Any?):String {
        if (attribute == null)
            return ""
        return attribute as String
    }

    @TypeConverter
    fun stringToAnyConverter(attributes: String?):Any {
        if (attributes == null)
            return ""
        return attributes
    }

}