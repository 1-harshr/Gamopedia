package ranjan.harsh.gamopedia.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ranjan.harsh.game.ui.game.GameScreen
import ranjan.harsh.game.ui.gameDetails.GameDetailsScreen

object GameNavGraph: BaseNavGraph {
    sealed class Dest(val route: String){
        data object Root: Dest("/game-root")
        data object Game: Dest("/game")

        data object Details : Dest("/game_details/{id}") {
            fun getRoute(id: Int) = "/game_details/${id}"
        }
    }
    override fun build(
        modifier: Modifier,
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            startDestination = Dest.Game.route,
            route = Dest.Root.route
        ){
            composable(
                route = Dest.Game.route,
            ){
                GameScreen(
                    modifier = modifier.fillMaxSize(),
                    onFavoriteClick = {},
                    onSearchClick = {
                        navController.navigate(SearchNavGraph.Dest.Search.route)
                    },
                    onClick = {
                        navController.navigate(Dest.Details.getRoute(it))
                    }
                )
            }

            composable(
                route = Dest.Details.route,
            ){
                val id = it.arguments?.getString("id")
                GameDetailsScreen(
                    modifier = modifier.fillMaxSize(),
                    id = id.toString(),
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }

        }

    }
}