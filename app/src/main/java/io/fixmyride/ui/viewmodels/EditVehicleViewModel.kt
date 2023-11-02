package io.fixmyride.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class EditVehicleViewModel(
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
}