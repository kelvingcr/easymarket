package br.com.kelvingcr.easymarket.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.kelvingcr.easymarket.model.CompraModel

@Dao
interface CompraDAO {

    @Insert
    fun save(compra: CompraModel) : Long

    @Query("SELECT * FROM Compra")
    fun getAll() : List<CompraModel>

    @Query("SELECT * FROM Compra WHERE id = :id")
    fun get(id: Int) : CompraModel

    @Query("DELETE FROM Compra WHERE id = :id")
    fun delete(id: Int) : Int


}