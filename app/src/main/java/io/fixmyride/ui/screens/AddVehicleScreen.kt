package io.fixmyride.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.fixmyride.R
import io.fixmyride.database.DatabaseManager
import io.fixmyride.enums.ManageVehicleType
import io.fixmyride.models.Vehicle
import io.fixmyride.ui.components.FloatingButton
import io.fixmyride.ui.components.UniversalHeader
import io.fixmyride.ui.components.VehicleThumbnail
import io.fixmyride.ui.components.managevehicle.DateField
import io.fixmyride.ui.components.managevehicle.FormField
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements
import io.fixmyride.utils.ValidationUtils
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddVehicleScreen(navCtrl: NavController) {
    val imagePath = remember { mutableStateOf<ByteArray?>(null) }
    val model = remember { mutableStateOf("") }
    val registration = remember { mutableStateOf("") }
    val tplInsurance = remember { mutableStateOf("") }
    val collisionInsurance = remember { mutableStateOf<String?>(null) }
    val nextInspectionDate = remember { mutableStateOf("") }

    val emptyFields = remember { mutableStateOf(emptyArray<Int>()) }

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
                caption = stringResource(R.string.add_vehicle),
                navCtrl = navCtrl,
            )

            Spacer(Modifier.height(10.dp))
            VehicleThumbnail(
                viewType = ManageVehicleType.ADD,
                imagePath = imagePath.value,
                allowEditing = true,
            ) { imagePath.value = it }

            FormField(
                isError = 0 in emptyFields.value,
                initialValue = model.value,
                caption = stringResource(R.string.addvehicle_model_headline),
                placeholder = stringResource(R.string.addvehicle_model_placeholder),
            ) { model.value = it }

            FormField(
                isError = 1 in emptyFields.value,
                initialValue = registration.value,
                caption = stringResource(R.string.addvehicle_registration_number_headline),
                placeholder = stringResource(R.string.addvehicle_registration_number_placeholder),
            ) { registration.value = it }

            DateField(
                isError = 2 in emptyFields.value,
                initialValue = when (tplInsurance.value) {
                    "" -> null
                    else -> tplInsurance.value
                },
                caption = stringResource(R.string.tpl_insurance_expiry_date),
                hintHeadline = stringResource(R.string.tpl_insurance),
                hintDescription = stringResource(R.string.tpl_insurance_desc),
            ) { tplInsurance.value = it.toString().replace("-", ".") }

            DateField(
                initialValue = when (collisionInsurance.value) {
                    "null" -> null
                    else -> collisionInsurance.value
                },
                caption = stringResource(R.string.ci_expiry_date),
                hintHeadline = stringResource(R.string.ci),
                hintDescription = stringResource(R.string.ci_insurance_desc),
            ) { collisionInsurance.value = it.toString().replace("-", ".") }

            DateField(
                isError = 3 in emptyFields.value,
                initialValue = when (nextInspectionDate.value) {
                    "" -> null
                    else -> nextInspectionDate.value
                },
                caption = stringResource(R.string.next_inspection_date),
                showHint = false,
            ) { nextInspectionDate.value = it.toString().replace("-", ".") }

            Spacer(Modifier.height(100.dp))
        }

        val requiredData =
            arrayOf(
                model.value,
                registration.value,
                tplInsurance.value,
                nextInspectionDate.value,
            )
        val buttonColor = animateColorAsState(
            targetValue = when {
                ValidationUtils.stringEmptyIndexes(*requiredData).isEmpty() -> ColorPalette.primary
                else -> ColorPalette.tertiary
            },
            animationSpec = Measurements.colorChangeAnimation(),
            label = "",
        )

        val coroutineScope = rememberCoroutineScope()
        FloatingButton(
            color = buttonColor.value,
            icon = Icons.Rounded.Done,
            alignment = Alignment.BottomEnd,
            scrollState = null,
        ) {
            val emptyRequiredFields = ValidationUtils.stringEmptyIndexes(*requiredData)
            if (emptyRequiredFields.isNotEmpty()) {
                emptyFields.value = emptyRequiredFields.toTypedArray()
                return@FloatingButton
            }

            val db = DatabaseManager.getInstance().dao
            coroutineScope.launch {
                db.addVehicle(
                    Vehicle(
                        imagePath = imagePath.value,
                        model = model.value,
                        registration = registration.value,
                        tplInsuranceExpiry = tplInsurance.value,
                        collisionInsuranceExpiry = collisionInsurance.value,
                        nextInspectionDate = nextInspectionDate.value,
                    )
                )
            }
            navCtrl.popBackStack()
        }
    }
}