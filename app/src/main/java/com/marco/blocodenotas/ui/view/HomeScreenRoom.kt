package com.marco.blocodenotas.ui.view

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.marco.blocodenotas.R
import com.marco.blocodenotas.Validacao
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
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                }
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    scope.launch(Dispatchers.IO) {
                        sucess = validacao.criarNota(titulo, anotacao, context)

                        withContext(Dispatchers.Main) {
                            if (sucess){
                                navController.popBackStack()
                            }
                            else{
                                Toast.makeText(context, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                },
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 8.dp
                ),
                shape = CircleShape,

                ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_salvar),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
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