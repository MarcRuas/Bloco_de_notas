package com.marco.blocodenotas.ui.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.marco.blocodenotas.model.Notas

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotaItem(
    position: Int,
    notaList: MutableList<Notas>,
    navController: NavController
) {

    val titulo = notaList[position].titulo
    val anotacao = notaList[position].anotacao

    ElevatedCard(
        onClick = {
                  navController.navigate("atualizarTela")
                  }, modifier = Modifier
            .padding(start = 10.dp, top = 20.dp, end = 10.dp, bottom = 10.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 16.dp
        )
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(all = 20.dp)
        ) {
            val (txtTitulo, txtAnotacao) = createRefs()

            Text(text = titulo, modifier = Modifier.constrainAs(txtTitulo) {
                top.linkTo(parent.top, margin = 10.dp)
                start.linkTo(parent.start, margin = 5.dp)
            })
            Text(text = anotacao, modifier = Modifier.constrainAs(txtAnotacao) {
                top.linkTo(txtTitulo.bottom, margin = 10.dp)
                start.linkTo(parent.start, margin = 5.dp)
            }
            )
        }
    }
}