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
import io.fixmyride.models.Vehicle
import io.fixmyride.utils.ValidationUtils
import kotlinx.coroutines.launch

class AddVehicleViewModel(
    val navController: NavController,
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
    val emptyFields: State<Array<Int>> = _emptyFields

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
    fun addVehicleToDatabase() {
        if (!isDataValid()) {
            val requiredData = arrayOf(
                _model.value,
                _registration.value,
                _tplInsurance.value,
                _nextInspectionDate.value,
            )
            _emptyFields.value = ValidationUtils.stringEmptyIndexes(*requiredData).toTypedArray()
            return
        }

        val db = DatabaseManager.getInstance().dao
        viewModelScope.launch {
            val vehicle = Vehicle(
                model = _model.value,
                registration = _registration.value,
                tplInsuranceExpiry = DateConverter.toLocalDate(_tplInsurance.value),
                collisionInsuranceExpiry = when (_collisionInsurance.value) {
                    null -> null
                    else -> DateConverter.toLocalDate(_collisionInsurance.value!!)
                },
                nextInspectionDate = DateConverter.toLocalDate(_nextInspectionDate.value),
            )

            db.addVehicle(vehicle)
        }
        navController.popBackStack()
    }

}
