package br.com.kelvingcr.easymarket.converters

import androidx.room.TypeConverter
import br.com.kelvingcr.easymarket.model.ItemModel
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun listToJson(value: List<ItemModel>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<ItemModel>::class.java).toList()

}