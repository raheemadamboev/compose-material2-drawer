package xyz.teamgravity.composematerial2drawer.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import xyz.teamgravity.composematerial2drawer.R
import xyz.teamgravity.composematerial2drawer.presentation.component.AppBar
import xyz.teamgravity.composematerial2drawer.presentation.component.DrawerContent
import xyz.teamgravity.composematerial2drawer.presentation.theme.ComposeMaterial2DrawerTheme
import xyz.teamgravity.composematerial2drawer.presentation.viewmodel.MainViewModel

class Main : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMaterial2DrawerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewmodel = viewModel<MainViewModel>()
                    val scaffold = rememberScaffoldState()
                    val scope = rememberCoroutineScope()

                    Scaffold(
                        scaffoldState = scaffold,
                        topBar = {
                            AppBar(
                                onNavigationClick = {
                                    scope.launch { scaffold.drawerState.open() }
                                }
                            )
                        },
                        floatingActionButton = {
                            FloatingActionButton(
                                onClick = viewmodel::onHeartCheckedChange,
                                backgroundColor = MaterialTheme.colors.primary
                            ) {
                                Icon(
                                    imageVector = if (viewmodel.heartChecked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                    contentDescription = stringResource(id = R.string.cd_favorite_button),
                                )
                            }
                        },
                        drawerContent = {
                            DrawerContent(
                                menus = viewmodel.menus,
                                onMenuClick = { menu ->
                                    scope.launch { scaffold.drawerState.close() }
                                    viewmodel.onSelectedMenuChange(menu)
                                }
                            )
                        }
                    ) { padding ->
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(padding)
                        ) {
                            Text(text = stringResource(id = viewmodel.selectedMenu.title))
                        }
                    }
                }
            }
        }
    }
}