package io.fixmyride.ui.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import io.fixmyride.database.DatabaseManager
import io.fixmyride.database.DateConverter
import io.fixmyride.enums.Decision
import io.fixmyride.models.Vehicle
import io.fixmyride.utils.ValidationUtils
import kotlinx.coroutines.launch

class EditVehicleViewModel(
    val navController: NavController,
    private val vehicleId: Int,
) : ViewModel() {
    private val _model = mutableStateOf("")
    private val _registration = mutableStateOf("")
    private val _tplInsurance = mutableStateOf("")
    private val _collisionInsurance = mutableStateOf<String?>(null)
    private val _nextInspectionDate = mutableStateOf("")

    val model: State<String> = _model
    val registration: State<String> = _registration
    val tplInsurance: State<String> = _tplInsurance
    val collisionInsurance: State<String?> = _collisionInsurance
    val nextInspectionDate: State<String> = _nextInspectionDate

    private val _emptyFields = mutableStateOf(emptyArray<Int>())
    private val _showDeleteConfirmationDialog = mutableStateOf(false)

    val emptyFields: State<Array<Int>> = _emptyFields
    val showDeleteConfirmationDialog: State<Boolean> = _showDeleteConfirmationDialog

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun loadVehicle() {
        val db = DatabaseManager.getInstance().dao
        val vehicle = db.getVehicleById(vehicleId)

        _model.value = vehicle.model
        _registration.value = vehicle.registration
        _tplInsurance.value = DateConverter.fromLocalDate(vehicle.tplInsuranceExpiry)
        _collisionInsurance.value = when (vehicle.collisionInsuranceExpiry) {
            null -> null
            else -> DateConverter.fromLocalDate(vehicle.collisionInsuranceExpiry)
        }
        _nextInspectionDate.value = DateConverter.fromLocalDate(vehicle.nextInspectionDate)
    }

    fun filterFieldValue(value: String?): String? {
        return when (value) {
            "" -> null
            "null" -> null
            else -> value
        }
    }

    fun updateModel(newValue: String) {
        _model.value = newValue
    }

    fun updateRegistration(newValue: String) {
        _registration.value = newValue
    }

    fun updateTplInsurance(newValue: String) {
        _tplInsurance.value = newValue
    }

    fun updateCollisionInsurance(newValue: String?) {
        _collisionInsurance.value = newValue
    }

    fun updateNextInspectionDate(newValue: String) {
        _nextInspectionDate.value = newValue
    }

    fun toggleConfirmationDialog(on: Boolean) {
        _showDeleteConfirmationDialog.value = on
    }

    fun isDataValid(): Boolean {
        val requiredData = arrayOf(
            _model.value,
            _registration.value,
            _tplInsurance.value,
            _nextInspectionDate.value,
        )

        val emptyIndexes = ValidationUtils.stringEmptyIndexes(*requiredData)
        return emptyIndexes.isEmpty()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveChanges() {
        val requiredData = arrayOf(
            _model.value,
            _registration.value,
            _tplInsurance.value,
            _nextInspectionDate.value,
        )

        val emptyRequiredFields = ValidationUtils.stringEmptyIndexes(*requiredData)
        if (emptyRequiredFields.isNotEmpty()) {
            _emptyFields.value = emptyRequiredFields.toTypedArray()
            return
        }
        val db = DatabaseManager.getInstance().dao
        viewModelScope.launch {
            db.updateVehicle(
                Vehicle(
                    vehicleId,
                    model.value,
                    registration.value,
                    DateConverter.toLocalDate(tplInsurance.value),
                    when (collisionInsurance.value) {
                        null -> null
                        else -> DateConverter.toLocalDate(collisionInsurance.value!!)
                    },
                    DateConverter.toLocalDate(nextInspectionDate.value),
                )
            )
        }
        for (i in 0..1) navController.popBackStack()
    }

    fun deleteVehicle(decision: Decision?) {
        if (decision == Decision.YES) {
            val db = DatabaseManager.getInstance().dao
            viewModelScope.launch { db.deleteVehicleById(vehicleId) }
            for (i in 0..1) navController.popBackStack()
        }
        _showDeleteConfirmationDialog.value = false
    }
}