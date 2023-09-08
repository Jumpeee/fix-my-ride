package io.fixmyride

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.fixmyride.database.DatabaseManager
import io.fixmyride.enums.ManageVehicleType
import io.fixmyride.models.Vehicle
import io.fixmyride.ui.screens.HomeScreen
import io.fixmyride.ui.screens.ManageVehicleScreen
import io.fixmyride.ui.screens.NotificationsScreen
import io.fixmyride.ui.screens.SettingsScreen
import io.fixmyride.ui.screens.ViewVehicleScreen
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.FixMyRideTheme


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DatabaseManager.initialize(this)
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun App() {
    val navCtrl = rememberNavController()

    NavHost(
        navController = navCtrl,
        startDestination = "/home",
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
    ) {
        composable("/home") { HomeScreen(navCtrl) }
        composable("/notifications") { NotificationsScreen(navCtrl) }
        composable("/settings") { SettingsScreen(navCtrl) }
        composable("/add-vehicle") { ManageVehicleScreen(navCtrl, ManageVehicleType.ADD, null) }
        composable(
            "/edit-vehicle/{vehicle}",
            arguments = listOf(navArgument("vehicle") { type = NavType.StringType }),
        ) {
            val vehicle = it.arguments?.getString("vehicle") ?: ""
            ManageVehicleScreen(
                navCtrl,
                ManageVehicleType.EDIT,
                Vehicle.from(vehicle),
            )
        }
        composable(
            "/selected-vehicle/{vehicle}",
            arguments = listOf(navArgument("vehicle") { type = NavType.StringType }),
        ) {
            val vehicle = it.arguments?.getString("vehicle") ?: ""
            ViewVehicleScreen(
                navCtrl,
                Vehicle.from(vehicle.replace("", "")),
            )
        }
    }
}
