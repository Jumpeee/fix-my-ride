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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.fixmyride.database.DatabaseManager
import io.fixmyride.database.PrefsManager
import io.fixmyride.ui.screens.AddVehicleScreen
import io.fixmyride.ui.screens.EditVehicleScreen
import io.fixmyride.ui.screens.HomeScreen
import io.fixmyride.ui.screens.NotificationsScreen
import io.fixmyride.ui.screens.PreviewVehicleScreen
import io.fixmyride.ui.screens.SettingsScreen
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.FixMyRideTheme
import io.fixmyride.ui.viewmodels.AddVehicleViewModel
import io.fixmyride.ui.viewmodels.EditVehicleViewModel
import io.fixmyride.ui.viewmodels.HomeViewModel
import io.fixmyride.ui.viewmodels.NotificationsViewModel
import io.fixmyride.ui.viewmodels.PreviewVehicleViewModel
import io.fixmyride.utils.Routes

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DatabaseManager.initialize(this)
        PrefsManager.initialize(this)

        val prefs = PrefsManager.getInstance()
        if (prefs.getInt("notifications_days", -1) == -1) {
            prefs.edit().putInt("notifications_days", 7).apply()
        }

        setContent {
            FixMyRideTheme {
                Surface(
                    color = ColorPalette.background,
                    modifier = Modifier.fillMaxSize(),
                ) { App() }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
private fun App() {
    val navCtrl = rememberNavController()

    NavHost(
        navController = navCtrl,
        startDestination = Routes.HOME,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
    ) {
        composable(Routes.HOME) {
            val homeViewModel = remember { HomeViewModel(navCtrl) }
            HomeScreen(homeViewModel)
        }
        composable(Routes.NOTIFICATIONS) {
            val notificationsViewModel = remember { NotificationsViewModel(navCtrl) }
            NotificationsScreen(notificationsViewModel)
        }
        composable(Routes.SETTINGS) { SettingsScreen(navCtrl) }
        composable(Routes.ADD_VEHICLE) {
            val addVehicleViewModel = remember { AddVehicleViewModel(navCtrl) }
            AddVehicleScreen(addVehicleViewModel)
        }
        composable(
            route = "${Routes.EDIT_VEHICLE}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType }),
        ) {
            val vehicleId = it.arguments?.getString("id")?.toInt()
            val editVehicleViewModel = remember { EditVehicleViewModel(navCtrl, vehicleId!!) }
            EditVehicleScreen(editVehicleViewModel)
        }

        composable(
            route = "${Routes.SELECTED_VEHICLE}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType }),
        ) {
            val vehicleId = it.arguments?.getString("id")?.toInt()
            val previewVehicleViewModel = remember { PreviewVehicleViewModel(navCtrl, vehicleId!!) }
            PreviewVehicleScreen(previewVehicleViewModel)
        }
    }
}
