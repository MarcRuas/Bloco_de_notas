package com.marco.blocodenotas

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.marco.blocodenotas.dao.NotasDao
import com.marco.blocodenotas.model.Notas
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private lateinit var notasDao: NotasDao

class Validacao(
    val listaDeNotas: MutableList<Notas> = mutableListOf()
) {
    fun criarNota(
        titulo: String,
        anotacao: String,
        context: Context
    ): Boolean {
        return if (titulo.isEmpty() || anotacao.isEmpty()) {
            false
        } else {
            val note = Notas(titulo, anotacao)
            listaDeNotas.add(note)
            notasDao = AppDatabase.getIntance(context).notasDao()
            notasDao.gravar(listaDeNotas)
            true
        }
    }

    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun ReadNota(context: Context) {
        val scope = rememberCoroutineScope()

        scope.launch(Dispatchers.IO) {
            notasDao = AppDatabase.getIntance(context).notasDao()
            val notasLista = notasDao.getNotas()
            for (nota in notasLista) {
                listaDeNotas.add(nota)
            }
        }
    }
}