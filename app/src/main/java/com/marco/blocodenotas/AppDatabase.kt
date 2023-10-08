package com.marco.blocodenotas

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marco.blocodenotas.constant.Constantes
import com.marco.blocodenotas.dao.NotasDao
import com.marco.blocodenotas.model.Notas

@Database(entities = [Notas::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notasDao(): NotasDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getIntance(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    Constantes.DB_NOTAs
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}