package dev.greenox

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import dev.greenox.data.HeroesRepository
import dev.greenox.model.Hero

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.displayLarge
            )
        }
    )
}

@Composable
fun SuperheroItem(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.shadow(
            elevation = dimensionResource(id = R.dimen.elevation_2),
            shape = MaterialTheme.shapes.medium,
        )
    ) {
        Box(
            modifier = Modifier
                .clip(shape = MaterialTheme.shapes.medium)
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
                .padding(all = dimensionResource(id = R.dimen.padding_16))
        ) {
            Row(
                modifier = Modifier
                    .height(height = dimensionResource(id = R.dimen.height_72))
            ) {
                Column(
                    modifier = Modifier.weight(weight = 2.5F)
                ) {
                    Text(
                        text = stringResource(id = hero.nameRes),
                        style = MaterialTheme.typography.displaySmall,
                    )
                    Text(
                        text = stringResource(id = hero.descriptionRes),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
                Spacer(modifier = Modifier.weight(weight = .5F))
                Box(
                    modifier = Modifier
                        .weight(weight = 1F)
                ) {
                    Image(
                        painter = painterResource(id = hero.imageRes),
                        contentDescription = stringResource(id = hero.nameRes),
                        modifier = Modifier
                            .clip(shape = MaterialTheme.shapes.medium)
                    )
                }
            }
        }
    }
}

@Composable
fun SuperheroListItem(
    modifier: Modifier = Modifier
) {
    val heroesRepository = HeroesRepository()

    val visibleState = remember {
        MutableTransitionState(initialState = false).apply {
            targetState = true
        }
    }

    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = DampingRatioLowBouncy)
        ),
        exit = fadeOut(),
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = dimensionResource(id = R.dimen.padding_16))
        ) {
            itemsIndexed(items = heroesRepository.heroes) {
                index, hero -> SuperheroItem(
                    hero = hero,
                    modifier = Modifier
                        .padding(vertical = dimensionResource(id = R.dimen.vertical_8))
                        .animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = Spring.StiffnessVeryLow,
                                    dampingRatio = DampingRatioLowBouncy
                                ),
                                initialOffsetY = { it  * (index + 1)}
                            ),
                        )
                )
            }
        }
    }
}