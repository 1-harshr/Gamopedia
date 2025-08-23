package ranjan.harsh.gamopedia.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ranjan.harsh.search.ui.SearchScreen

object SearchNavGraph: BaseNavGraph {
    sealed class Dest(val route: String){
        data object Root: Dest("/search-root")
        data object Search: Dest("/search")
    }

    override fun build(
        modifier: Modifier,
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            route = Dest.Root.route,
            startDestination = Dest.Search.route
        ) {

            composable(
                route = Dest.Search.route,
                enterTransition = { 
                    slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(300)
                    )
                },
                exitTransition = { 
                    slideOutHorizontally(
                        targetOffsetX = { -it },
                        animationSpec = tween(300)
                    )
                },
                popEnterTransition = { 
                    slideInHorizontally(
                        initialOffsetX = { -it },
                        animationSpec = tween(300)
                    )
                },
                popExitTransition = { 
                    slideOutHorizontally(
                        targetOffsetX = { it },
                        animationSpec = tween(300)
                    )
                }
            ) {

                SearchScreen(
                    modifier = modifier.fillMaxSize(),
                    onClick = {},
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }

        }

    }

}