package com.marco.blocodenotas.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.marco.blocodenotas.model.Notas

@Dao
interface NotasDao {
    @Insert
    fun gravar(listaAnotacoes: MutableList<Notas>)

    @Query("SELECT * FROM tabela_notas ORDER BY titulo ASC")
    fun getNotas(): MutableList<Notas>

    @Query("UPDATE tabela_notas SET titulo = :novoTitulo, anotacao = :novaAnotacao WHERE uid = :id")
    fun atualizar(id: Int, novoTitulo: String, novaAnotacao: String)

    @Query("DELETE FROM tabela_notas WHERE uid = :id")
    fun deletar(id: Int)
}