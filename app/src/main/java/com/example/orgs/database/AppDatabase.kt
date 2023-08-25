package com.example.orgs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.orgs.database.conversor.Conversores
import com.example.orgs.database.dao.ProdutoDAO
import com.example.orgs.model.Produto

@Database(entities = [Produto::class], version = 1)
@TypeConverters(Conversores::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun produtoDao(): ProdutoDAO

    companion object {
        fun instancia(context:Context): AppDatabase{
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "orgs.db"
            ).allowMainThreadQueries()
                .build()
        }
    }
}