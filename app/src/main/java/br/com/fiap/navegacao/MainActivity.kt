package br.com.fiap.navegacao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.navegacao.screens.LoginScreen
import br.com.fiap.navegacao.screens.MenuScreen
import br.com.fiap.navegacao.screens.PedidosScreen
import br.com.fiap.navegacao.screens.PerfilScreen
import br.com.fiap.navegacao.ui.theme.NavegaçãoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavegaçãoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "login"){
                        composable(route = "login"){ LoginScreen(navController)}
                        composable(route = "menu") { MenuScreen(navController)}
                        composable(route = "pedidos") { PedidosScreen(navController)}
                        composable(route = "perfil") { PerfilScreen(navController)}
                    }
                }
            }
        }
    }
}

