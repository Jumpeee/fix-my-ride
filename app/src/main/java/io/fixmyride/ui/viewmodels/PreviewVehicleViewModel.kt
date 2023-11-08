package io.fixmyride.ui.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import io.fixmyride.data.models.Vehicle
import io.fixmyride.data.repositories.VehicleRepository

@RequiresApi(Build.VERSION_CODES.O)
class PreviewVehicleViewModel(
    val navController: NavController,
    private val vehicleId: Int,
    private val repo: VehicleRepository,
) : ViewModel() {
    private val _vehicle = mutableStateOf(Vehicle.EMPTY)

    val vehicle: State<Vehicle> = _vehicle

    suspend fun loadVehicle() {
        _vehicle.value = repo.getVehicleById(vehicleId)
    }
}