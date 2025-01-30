package io.github.aptemkov.tasksapp.presentation.utils

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.text.TextLayoutResult


fun SemanticsNode.getElementColor(): Color {

    val textLayoutResults = mutableListOf<TextLayoutResult>()
    this.config.getOrNull(SemanticsActions.GetTextLayoutResult)
        ?.action
        ?.invoke(textLayoutResults)

    val color = textLayoutResults.first().layoutInput.style.color
    Log.i("SemanticsNode", "color is: $color")

    return color
}
