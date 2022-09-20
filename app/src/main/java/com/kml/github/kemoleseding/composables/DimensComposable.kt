package com.kml.github.kemoleseding.composables

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalConfiguration
import com.kml.github.kemoleseding.ui.theme.TypeDimensions
import com.kml.github.kemoleseding.ui.theme.typeDimensionPhone
import com.kml.github.kemoleseding.ui.theme.typeDimensionTablet

@Composable
fun ProvideDimens( // https://proandroiddev.com/supporting-different-screen-sizes-on-android-with-jetpack-compose-f215c13081bd
    dimensions: TypeDimensions,
    content: @Composable () -> Unit
) {
    val theDimensionSet = remember { dimensions }
    CompositionLocalProvider(LocalAppDimensions provides theDimensionSet, content = content)
}

val LocalAppDimensions = staticCompositionLocalOf<TypeDimensions> { typeDimensionPhone }

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val configuration = LocalConfiguration.current
    val dimensions =
        if (configuration.screenWidthDp >= 400) typeDimensionTablet else typeDimensionPhone

    ProvideDimens(dimensions = dimensions) {
        MaterialTheme(
            content = content
        )
    }
}

object AppTheme {
    val dimens: TypeDimensions
        @Composable
        get() = LocalAppDimensions.current
}

val moreDimensions: TypeDimensions
    @Composable
    get() = AppTheme.dimens