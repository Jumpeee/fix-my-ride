package io.fixmyride.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import io.fixmyride.database.DatabaseManager
import io.fixmyride.enums.SortType
import io.fixmyride.models.Vehicle
import kotlinx.coroutines.launch

class HomeViewModel(
    val navController: NavController,
) : ViewModel() {
    private val _vehicles = mutableStateOf<List<Vehicle>>(emptyList())

    val vehicles: State<List<Vehicle>> = _vehicles

    suspend fun loadVehicles() {
        println(vehicles.value)
        viewModelScope.launch {
            val db = DatabaseManager.getInstance().dao
            _vehicles.value = db.getVehicles()
        }
    }

    fun setVehiclesOrder(sortType: SortType) {
        val db = DatabaseManager.getInstance().dao
        when (sortType) {
            SortType.MODEL -> {
                viewModelScope.launch { _vehicles.value = db.getVehiclesOrderedByModel() }
            }

            SortType.REGISTRATION -> {
                viewModelScope.launch { _vehicles.value = db.getVehiclesOrderedByRegistration() }
            }

            SortType.TPL_INSURANCE -> {
                viewModelScope.launch { _vehicles.value = db.getVehiclesOrderedByTPLInsurance() }
            }

            SortType.COLLISION_INSURANCE -> {
                viewModelScope.launch { _vehicles.value = db.getVehiclesOrderedByCIInsurance() }
            }

            SortType.NEXT_INSPECTION_DATE -> {
                viewModelScope.launch {
                    _vehicles.value = db.getVehiclesOrderedByNextInspectionDate()
                }
            }
        }
    }
}