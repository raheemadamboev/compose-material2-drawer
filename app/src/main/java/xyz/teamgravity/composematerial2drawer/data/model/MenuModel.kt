package xyz.teamgravity.composematerial2drawer.data.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class MenuModel(
    val id: String,
    @StringRes val title: Int,
    @StringRes val contentDescription: Int,
    val icon: ImageVector,
)