package io.github.aptemkov.tasksapp.presentation.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import io.github.aptemkov.tasksapp.presentation.details.divkit.DetailsView

@Composable
fun DetailScreen(
    onClose: () -> Unit,
) {

    Scaffold { innerPadding ->
        AndroidView(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            factory = { context ->
                val detailsDiv = DetailsView(context, onClose)
                detailsDiv.getView()
            }
        )
    }
}