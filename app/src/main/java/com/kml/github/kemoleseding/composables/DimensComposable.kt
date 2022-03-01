package com.kml.github.kemoleseding.composables

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalConfiguration
import com.kml.github.kemoleseding.TypeDimensions
import com.kml.github.kemoleseding.typeDimensionPhone
import com.kml.github.kemoleseding.typeDimensionTablet

@Composable
fun ProvideDimens(
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