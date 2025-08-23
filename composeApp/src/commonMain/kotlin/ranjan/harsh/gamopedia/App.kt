package ranjan.harsh.gamopedia

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import gamopedia.composeapp.generated.resources.Res
import gamopedia.composeapp.generated.resources.compose_multiplatform
import ranjan.harsh.gamopedia.navigation.GameNavGraph
import ranjan.harsh.gamopedia.navigation.SearchNavGraph

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navHostController = rememberNavController()

        NavHost(navHostController, startDestination = GameNavGraph.Dest.Root.route){
            listOf(
                GameNavGraph,
                SearchNavGraph
            ).forEach {
                it.build(
                    modifier = Modifier.fillMaxSize(),
                    navController = navHostController,
                    navGraphBuilder = this
                )
            }
        }
    }
}