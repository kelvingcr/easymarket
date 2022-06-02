package br.com.kelvingcr.easymarket.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Compra")
class CompraModel (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "nome")
    var nome: String,

    @ColumnInfo(name = "itens")
    var itens: List<ItemModel>,

    @ColumnInfo(name = "data")
    var data: String
        )


