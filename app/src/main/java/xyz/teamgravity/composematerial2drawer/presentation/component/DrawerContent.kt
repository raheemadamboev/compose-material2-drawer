package xyz.teamgravity.composematerial2drawer.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import xyz.teamgravity.composematerial2drawer.data.model.MenuModel

@Composable
fun DrawerContent(
    menus: List<MenuModel>,
    onMenuClick: (menu: MenuModel) -> Unit,
) {
    Column {
        DrawerHeader()
        DrawerBody(
            menus = menus,
            onMenuClick = onMenuClick
        )
    }
}