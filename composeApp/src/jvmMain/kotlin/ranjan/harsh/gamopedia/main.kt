package ranjan.harsh.gamopedia

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ranjan.harsh.gamopedia.di.initKoin

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Gamopedia",
    ) {
        initKoin ()
        App()
    }
}