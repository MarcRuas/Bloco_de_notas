package com.marco.blocodenotas.ui.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.marco.blocodenotas.R
import com.marco.blocodenotas.Validacao
import com.marco.blocodenotas.ui.components.NotaItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnotacoesScreen(navController: NavController) {

    val context = LocalContext.current
    val lerNotas = Validacao()
    lerNotas.ReadNota(context = context)

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
                    navController.navigate("salvarTela")
                },
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 8.dp
                ),
                shape = CircleShape,

                ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues
        ) {
            itemsIndexed(lerNotas.listaDeNotas) { position, item ->
                NotaItem(
                    position = position,
                    notaList = lerNotas.listaDeNotas,
                    navController = navController
                )
            }
        }

    }
}