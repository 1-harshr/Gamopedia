package ranjan.harsh.gamopedia.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ranjan.harsh.game.ui.game.GameScreen

object GameNavGraph: BaseNavGraph {
    sealed class Dest(val route: String){
        data object Root: Dest("/game-root")
        data object Game: Dest("/game")
    }
    override fun build(
        modifier: Modifier,
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            startDestination = Dest.Root.route,
            route = Dest.Game.route
        ){
            composable(route = Dest.Root.route){
                GameScreen(
                    modifier = modifier.fillMaxSize(),
                    onFavoriteClick = {},
                    onSearchClick = {},
                    onClick = {}
                )
            }

        }

    }
}