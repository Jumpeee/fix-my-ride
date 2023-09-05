package io.fixmyride.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.fixmyride.R
import io.fixmyride.database.DatabaseManager
import io.fixmyride.enums.Decision
import io.fixmyride.enums.ManageVehicleType
import io.fixmyride.models.Vehicle
import io.fixmyride.ui.components.FloatingButton
import io.fixmyride.ui.components.UniversalHeader
import io.fixmyride.ui.components.VehicleThumbnail
import io.fixmyride.ui.components.dialogs.DecisionDialog
import io.fixmyride.ui.components.managevehicle.DateField
import io.fixmyride.ui.components.managevehicle.FormField
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ManageVehicleScreen(
    navCtrl: NavController,
    viewType: ManageVehicleType,
    vehicle: Vehicle?,
) {
    val imagePath = remember { mutableStateOf<String?>(null) }
    val model = remember { mutableStateOf("") }
    val registration = remember { mutableStateOf("") }
    val tplInsurance = remember { mutableStateOf("") }
    val collisionInsurance = remember { mutableStateOf<String?>(null) }

    if (viewType == ManageVehicleType.EDIT) {
        imagePath.value = vehicle!!.imagePath
        model.value = vehicle.model
        registration.value = vehicle.registration
        tplInsurance.value = vehicle.tplInsuranceExpiry
        collisionInsurance.value = vehicle.collisionInsuranceExpiry
    }

    val showDeleteConfirmationDialog = remember { mutableStateOf(false) }

    Surface(
        color = ColorPalette.background,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Measurements.screenPadding),
    ) {
        val scrollState = rememberScrollState()
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            Spacer(Modifier.height(Measurements.screenPadding))

            UniversalHeader(
                caption = when (viewType) {
                    ManageVehicleType.ADD -> stringResource(R.string.add_vehicle)
                    ManageVehicleType.EDIT -> stringResource(R.string.edit_vehicle)
                },
                navCtrl = navCtrl,
            )

            Spacer(Modifier.height(10.dp))

            VehicleThumbnail(
                imagePath = vehicle?.imagePath,
                allowEditing = true,
            )

            FormField(
                initialValue = model.value,
                caption = stringResource(R.string.addvehicle_model_headline),
                placeholder = stringResource(R.string.addvehicle_model_placeholder),
            ) { model.value = it }

            FormField(
                initialValue = registration.value,
                caption = stringResource(R.string.addvehicle_registration_number_headline),
                placeholder = stringResource(R.string.addvehicle_registration_number_placeholder),
            ) { registration.value = it }

            DateField(
                initialValue = tplInsurance.value,
                caption = stringResource(R.string.tpl_insurance_expiry_date),
                hintHeadline = stringResource(R.string.tpl_insurance),
                hintDescription = stringResource(R.string.tpl_insurance_desc),
            ) { tplInsurance.value = it.toString().replace("-", ".") }

            DateField(
                initialValue = collisionInsurance.value,
                caption = stringResource(R.string.ci_expiry_date),
                hintHeadline = stringResource(R.string.ci),
                hintDescription = stringResource(R.string.ci_insurance_desc),
            ) { collisionInsurance.value = it.toString().replace("-", ".") }

            Spacer(Modifier.height(80.dp))
        }

        if (viewType == ManageVehicleType.ADD) {
            val requiredData = arrayOf(model.value, registration.value, tplInsurance.value)
            FloatingButton(
                color = when {
                    emptyIndexes(*requiredData).isNotEmpty() -> ColorPalette.primary
                    else -> ColorPalette.tertiary
                },
                icon = Icons.Rounded.Done,
                alignment = Alignment.BottomEnd,
                scrollState = null,
            ) {
                val db = DatabaseManager.getInstance().dao
                val coroutine = CoroutineScope(Dispatchers.Default)
                coroutine.launch {
                    db.addVehicle(
                        Vehicle(
                            model = model.value,
                            registration = registration.value,
                            tplInsuranceExpiry = tplInsurance.value,
                            collisionInsuranceExpiry = collisionInsurance.value,
                        )
                    )
                }
                navCtrl.popBackStack()
            }
        }

        if (viewType == ManageVehicleType.EDIT) {
            FloatingButton(
                color = ColorPalette.primary,
                icon = Icons.Rounded.Done,
                alignment = Alignment.BottomEnd,
            ) {
                val db = DatabaseManager.getInstance().dao
                CoroutineScope(Dispatchers.Default).launch {
                    db.updateVehicle(
                        Vehicle(
                            id = vehicle!!.id,
                            imagePath = imagePath.value,
                            model = model.value,
                            registration = registration.value,
                            tplInsuranceExpiry = tplInsurance.value,
                            collisionInsuranceExpiry = collisionInsurance.value,
                        )
                    )
                }
                for (i in 0..1) navCtrl.popBackStack()
            }

            FloatingButton(
                color = ColorPalette.lightRed,
                icon = Icons.Rounded.Delete,
                alignment = Alignment.BottomStart,
            ) { showDeleteConfirmationDialog.value = true }
        }

        if (viewType == ManageVehicleType.EDIT && showDeleteConfirmationDialog.value) {
            DecisionDialog(
                headline = stringResource(R.string.delete_vehicle),
                description = stringResource(R.string.are_you_sure_you_want_to_delete_the_vehicle),
            ) {
                if (it == Decision.YES) {
                    val db = DatabaseManager.getInstance().dao
                    CoroutineScope(Dispatchers.Default).launch { db.deleteVehicle(vehicle!!) }
                    for (i in 0..1) navCtrl.popBackStack()
                }
                showDeleteConfirmationDialog.value = false
            }
        }

    }
}

/** Returns a list of indexes of fields whose values are empty or null. */
private fun emptyIndexes(vararg requiredData: String): List<Int> {
    val emptyIndexes = ArrayList<Int>()
    for (i in requiredData.indices) {
        if (requiredData[i].isEmpty()) {
            emptyIndexes.add(i)
        }
    }

    return emptyIndexes
}