package io.pdaa.chilenastats.ui.screens.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.pdaa.chilenastats.ui.screens.Screen

@Composable
fun DashboardScreen() {

    Screen {
        Scaffold {contentPadding ->
            Box(modifier = Modifier.fillMaxSize().padding(contentPadding)){
                Text(modifier = Modifier.align(Alignment.Center), text = "Dashboard")
            }
        }
    }

}