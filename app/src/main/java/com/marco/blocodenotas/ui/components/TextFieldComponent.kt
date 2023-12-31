package com.marco.blocodenotas.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marco.blocodenotas.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldComponent(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    singleLine: Boolean
) {
    val isDark = isSystemInDarkTheme()

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth().padding(20.dp),
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = if (isDark) White else White,
            focusedLabelColor = if (isDark) White else White,
            textColor = if (isDark) White else White,
            unfocusedLabelColor = if (isDark) White else White,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        label = { Text(text = label)},
        singleLine = singleLine,
        shape = RoundedCornerShape(16.dp)
    )
}