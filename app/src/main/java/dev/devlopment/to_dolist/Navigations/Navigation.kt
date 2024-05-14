package dev.devlopment.to_dolist.Navigations

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.devlopment.to_dolist.MVVM.WishViewModel
import dev.devlopment.to_dolist.UiandView.AddEditDetailView
import dev.devlopment.to_dolist.UiandView.HomeView

@Composable
fun Navigation(viewModel: WishViewModel = viewModel(),
               navController: NavHostController = rememberNavController()){
    NavHost(
        navController= navController,
        startDestination = Screen.HomeScreen.route
    ){
        composable(Screen.HomeScreen.route){
            HomeView(navController, viewModel)
        }

        composable(Screen.AddScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.LongType
                    defaultValue = 0L
                    nullable = false
                }
            )
        ){entry->
            val id = if(entry.arguments != null)  entry.arguments!!.getLong("id") else 0L
            AddEditDetailView(id = id, viewModel = viewModel , navController = navController)
        }
    }
}