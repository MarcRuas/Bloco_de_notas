package com.marco.blocodenotas.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.marco.blocodenotas.model.Notas
import com.marco.blocodenotas.ui.theme.DarkWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotaItem(
    position: Int,
    notaList: MutableList<Notas>,
    navController: NavController
) {

    val titulo = notaList[position].titulo
    val anotacao = notaList[position].anotacao
    val uid = notaList[position].uid


    ElevatedCard(
        onClick = {
                  navController.navigate("atualizarTela/${uid}")
        },
        modifier = Modifier
            .padding(start = 10.dp, top = 20.dp, end = 10.dp, bottom = 10.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 16.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(start = 20.dp, top = 10.dp, end = 20.dp, bottom = 20.dp)
        ) {
            val (txtTitulo, txtAnotacao, divider) = createRefs()

            Text(text = titulo, modifier = Modifier.constrainAs(txtTitulo) {
                top.linkTo(parent.top, margin = 0.dp)
                start.linkTo(parent.start, margin = 5.dp)
            }, fontSize = 22.sp)
            Divider(
                thickness = 2.dp,
                color = DarkWhite,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(divider) {
                        top.linkTo(txtTitulo.bottom, margin = 5.dp)
                    })
            Text(
                text = anotacao,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.constrainAs(txtAnotacao) {
                    top.linkTo(txtTitulo.bottom, margin = 15.dp)
                    start.linkTo(parent.start, margin = 5.dp)
                }
            )
        }
    }
}