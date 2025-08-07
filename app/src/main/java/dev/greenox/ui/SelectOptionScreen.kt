package dev.greenox.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.greenox.R
import dev.greenox.ui.components.FormattedPriceLabel
import dev.greenox.ui.theme.CupcakeAppTheme

/**
 * Composable that displays the list of items as [RadioButton] options,
 * [onSelectionChanged] lambda that notifies the parent composable when a new value is selected,
 * onCancelButtonClicked lambda that cancels the order when user clicks cancel and
 * onNextButtonClicked lambda that triggers the navigation to next screen
 */
@Composable
fun SelectOptionScreen(
    modifier: Modifier = Modifier,
    subtotal: String,
    options: List<String>,
    onCancelButtonClicked: () -> Unit = {},
    onSelectionChanged: (String) -> Unit = {},
    onNextButtonClicked: () -> Unit = {},
) {
    var selectedValue by rememberSaveable { mutableStateOf(value = "") }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.padding(all = dimensionResource(id = R.dimen.padding_16))) {
            options.forEach { item ->
                Row(
                    modifier = Modifier.selectable(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    )
                    Text(text = item)
                }
            }
            HorizontalDivider(
                thickness = dimensionResource(id = R.dimen.thickness_divider),
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_16))
            )
            FormattedPriceLabel(
                subtotal = subtotal,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(
                        top = dimensionResource(id = R.dimen.padding_16),
                        bottom = dimensionResource(id = R.dimen.padding_16)
                    )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = dimensionResource(id = R.dimen.padding_16)),
            horizontalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.padding_16)),
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedButton(
                modifier = Modifier.weight(weight = 1f),
                onClick = onCancelButtonClicked
            ) {
                Text(text = stringResource(id = R.string.cancel))
            }
            Button(
                modifier = Modifier.weight(weight = 1f),
                enabled = selectedValue.isNotEmpty(),
                onClick = onNextButtonClicked
            ) {
                Text(text = stringResource(id = R.string.next))
            }
        }
    }
}

@Preview
@Composable
fun SelectOptionPreview() {
    CupcakeAppTheme {
        SelectOptionScreen(
            subtotal = "299.99",
            options = listOf("Option 1", "Option 2", "Option 3", "Option 4"),
            modifier = Modifier.fillMaxHeight()
        )
    }
}