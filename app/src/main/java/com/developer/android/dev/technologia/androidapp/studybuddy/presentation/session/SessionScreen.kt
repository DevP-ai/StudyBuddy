package com.developer.android.dev.technologia.androidapp.studybuddy.presentation.session

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SessionScreen() {
    Scaffold(
        topBar = { SessionScreenTopBar(
            onBackButtonClick = {}
        )}
    ) { paddingValues ->
        LazyColumn (
            modifier= Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionScreenTopBar(
    onBackButtonClick: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackButtonClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Navigation"
                )
            }
        },
        title = {
            Text(
                text = "Study Session",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    )
}
