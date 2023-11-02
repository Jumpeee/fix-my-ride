package io.fixmyride.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import io.fixmyride.database.DatabaseManager
import io.fixmyride.enums.SortType
import io.fixmyride.models.Vehicle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    val navController: NavController,
) : ViewModel() {
    private val _vehicles = mutableStateOf<List<Vehicle>>(emptyList())
    val vehicles: State<List<Vehicle>> = _vehicles

    suspend fun loadVehicles() {
        println(vehicles.value)
        CoroutineScope(Dispatchers.Default).launch {
            val db = DatabaseManager.getInstance().dao
            _vehicles.value = db.getVehicles()
        }
    }

    fun setVehiclesOrder(sortType: SortType) {
        val db = DatabaseManager.getInstance().dao
        val coroutineScope = CoroutineScope(Dispatchers.Default)
        when (sortType) {
            SortType.MODEL -> {
                coroutineScope.launch { _vehicles.value = db.getVehiclesOrderedByModel() }
            }

            SortType.REGISTRATION -> {
                coroutineScope.launch { _vehicles.value = db.getVehiclesOrderedByRegistration() }
            }

            SortType.TPL_INSURANCE -> {
                coroutineScope.launch { _vehicles.value = db.getVehiclesOrderedByTPLInsurance() }
            }

            SortType.COLLISION_INSURANCE -> {
                coroutineScope.launch { _vehicles.value = db.getVehiclesOrderedByCIInsurance() }
            }

            SortType.NEXT_INSPECTION_DATE -> {
                coroutineScope.launch {
                    _vehicles.value = db.getVehiclesOrderedByNextInspectionDate()
                }
            }
        }
    }
}