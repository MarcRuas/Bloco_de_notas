package com.marco.blocodenotas.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.marco.blocodenotas.constant.Constantes

@Entity(tableName = Constantes.TABELA_NOTAS)
data class Notas(
    @ColumnInfo(name = "titulo") val titulo: String,
    @ColumnInfo(name = "anotacao") val anotacao: String
) {
    @PrimaryKey(autoGenerate = true)
        var uid: Int = 0
}