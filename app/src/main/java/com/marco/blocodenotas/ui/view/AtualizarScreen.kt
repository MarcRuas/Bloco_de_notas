package com.marco.blocodenotas.ui.view

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.marco.blocodenotas.AppDatabase
import com.marco.blocodenotas.R
import com.marco.blocodenotas.Validacao
import com.marco.blocodenotas.dao.NotasDao
import com.marco.blocodenotas.ui.components.BotaoFlutuante
import com.marco.blocodenotas.ui.components.TextFieldComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


private lateinit var notasDao: NotasDao

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AtualizarScreen(
    navController: NavController,
    notaId: String
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

    LaunchedEffect(notaId) {
        val nota = withContext(Dispatchers.IO) {
            notasDao = AppDatabase.getIntance(context).notasDao()
            notasDao.getNotasSeparada(notaId)
        }
        if (nota != null) {
            titulo = nota.titulo
            anotacao = nota.anotacao
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = {
                        scope.launch(Dispatchers.IO){
                            notasDao.deletar(notaId.toInt())
                        }
                        scope.launch(Dispatchers.Main){
                            navController.popBackStack()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Ícone para deletar"
                        )
                    }
                }
            )
        },

        floatingActionButton = {
            BotaoFlutuante {
                scope.launch(Dispatchers.IO) {
                    sucess =
                        validacao.atualizarNota(
                            uid = notaId,
                            titulo = titulo,
                            anotacao = anotacao,
                            context = context
                        )
                    withContext(Dispatchers.Main) {
                        if (sucess) {
                            navController.popBackStack()
                        } else {
                            Toast
                                .makeText(context, "Preencha todos os campos!", Toast.LENGTH_SHORT)
                                .show()
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