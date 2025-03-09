package br.com.fiap.navegacao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
//import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.fiap.navegacao.screens.LoginScreen
import br.com.fiap.navegacao.screens.MenuScreen
import br.com.fiap.navegacao.screens.PedidosScreen
import br.com.fiap.navegacao.screens.PerfilScreen
import br.com.fiap.navegacao.ui.theme.NavegaçãoTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.animation.composable

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavegaçãoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberAnimatedNavController()
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = "login",
                        exitTransition = {
                            slideOutOfContainer(
                                towards = AnimatedContentScope.SlideDirection.End,
                                tween(2000)
                            ) + fadeOut(animationSpec = tween(2000))
                        },
                        enterTransition = {
                            slideIntoContainer(
                                towards = AnimatedContentScope.SlideDirection.Down,
                                animationSpec = tween(2000)
                            )
                        }
                    ) {
                        composable(route = "login") {
                            LoginScreen(navController)
                        }
                        composable(route = "menu") {
                            MenuScreen(navController)
                        }
                        composable(
                            route = "pedidos?numero={numero}",
                            arguments = listOf(navArgument(name = "numero") {
                                defaultValue = ""
                            })
                        ) {
                            PedidosScreen(navController, it.arguments?.getString("numero")!!)
                        }
                        composable(
                            route = "perfil/{nome}/{idade}",
                            arguments = listOf(
                                navArgument(name = "nome") {
                                    type = NavType.StringType
                                },
                                navArgument(name = "idade") {
                                    type = NavType.IntType
                                }
                            )
                        ) {
                            val nome = it.arguments?.getString("nome")
                            val idade = it.arguments?.getInt("idade")
                            PerfilScreen(
                                navController,
                                nome!!,
                                idade!!
                            ) // double bang, ela trata o nulo
                        }
                    }
                }
            }
        }
    }
}

