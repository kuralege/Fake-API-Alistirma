package com.example.apialistirma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apialistirma.screens.CommentScreen
import com.example.apialistirma.screens.PatchPostScreen
import com.example.apialistirma.screens.PostScreen
import com.example.apialistirma.screens.UpdatePostScreen
import com.example.apialistirma.ui.theme.ApiAlistirmaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApiAlistirmaTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}


@Composable
fun AppNavHost(navController: NavHostController){
    NavHost(navController, startDestination = "posts") {
        composable("posts") { PostScreen(navController = navController) }
        composable("comments") { CommentScreen() }
        composable("update/{postId}"){backStackEntry ->
            val postId = backStackEntry.arguments?.getString("postId")?.toInt() ?: 0
            UpdatePostScreen(postId)
        }
        composable("patch/{postId}") { backStackEntry ->
            val postId = backStackEntry.arguments?.getString("postId")?.toInt() ?: 0
            PatchPostScreen(postId)
        }
    }
}