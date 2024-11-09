package io.pdaa.chilenastats.ui.screens.onboarding.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import io.pdaa.chilenastats.R
import io.pdaa.chilenastats.ui.screens.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    continueToOnBoarding: () -> Unit,
    continueToDashboard: () -> Unit,
) {

    Screen {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold { contentPadding ->
            Box(modifier = Modifier.padding(contentPadding)){
                TextButton(
                    onClick = {
                        continueToOnBoarding()
                    }
                ) {
                    Text(text = stringResource(R.string.continue_to_onboarding))
                }
            }

        }
    }

}