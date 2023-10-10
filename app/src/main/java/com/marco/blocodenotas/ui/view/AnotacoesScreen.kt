package com.marco.blocodenotas.ui.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.marco.blocodenotas.Validacao
import com.marco.blocodenotas.ui.components.BarraSuperior
import com.marco.blocodenotas.ui.components.BotaoFlutuante
import com.marco.blocodenotas.ui.components.NotaItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnotacoesScreen(navController: NavController) {

    val context = LocalContext.current
    val lerNotas = Validacao()
    lerNotas.ReadNota(context = context)

    Scaffold(
        topBar = {
            BarraSuperior()
        },

        floatingActionButton = {
            BotaoFlutuante {
                navController.navigate("salvarTela")
            }
        },
        containerColor = MaterialTheme.colorScheme.primary
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues
        ) {
            itemsIndexed(lerNotas.listaDeNotas) { position, _ ->
                NotaItem(
                    position = position,
                    notaList = lerNotas.listaDeNotas,
                    navController = navController
                )
            }
        }

    }
}