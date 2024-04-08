package com.roqay.mostafa.presentation.compoents.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun CustomAppBar(
    isHome: Boolean = false,
    title: String? = null,
    haveAction: Boolean = false,
    action: (() -> Unit)? = null,
    actionIcon: ImageVector? = null
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().background(Color.Gray)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(if (haveAction) 0.9f else 1.0f),
            horizontalArrangement = Arrangement.Start
        ) {
            if (isHome) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
            title?.let {
                Text(
                    it,
                    modifier = Modifier,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }
        if (haveAction) {
            action?.let {
                IconButton(onClick = it) {
                    actionIcon?.let { icon ->
                        Icon(icon, contentDescription = "Action")
                    }
                }
            }
        }
    }
}
