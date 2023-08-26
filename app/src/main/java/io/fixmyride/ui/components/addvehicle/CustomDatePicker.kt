package io.fixmyride.ui.components.addvehicle

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import io.fixmyride.R
import io.fixmyride.models.DateType
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Typing
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomDatePicker(onDismiss: (LocalDate?) -> Unit) {
    Dialog(
        onDismissRequest = { onDismiss(null) },
    ) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = ColorPalette.background,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.padding(10.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        stringResource(R.string.pick_a_date),
                        style = Typing.subheading,
                    )
                    Icon(
                        Icons.Rounded.Close,
                        contentDescription = "Close dialog",
                        tint = ColorPalette.lightRed,
                        modifier = Modifier.clickable { onDismiss(null) }
                    )
                }

                Spacer(Modifier.height(10.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Row {
                        DateRoll(DateType.DAY)
                        Spacer(Modifier.width(20.dp))
                    }

                    Row {
                        DateRoll(DateType.MONTH)
                        Spacer(Modifier.width(20.dp))
                    }

                    DateRoll(DateType.YEAR)
                }

                Spacer(Modifier.height(10.dp))

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = ColorPalette.primary,
                            shape = RoundedCornerShape(10.dp),
                        )
                        .clickable { /* TODO */ }
                ) {
                    Text(
                        stringResource(R.string.save),
                        style = Typing.buttonText,
                        modifier = Modifier.padding(vertical = 5.dp),
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateRoll(type: DateType) {
    val dateValues: List<Any> = when (type) {
        DateType.DAY -> (1..31).toList()
        DateType.MONTH -> stringResource(R.string.month_list).split(" ")
        DateType.YEAR -> (LocalDate.now().year..LocalDate.now().year + 30).toList()
    }

    Column(
        modifier = Modifier.fillMaxWidth(0.33f)
    ) {
        Divider(
            color = ColorPalette.primary,
            thickness = 2.dp,
        )

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
        ) {
            items(dateValues.size) {
                Text(
                    "${dateValues[it]}",
                    style = Typing.defaultBody,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            }
        }

        Divider(
            color = ColorPalette.primary,
            thickness = 2.dp,
        )
    }
}