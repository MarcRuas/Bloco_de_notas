package com.marco.blocodenotas.ui.view

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.marco.blocodenotas.Validacao
import com.marco.blocodenotas.ui.components.BarraSuperior
import com.marco.blocodenotas.ui.components.BotaoFlutuante
import com.marco.blocodenotas.ui.components.TextFieldComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val validacao = Validacao()
    var sucess: Boolean

    var titulo by rememberSaveable {
        mutableStateOf("")
    }

    var anotacao by rememberSaveable {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            BarraSuperior()
        },

        floatingActionButton = {
            BotaoFlutuante {
                scope.launch(Dispatchers.IO) {
                    sucess = validacao.criarNota(titulo, anotacao, context)

                    withContext(Dispatchers.Main) {
                        if (sucess) {
                            navController.popBackStack()
                        } else {
                            Toast.makeText(
                                context,
                                "Preencha todos os campos!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.primary
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            TextFieldComponent(
                value = titulo,
                onValueChange = { titulo = it },
                label = "Digite o título da anotação!",
                singleLine = true
            )

            TextFieldComponent(
                value = anotacao,
                onValueChange = { anotacao = it },
                label = "Digite sua anotação",
                singleLine = false
            )
        }
    }
}