package io.fixmyride.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import io.fixmyride.data.enums.SortType
import io.fixmyride.data.models.Vehicle
import io.fixmyride.data.repositories.VehicleRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    val navController: NavController,
    private val repo: VehicleRepository,

    ) : ViewModel() {
    private val _vehicles = mutableStateOf<List<Vehicle>>(emptyList())

    val vehicles: State<List<Vehicle>> = _vehicles

    suspend fun loadVehicles() {
        viewModelScope.launch {
            _vehicles.value = repo.getVehicles()
        }
    }

    fun setVehiclesOrder(sortType: SortType) {
        when (sortType) {
            SortType.MODEL -> {
                viewModelScope.launch { _vehicles.value = repo.getVehiclesOrderedByModel() }
            }

            SortType.REGISTRATION -> {
                viewModelScope.launch { _vehicles.value = repo.getVehiclesOrderedByRegistration() }
            }

            SortType.TPL_INSURANCE -> {
                viewModelScope.launch { _vehicles.value = repo.getVehiclesOrderedByTPLInsurance() }
            }

            SortType.COLLISION_INSURANCE -> {
                viewModelScope.launch { _vehicles.value = repo.getVehiclesOrderedByCIInsurance() }
            }

            SortType.NEXT_INSPECTION_DATE -> {
                viewModelScope.launch {
                    _vehicles.value = repo.getVehiclesOrderedByNextInspectionDate()
                }
            }
        }
    }
}