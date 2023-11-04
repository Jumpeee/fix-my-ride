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
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.fixmyride.R
import io.fixmyride.ui.components.FloatingButton
import io.fixmyride.ui.components.UniversalHeader
import io.fixmyride.ui.components.dialogs.DecisionDialog
import io.fixmyride.ui.components.managevehicle.DateField
import io.fixmyride.ui.components.managevehicle.FormField
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements
import io.fixmyride.ui.viewmodels.EditVehicleViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditVehicleScreen(viewModel: EditVehicleViewModel) {
    LaunchedEffect(Unit) { viewModel.loadVehicle() }

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
                caption = stringResource(R.string.edit_vehicle),
                navCtrl = viewModel.navController,
            )

            Spacer(Modifier.height(10.dp))

            FormField(
                isError = 0 in viewModel.emptyFields.value,
                initialValue = viewModel.model.value,
                caption = stringResource(R.string.addvehicle_model_headline),
                placeholder = stringResource(R.string.addvehicle_model_placeholder),
            ) { viewModel.updateModel(it) }

            FormField(
                isError = 1 in viewModel.emptyFields.value,
                initialValue = viewModel.registration.value,
                upperCase = true,
                caption = stringResource(R.string.addvehicle_registration_number_headline),
                placeholder = stringResource(R.string.addvehicle_registration_number_placeholder),
            ) { viewModel.updateRegistration(it) }

            DateField(
                isError = 2 in viewModel.emptyFields.value,
                initialValue = when (viewModel.tplInsurance.value) {
                    "" -> null
                    else -> viewModel.tplInsurance.value
                },
                caption = stringResource(R.string.tpl_insurance_expiry_date),
                hintHeadline = stringResource(R.string.tpl_insurance),
                hintDescription = stringResource(R.string.tpl_insurance_desc),
            ) { viewModel.updateTplInsurance(it.toString().replace("-", ".")) }

            DateField(
                initialValue = when (viewModel.collisionInsurance.value) {
                    "null" -> null
                    else -> viewModel.collisionInsurance.value
                },
                caption = stringResource(R.string.ci_expiry_date),
                hintHeadline = stringResource(R.string.ci),
                hintDescription = stringResource(R.string.ci_insurance_desc),
            ) { viewModel.updateCollisionInsurance(it.toString().replace("-", ".")) }

            DateField(
                isError = 3 in viewModel.emptyFields.value,
                initialValue = when (viewModel.nextInspectionDate.value) {
                    "" -> null
                    else -> viewModel.nextInspectionDate.value
                },
                caption = stringResource(R.string.next_inspection_date),
                showHint = false,
            ) { viewModel.updateNextInspectionDate(it.toString().replace("-", ".")) }

            Spacer(Modifier.height(100.dp))
        }

        val buttonColor = animateColorAsState(
            targetValue = when {
                viewModel.isDataValid() -> ColorPalette.primary
                else -> ColorPalette.tertiary
            },
            animationSpec = Measurements.colorChangeAnimation(),
            label = "",
        )

        FloatingButton(
            color = buttonColor.value,
            icon = Icons.Rounded.Done,
            alignment = Alignment.BottomEnd,
        ) { viewModel.saveChanges() }

        FloatingButton(
            color = ColorPalette.lightRed,
            icon = Icons.Rounded.Delete,
            alignment = Alignment.BottomStart,
        ) { viewModel.toggleConfirmationDialog(true) }


        if (viewModel.showDeleteConfirmationDialog.value) {
            DecisionDialog(
                headline = stringResource(R.string.delete_vehicle),
                description = stringResource(R.string.are_you_sure_you_want_to_delete_the_vehicle),
            ) { viewModel.deleteVehicle(it) }
        }

    }
}