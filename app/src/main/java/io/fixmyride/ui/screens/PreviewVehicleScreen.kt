package io.fixmyride.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.fixmyride.R
import io.fixmyride.database.DateConverter
import io.fixmyride.ui.components.FloatingButton
import io.fixmyride.ui.components.UniversalHeader
import io.fixmyride.ui.components.previewvehicle.DataDisplayField
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements
import io.fixmyride.ui.viewmodels.PreviewVehicleViewModel
import io.fixmyride.utils.Routes

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PreviewVehicleScreen(viewModel: PreviewVehicleViewModel) {
    LaunchedEffect(Unit) { viewModel.loadVehicle() }

    Surface(
        color = ColorPalette.background,
        modifier = Modifier.padding(horizontal = Measurements.screenPadding),
    ) {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            Spacer(Modifier.height(Measurements.screenPadding))

            UniversalHeader(
                caption = stringResource(R.string.vehicle_preview),
                navCtrl = viewModel.navController,
            )
            Spacer(Modifier.height(30.dp))

            DataDisplayField(
                caption = stringResource(R.string.addvehicle_model_headline),
                value = viewModel.vehicle.value.model,
            )
            DataDisplayField(
                caption = stringResource(R.string.addvehicle_registration_number_headline),
                value = viewModel.vehicle.value.registration,
            )
            DataDisplayField(
                caption = stringResource(R.string.tpl_insurance_expiry_date),
                value = DateConverter.fromLocalDate(viewModel.vehicle.value.tplInsuranceExpiry),
                infoHeadline = stringResource(R.string.tpl_insurance),
                infoDescription = stringResource(R.string.tpl_insurance_desc),
                isDate = true,
            )
            DataDisplayField(
                caption = stringResource(R.string.ci_expiry_date),
                value = when (viewModel.vehicle.value.collisionInsuranceExpiry) {
                    null -> stringResource(R.string.empty)
                    else -> DateConverter.fromLocalDate(viewModel.vehicle.value.collisionInsuranceExpiry!!)
                },
                infoHeadline = stringResource(R.string.ci),
                infoDescription = stringResource(R.string.ci_insurance_desc),
                isDate = true,
            )

            DataDisplayField(
                caption = stringResource(R.string.next_inspection_date),
                value = DateConverter.fromLocalDate(viewModel.vehicle.value.nextInspectionDate),
                isDate = true,
                showHint = false,
            )
            Spacer(Modifier.height(100.dp))
        }

        FloatingButton(
            color = ColorPalette.primary,
            icon = Icons.Rounded.Edit,
            alignment = Alignment.BottomEnd,
        ) { viewModel.navController.navigate("${Routes.EDIT_VEHICLE}/${viewModel.vehicle.value.id}") }
    }
}