package dev.greenox.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import dev.greenox.R
import dev.greenox.model.MenuItem

@Composable
fun <T : MenuItem> BaseMenuScreen(
    options: List<T>,
    modifier: Modifier = Modifier,
    onCancelButtonClicked: () -> Unit = {},
    onNextButtonClicked: () -> Unit = {},
    onSelectionChanged: (T) -> Unit,
) {

    var selectedItemName by rememberSaveable { mutableStateOf(value = "") }

    Column {
        LazyColumn(modifier = modifier.weight(weight = 1F)) {
            items(items = options) { item ->
                val onClick = {
                    selectedItemName = item.name
                    onSelectionChanged(item)
                }
                MenuItemRow(
                    item = item,
                    selectedItemName = selectedItemName,
                    onClick = onClick,
                    modifier = Modifier.selectable(
                        selected = selectedItemName == item.name,
                        onClick = onClick
                    )
                )
            }
        }
        MenuScreenButtonGroup(
            selectedItemName = selectedItemName,
            onCancelButtonClicked = onCancelButtonClicked,
            onNextButtonClicked = {
                onNextButtonClicked()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = dimensionResource(id = R.dimen.padding_16))
        )
    }
}

@Composable
fun MenuItemRow(
    item: MenuItem,
    selectedItemName: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selectedItemName == item.name,
            onClick = onClick,
            modifier = Modifier.semantics{
                contentDescription = item.name
            }
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.padding_8))
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = item.getFormattedPrice(),
                style = MaterialTheme.typography.bodyMedium
            )
            HorizontalDivider(
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_16)),
                thickness = dimensionResource(id = R.dimen.thickness_divider),
                color = DividerDefaults.color
            )
        }
    }
}

@Composable
fun MenuScreenButtonGroup(
    selectedItemName: String,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.padding_16))
    ) {
        OutlinedButton(modifier = Modifier.weight(weight = 1f), onClick = onCancelButtonClicked) {
            Text(text = stringResource(id = R.string.cancel))
        }
        Button(
            modifier = Modifier.weight(weight = 1f),
            enabled = selectedItemName.isNotEmpty(),
            onClick = onNextButtonClicked
        ) {
            Text(text = stringResource(id = R.string.next))
        }
    }
}