package org.prauga.pvotcalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.prauga.pvotcalculator.ui.theme.CalcDarkGray
import org.prauga.pvotcalculator.ui.theme.CalcLightGray
import org.prauga.pvotcalculator.ui.theme.CalcOrange
import org.prauga.pvotcalculator.ui.theme.CalcWhite
import org.prauga.pvotcalculator.ui.theme.SoraFamily

private val ButtonSpacing = 12.dp
private val KeypadPadding = 12.dp

@Composable
fun CalculatorKeypad(
    onDigit: (String) -> Unit,
    onOperator: (String) -> Unit,
    onEquals: () -> Unit,
    onClear: () -> Unit,
    onBackspace: () -> Unit,
    onDecimal: () -> Unit,
    onToggleSign: () -> Unit,
    onPercent: () -> Unit,
    modifier: Modifier = Modifier
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val buttonSize = (screenWidth - KeypadPadding * 2 - ButtonSpacing * 3) / 4

    Column(
        modifier = modifier.padding(horizontal = KeypadPadding, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(ButtonSpacing)
    ) {
        // Row 1: AC, +/-, %, ÷
        KeypadRow {
            CalcButton("AC", CalcLightGray, Color.Black, buttonSize, fontSize = 20, onClick = { onClear() })
            CalcButton("+/−", CalcLightGray, Color.Black, buttonSize, fontSize = 18, onClick = { onToggleSign() })
            CalcButton("%", CalcLightGray, Color.Black, buttonSize, onClick = { onPercent() })
            CalcButton("÷", CalcOrange, CalcWhite, buttonSize, fontSize = 30, onClick = { onOperator("÷") })
        }
        // Row 2: 7, 8, 9, ×
        KeypadRow {
            CalcButton("7", CalcDarkGray, CalcWhite, buttonSize, onClick = { onDigit("7") })
            CalcButton("8", CalcDarkGray, CalcWhite, buttonSize, onClick = { onDigit("8") })
            CalcButton("9", CalcDarkGray, CalcWhite, buttonSize, onClick = { onDigit("9") })
            CalcButton("×", CalcOrange, CalcWhite, buttonSize, fontSize = 30, onClick = { onOperator("×") })
        }
        // Row 3: 4, 5, 6, −
        KeypadRow {
            CalcButton("4", CalcDarkGray, CalcWhite, buttonSize, onClick = { onDigit("4") })
            CalcButton("5", CalcDarkGray, CalcWhite, buttonSize, onClick = { onDigit("5") })
            CalcButton("6", CalcDarkGray, CalcWhite, buttonSize, onClick = { onDigit("6") })
            CalcButton("−", CalcOrange, CalcWhite, buttonSize, fontSize = 30, onClick = { onOperator("−") })
        }
        // Row 4: 1, 2, 3, +
        KeypadRow {
            CalcButton("1", CalcDarkGray, CalcWhite, buttonSize, onClick = { onDigit("1") })
            CalcButton("2", CalcDarkGray, CalcWhite, buttonSize, onClick = { onDigit("2") })
            CalcButton("3", CalcDarkGray, CalcWhite, buttonSize, onClick = { onDigit("3") })
            CalcButton("+", CalcOrange, CalcWhite, buttonSize, fontSize = 30, onClick = { onOperator("+") })
        }
        // Row 5: 0 (wide), ., =
        KeypadRow {
            WideCalcButton("0", CalcDarkGray, CalcWhite, buttonSize, onClick = { onDigit("0") })
            CalcButton(".", CalcDarkGray, CalcWhite, buttonSize, onClick = { onDecimal() })
            CalcButton("=", CalcOrange, CalcWhite, buttonSize, fontSize = 30, onClick = { onEquals() })
        }
    }
}

@Composable
private fun KeypadRow(content: @Composable RowScope.() -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(ButtonSpacing),
        content = content
    )
}

@Composable
private fun CalcButton(
    text: String,
    containerColor: Color,
    contentColor: Color,
    buttonSize: Dp,
    fontSize: Int = 26,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(buttonSize),
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Text(
            text = text,
            fontSize = fontSize.sp,
            fontFamily = SoraFamily,
            fontWeight = FontWeight.Normal,
            maxLines = 1,
        )
    }
}

@Composable
private fun WideCalcButton(
    text: String,
    containerColor: Color,
    contentColor: Color,
    buttonSize: Dp,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(buttonSize)
            .fillMaxWidth(0.5f),
        shape = RoundedCornerShape(buttonSize / 2),
        contentPadding = PaddingValues(horizontal = buttonSize * 0.3f, vertical = 0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = text,
                fontSize = 26.sp,
                fontFamily = SoraFamily,
                fontWeight = FontWeight.Normal,
                maxLines = 1,
            )
        }
    }
}
