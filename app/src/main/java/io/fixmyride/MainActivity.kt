package io.fixmyride

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.fixmyride.ui.screens.AddVehicleScreen
import io.fixmyride.ui.screens.HomeScreen
import io.fixmyride.ui.screens.NotificationsScreen
import io.fixmyride.ui.screens.SelectedVehicleScreen
import io.fixmyride.ui.screens.SettingsScreen
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.FixMyRideTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FixMyRideTheme {
                Surface(
                    color = ColorPalette.background,
                    modifier = Modifier.fillMaxSize()
                ) { App() }
            }
        }
    }
}

@Composable
private fun App() {
    val navCtrl = rememberNavController()

    NavHost(
        navController = navCtrl,
        startDestination = "/home",
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
    ) {
        composable("/home") { HomeScreen(navCtrl)  }
        composable("/notifications") { NotificationsScreen(navCtrl)  }
        composable("/add-vehicle") { AddVehicleScreen(navCtrl) }
        composable("/settings") { SettingsScreen(navCtrl) }
        composable("/selected-vehicle") { SelectedVehicleScreen(navCtrl) }
    }
}
