package io.fixmyride.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.fixmyride.R
import io.fixmyride.enums.ManageVehicleType
import io.fixmyride.models.Vehicle
import io.fixmyride.ui.components.FloatingButton
import io.fixmyride.ui.components.UniversalHeader
import io.fixmyride.ui.components.VehicleThumbnail
import io.fixmyride.ui.components.viewvehicle.DataDisplayField
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements

@Composable
fun ViewVehicleScreen(
    navCtrl: NavController,
    vehicle: Vehicle,
) {

    Surface(
        color = ColorPalette.background,
        modifier = Modifier.padding(horizontal = Measurements.screenPadding),
    ) {
        Column {
            Spacer(Modifier.height(Measurements.screenPadding))
            UniversalHeader(
                caption = stringResource(R.string.vehicle_preview),
                navCtrl = navCtrl,
            )

            Spacer(Modifier.height(10.dp))
            VehicleThumbnail(ManageVehicleType.PREVIEW, vehicle.imagePath)

            Spacer(Modifier.height(20.dp))
            DataDisplayField(
                caption = stringResource(R.string.addvehicle_model_headline),
                value = vehicle.model,
            )
            DataDisplayField(
                caption = stringResource(R.string.addvehicle_registration_number_headline),
                value = vehicle.registration,
            )
            DataDisplayField(
                caption = stringResource(R.string.tpl_insurance_expiry_date),
                value = vehicle.tplInsuranceExpiry,
                infoHeadline = stringResource(R.string.tpl_insurance),
                infoDescription = stringResource(R.string.tpl_insurance_desc),
                isDate = true,
            )
            DataDisplayField(
                caption = stringResource(R.string.ci_expiry_date),
                value = when (vehicle.collisionInsuranceExpiry) {
                    "null" -> stringResource(R.string.empty)
                    else -> vehicle.collisionInsuranceExpiry!!
                },
                infoHeadline = stringResource(R.string.ci),
                infoDescription = stringResource(R.string.ci_insurance_desc),
                isDate = true,
            )
        }

        FloatingButton(
            color = ColorPalette.primary,
            icon = Icons.Rounded.Edit,
            alignment = Alignment.BottomEnd,
        ) { navCtrl.navigate("/edit-vehicle/$vehicle") }
    }
}