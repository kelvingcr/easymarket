package br.com.kelvingcr.easymarket.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.kelvingcr.easymarket.model.CompraModel
import br.com.kelvingcr.easymarket.converters.Converters

@Database(entities = arrayOf(CompraModel::class), version = 1)
@TypeConverters(Converters::class)
abstract class CompraDatabase : RoomDatabase() {

    abstract fun compraDAO() : CompraDAO

    companion object {

        private lateinit var INSTANCE: CompraDatabase
        fun getDatabase(context: Context) : CompraDatabase {
            if (!Companion::INSTANCE.isInitialized) {
                //Previnir que duas threads não utilizem ao mesmo tempo
                synchronized(CompraDatabase::class) {
                INSTANCE = Room.databaseBuilder(context, CompraDatabase::class.java, "compraDB")
                    .allowMainThreadQueries()
                    .build()
            }
        }
            return INSTANCE
        }
    }
}