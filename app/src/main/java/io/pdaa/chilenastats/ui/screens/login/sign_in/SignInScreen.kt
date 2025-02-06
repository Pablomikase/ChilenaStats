package io.pdaa.chilenastats.ui.screens.login.sign_in

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.pdaa.chilenastats.R
import io.pdaa.chilenastats.ui.screens.Screen
import io.pdaa.chilenastats.ui.screens.login.AuthenticationButton
import io.pdaa.chilenastats.ui.screens.login.launchCredManBottomSheet

@Composable
fun SignInScreen(
    vm: SigninViewModel,
    onClickSignUp: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val email = vm.email.collectAsState()
    val password = vm.password.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val loginScreenState = rememberLoginScreenState(
        snackbarHostState
    )

    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        launchCredManBottomSheet(context) { result ->
            vm.onSignUpWithGoogle(result)
            Log.i("LoginScreen", "Logged in with Google, continuing to team selection")
        }
    }

    Screen {

        Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { contentPadding ->
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(contentPadding)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = com.google.android.gms.base.R.drawable.common_full_open_on_phone),
                    contentDescription = "Auth image",
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(16.dp, 4.dp)
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                )

                OutlinedTextField(
                    singleLine = true,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(16.dp, 4.dp)
                        .border(
                            BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.secondary),
                            shape = RoundedCornerShape(50)
                        ),
                    value = email.value,
                    onValueChange = { vm.updateEmail(it) },
                    placeholder = { Text(stringResource(R.string.email)) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Email"
                        )
                    }
                )

                OutlinedTextField(
                    singleLine = true,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(16.dp, 4.dp)
                        .border(
                            BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.secondary),
                            shape = RoundedCornerShape(50)
                        ),
                    value = password.value,
                    onValueChange = { vm.updatePassword(it) },
                    placeholder = { Text(stringResource(R.string.password)) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Email"
                        )
                    },
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                )

                Button(
                    onClick = { vm.onSignInClick(loginScreenState::navigateAndPopUp) },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(16.dp, 0.dp)
                ) {
                    Text(
                        text = stringResource(R.string.sign_in),
                        fontSize = 16.sp,
                        modifier = modifier.padding(0.dp, 6.dp)
                    )
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )

                Text(
                    text = stringResource(R.string.or),
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.secondary
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )

                AuthenticationButton(buttonText = R.string.sign_in_with_google) { credential ->
                    vm.onSignInWithGoogle(credential, loginScreenState::navigateAndPopUp)
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )

                TextButton(onClick = {
                    onClickSignUp()
                }) {
                    Text(
                        text = stringResource(R.string.sign_up_description),
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }

        }
    }

}