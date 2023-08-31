package io.fixmyride.ui.screens

import android.graphics.Bitmap
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
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.fixmyride.R
import io.fixmyride.ui.components.FloatingButton
import io.fixmyride.ui.components.UniversalHeader
import io.fixmyride.ui.components.addvehicle.DateField
import io.fixmyride.ui.components.addvehicle.FormField
import io.fixmyride.ui.components.addvehicle.Thumbnail
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddVehicleScreen(navCtrl: NavController) {
    var thumbnail: Bitmap? = null
    var model = ""
    var registration = ""
    var tplInsurance: LocalDate? = null
    var collisionInsurance: LocalDate? = null
    var lastInspectionDate: LocalDate? = null

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

            Thumbnail()

            FormField(
                caption = stringResource(R.string.addvehicle_model_headline),
                placeholder = stringResource(R.string.addvehicle_model_placeholder),
            ) { model = it }

            FormField(
                caption = stringResource(R.string.addvehicle_registration_number_headline),
                placeholder = stringResource(R.string.addvehicle_registration_number_placeholder),
            ) { registration = it }

            DateField(
                caption = stringResource(R.string.tpl_insurance_expiry_date),
                hintHeadline = stringResource(R.string.tpl_insurance),
                hintDescription = stringResource(R.string.tpl_insurance_desc),
            ) { tplInsurance = it }

            DateField(
                caption = stringResource(R.string.ci_expiry_date),
                hintHeadline = stringResource(R.string.ci),
                hintDescription = stringResource(R.string.ci_insurance_desc),
            ) { collisionInsurance = it }

            DateField(
                caption = "Last inspection date",
                hintHeadline = "Inspection",
                hintDescription = "This is a hint description for custom-made dialog",
            ) { lastInspectionDate = it }

            Spacer(Modifier.height(80.dp))
        }

        // FIXME changing color based on the provided data
        val requiredData = arrayOf<Any?>(model, registration, tplInsurance)
        FloatingButton(
            color = when {
                emptyIndexes(*requiredData).isNotEmpty() -> ColorPalette.primary
                else -> ColorPalette.tertiary
            },
            icon = Icons.Rounded.Done,
            alignment = Alignment.BottomEnd,
            scrollState = null,
        ) {
            // TODO
        }
    }
}

/** Returns a list of indexes of fields whose values are empty or null. */
private fun emptyIndexes(vararg requiredData: Any?): List<Int> {
    val emptyIndexes = ArrayList<Int>()
    for (i in requiredData.indices) {
        if ((requiredData[i] as? String).isNullOrEmpty()) {
            emptyIndexes.add(i)
        }
    }

    return emptyIndexes
}