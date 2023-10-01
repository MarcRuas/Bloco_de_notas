package com.marco.blocodenotas.ui.view

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marco.blocodenotas.R
import com.marco.blocodenotas.datastore.StoreAnotacao
import com.marco.blocodenotas.ui.theme.Azul
import com.marco.blocodenotas.ui.theme.BlocoDeNotasTheme
import com.marco.blocodenotas.ui.theme.White
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val storeAnotacao = StoreAnotacao(context)
    val anotacaoSalva = storeAnotacao.getAnotacao.collectAsState(initial = "")

    var anotacao by rememberSaveable {
        mutableStateOf("")
    }

    anotacao = anotacaoSalva.value

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
                          scope.launch {
                              storeAnotacao.salvarAnotacao(anotacao)
                              Toast.makeText(context, "Anotação salva com sucesso", Toast.LENGTH_SHORT).show()
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
        },

        containerColor = Azul
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            TextField(
                value = anotacao,
                onValueChange = { anotacao = it },
                label = { Text(text = "Digite sua anotação...") },
                modifier = Modifier.fillMaxSize(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Azul,
                    cursorColor = White,
                    focusedLabelColor = Azul,
                    textColor = White,
                    unfocusedLabelColor = White,
                    unfocusedIndicatorColor = Azul,
                    focusedIndicatorColor = Azul
                )
                )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

@Preview
@Composable
fun HomeScreenPreviewDarkMode() {
    BlocoDeNotasTheme(darkTheme = true) {
        HomeScreen()
    }
}