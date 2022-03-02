package com.kml.github.kemoleseding

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

// https://proandroiddev.com/supporting-different-screen-sizes-on-android-with-jetpack-compose-f215c13081bd

class TypeDimensions(
    val typeSizeContent: TextUnit,
    val typeSizeTitle: TextUnit
)


val typeDimensionPhone = TypeDimensions(
    typeSizeContent =  12.sp,
    typeSizeTitle =  18.sp
)

val typeDimensionTablet = TypeDimensions(
    typeSizeContent = 18.sp,
    typeSizeTitle = 28.sp
)