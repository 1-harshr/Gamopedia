package ranjan.harsh.game.ui.gameDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import be.digitalia.compose.htmlconverter.htmlToAnnotatedString
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GameDetailsScreen(
    modifier: Modifier = Modifier,
    id: String,
    onBackClick: () -> Unit
) {

    val viewModel = koinViewModel<GameDetailsViewModel>()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(id) {
        viewModel.getGameDetails(id.toInt())
    }

    GameDetailsScreenContent(
        modifier = modifier.fillMaxSize(), uiState = uiState.value,
        onDelete = { },
        onSave = { id, name, image -> },
        onBackClick = onBackClick
    )

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GameDetailsScreenContent(
    modifier: Modifier = Modifier, uiState:
    GameDetailsScreenState.UiState,
    onDelete: (Int) -> Unit,
    onSave: (id: Int, title: String, image: String) -> Unit,
    onBackClick: () -> Unit
) {
    if (uiState.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
            Text(text = "Loading...")
        }
    }

    if (uiState.error.isNotBlank()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(uiState.error)
        }
    }


    uiState.gameDetails?.let { data ->
        Box(modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize())
            {

                item {
                    AsyncImage(
                        model = data.backgroundImage, contentDescription = null,
                        modifier = Modifier.fillMaxWidth().height(350.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                item {
                    Text(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)
                            .fillMaxWidth(),
                        text = data.name,
                        style = MaterialTheme.typography.headlineLarge
                    )
                }

                item {
                    val htmlText = remember(data.description) { htmlToAnnotatedString(data.description) }
                    Text(
                        text = htmlText, style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                            .fillMaxWidth()
                    )
                }

                item {
                    Column(modifier = Modifier.fillMaxWidth()) {

                        Text(
                            text = "Platforms", style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(horizontal = 12.dp).padding(top = 24.dp)
                        )

                        LazyRow(modifier = Modifier.fillMaxWidth()) {
                            items(data.platforms) {
                                Card(
                                    modifier = Modifier.padding(12.dp).wrapContentSize().clickable{

                                    },
                                    shape = RoundedCornerShape(12.dp),
                                    elevation = cardElevation(
                                        defaultElevation = 12.dp
                                    )
                                ) {
                                    Column(
                                        modifier = Modifier.width(150.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        AsyncImage(
                                            model = it.image, contentDescription = null,
                                            modifier = Modifier
                                                .padding(top = 8.dp)
                                                .background(
                                                    color = Color.Transparent,
                                                    shape = CircleShape
                                                ).clip(CircleShape)
                                                .size(120.dp),
                                            contentScale = ContentScale.Crop
                                        )
                                        Text(
                                            modifier = Modifier.padding(vertical = 8.dp),
                                            text = it.name,
                                            style = MaterialTheme.typography.bodyLarge
                                        )

                                    }

                                }
                            }
                        }

                    }
                }



                item {
                    Text(
                        text = "Stores", style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(horizontal = 12.dp).padding(top = 24.dp)
                            .padding(bottom = 12.dp)
                    )
                }

                items(data.stores) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp)
                            .padding(bottom = 8.dp).fillMaxWidth()
                    ) {

                        AsyncImage(
                            model = it.image, contentDescription = null,
                            modifier = Modifier.size(120.dp)
                                .background(
                                    color = Color.Transparent,
                                    shape = RoundedCornerShape(12.dp)
                                ).clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(Modifier.width(8.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = it.name, style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = it.domain, style = MaterialTheme.typography.bodyLarge,
                                textDecoration = TextDecoration.Underline
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = "Gamecount: " + it.gameCount,
                                style = MaterialTheme.typography.bodySmall
                            )

                        }

                    }
                }


                item {
                    Text(
                        text = "Tags", style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(horizontal = 12.dp).padding(top = 24.dp)
                    )
                }

                item {
                    FlowRow(
                        modifier = Modifier.padding(horizontal = 12.dp).fillMaxWidth(),
                    ) {

                        data.tags.forEach {
                            Row(
                                modifier = Modifier.padding(top = 8.dp, end = 12.dp).background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(200.dp)
                                ).border(
                                    width = .5.dp,
                                    color = Color.LightGray,
                                    shape = RoundedCornerShape(200.dp)
                                )
                                    .clip(RoundedCornerShape(200.dp))
                                    .clickable{},
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = it.image,
                                    contentDescription = null,
                                    modifier = Modifier.size(35.dp)
                                        .background(color = Color.Transparent, shape = CircleShape)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(Modifier.width(4.dp))
                                Text(
                                    text = it.name, style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                            }
                        }

                    }
                }


                item {
                    Text(
                        text = "Developers", style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(horizontal = 12.dp)
                            .padding(top = 24.dp, bottom = 12.dp)
                    )
                }

                items(data.developers) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp).padding(bottom = 8.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.Top
                    ) {

                        AsyncImage(
                            model = it.image, contentDescription = null,
                            modifier = Modifier.size(120.dp)
                                .background(
                                    color = Color.Transparent,
                                    shape = RoundedCornerShape(12.dp)
                                ).clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(Modifier.width(8.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = it.name, style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = "Gamecount: " + it.gameCount,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }

                }


            }

            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            ) {

                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.background(color = Color.White, shape = CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack, contentDescription = null,
                        modifier = Modifier.padding(4.dp)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = {
                        onSave(data.id, data.name, data.backgroundImage)
                    },
                    modifier = Modifier.background(color = Color.White, shape = CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite, contentDescription = null,
                        modifier = Modifier.padding(4.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))


                IconButton(
                    onClick = {
                        onDelete(data.id)
                    },
                    modifier = Modifier.background(color = Color.White, shape = CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete, contentDescription = null,
                        modifier = Modifier.padding(4.dp)
                    )
                }

            }

        }


    }


}