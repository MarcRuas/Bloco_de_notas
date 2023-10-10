package com.marco.blocodenotas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.marco.blocodenotas.ui.theme.BlocoDeNotasTheme
import com.marco.blocodenotas.ui.view.AnotacoesScreen
import com.marco.blocodenotas.ui.view.AtualizarScreen
import com.marco.blocodenotas.ui.view.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize().
                background(MaterialTheme.colorScheme.primary)
            ){
                BlocoDeNotasTheme {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "anotacaoTela"){
                        composable("anotacaoTela"){
                            AnotacoesScreen(navController = navController)
                        }
                        composable("salvarTela"){
                            HomeScreen(navController = navController)
                        }
                        composable(
                            "atualizarTela/{uid}",
                            arguments = listOf(navArgument("uid"){})
                        ){
                            AtualizarScreen(navController = navController, it.arguments?.getString("uid").toString())
                        }
                    }
                }
            }
        }
    }
}

