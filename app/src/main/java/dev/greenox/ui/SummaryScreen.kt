package dev.greenox.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import dev.greenox.R
import dev.greenox.data.OrderUiState
import dev.greenox.ui.components.FormattedPriceLabel
import dev.greenox.ui.theme.CupcakeAppTheme

/**
 * This composable expects [orderUiState] that represents the order state, onCancelButtonClicked
 * lambda that triggers canceling the order and passes the final order to onSendButtonClicked
 * lambda
 */
@Composable
fun OrderSummaryScreen(
    orderUiState: OrderUiState,
    onCancelButtonClicked: () -> Unit,
    onSendButtonClicked: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val resources = LocalContext.current.resources

    val numberOfCupcakes = resources.getQuantityString(
        R.plurals.cupcakes,
        orderUiState.quantity,
        orderUiState.quantity
    )
    //Load and format a string resource with the parameters.
    val orderSummary = stringResource(
        R.string.order_details,
        numberOfCupcakes,
        orderUiState.flavor,
        orderUiState.date,
        orderUiState.quantity
    )
    val newOrder = stringResource(id = R.string.new_cupcake_order)
    //Create a list of order summary to display
    val items = listOf(
        // Summary line 1: display selected quantity
        Pair(first = stringResource(id = R.string.quantity), second = numberOfCupcakes),
        // Summary line 2: display selected flavor
        Pair(first = stringResource(id = R.string.flavor), second = orderUiState.flavor),
        // Summary line 3: display selected pickup date
        Pair(first = stringResource(id = R.string.pickup_date), second = orderUiState.date)
    )

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.padding(all = dimensionResource(id = R.dimen.padding_16)),
            verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.padding_8))
        ) {
            items.forEach { item ->
                Text(text = item.first.uppercase())
                Text(text = item.second, fontWeight = FontWeight.Bold)
                HorizontalDivider(
                    thickness = dimensionResource(id = R.dimen.thickness_divider),
                    color = DividerDefaults.color
                )
            }
            Spacer(modifier = Modifier.height(height = dimensionResource(id = R.dimen.padding_8)))
            FormattedPriceLabel(
                subtotal = orderUiState.price,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Row(
            modifier = Modifier.padding(all = dimensionResource(id = R.dimen.padding_16))
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.padding_8))
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onSendButtonClicked(newOrder, orderSummary)
                    }
                ) {
                    Text(text = stringResource(id = R.string.send))
                }
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onCancelButtonClicked
                ) {
                    Text(text = stringResource(id = R.string.cancel))
                }
            }
        }
    }
}

@Preview()
@Composable
fun OrderSummaryPreview() {
    CupcakeAppTheme {
        OrderSummaryScreen(
            orderUiState = OrderUiState(quantity = 0, flavor = "Test", date =  "Test", price = "$300.00"),
            onCancelButtonClicked = {},
            onSendButtonClicked = {subject: String, summary: String ->},
            modifier = Modifier.fillMaxHeight()
        )
    }
}