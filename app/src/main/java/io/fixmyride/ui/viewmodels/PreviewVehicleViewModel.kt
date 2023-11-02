package io.fixmyride.ui.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import io.fixmyride.database.DatabaseManager
import io.fixmyride.models.Vehicle

@RequiresApi(Build.VERSION_CODES.O)
class PreviewVehicleViewModel(
    val navController: NavController,
    private val vehicleId: Int,
) : ViewModel() {
    private val _vehicle = mutableStateOf(Vehicle.EMPTY)

    val vehicle: State<Vehicle> = _vehicle

    suspend fun loadVehicle() {
        val db = DatabaseManager.getInstance().dao
        _vehicle.value = db.getVehicleById(vehicleId)
    }
}