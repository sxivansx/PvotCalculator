package org.prauga.pvotcalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.prauga.pvotcalculator.ui.theme.CalcBlack
import org.prauga.pvotcalculator.ui.theme.CalcLightGray
import org.prauga.pvotcalculator.ui.theme.CalcWhite
import org.prauga.pvotcalculator.ui.theme.SoraFamily

@Composable
fun CalculatorScreen(
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel = viewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CalcBlack)
    ) {
        // Display area — takes remaining space above keypad
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            // Expression (secondary display)
            if (viewModel.expression.isNotEmpty()) {
                Text(
                    text = viewModel.expression,
                    fontSize = 24.sp,
                    fontFamily = SoraFamily,
                    fontWeight = FontWeight.Light,
                    color = CalcLightGray,
                    textAlign = TextAlign.End,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
            }

            // Main display
            Text(
                text = viewModel.display,
                fontSize = 80.sp,
                fontFamily = SoraFamily,
                fontWeight = FontWeight.Thin,
                color = CalcWhite,
                textAlign = TextAlign.End,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Keypad — separate view
        CalculatorKeypad(
            onDigit = viewModel::onDigit,
            onOperator = viewModel::onOperator,
            onEquals = viewModel::onEquals,
            onClear = viewModel::onClear,
            onBackspace = viewModel::onBackspace,
            onDecimal = viewModel::onDecimal,
            onToggleSign = viewModel::onToggleSign,
            onPercent = viewModel::onPercent,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
