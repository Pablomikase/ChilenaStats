package io.pdaa.chilenastats.ui.screens.onboarding.login

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import io.pdaa.chilenastats.ui.screens.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    continueToOnBoarding: () -> Unit,
    viewModel: LoginViewModel
) {

    Screen {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold { contentPadding ->
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var isLoading by remember { mutableStateOf(false) }

            Column(
                modifier = Modifier.fillMaxSize().padding(contentPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Correo electrónico") }
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                    visualTransformation = PasswordVisualTransformation()
                )
                Button(
                    onClick = {
                        isLoading = true
                        viewModel.login(email, password) { success ->
                            isLoading = false
                            if (success) {
                                Log.d("LoginScreen", "Inicio de sesión exitoso")
                            } else {
                                Log.e("LoginScreen", "Error al iniciar sesión")
                            }
                        }
                    },
                    enabled = !isLoading
                ) {
                    Text("Iniciar sesión")
                }
            }

        }
    }

}