package com.zhuinden.simplestackftuecomposesample.features.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zhuinden.simplestackcomposeintegration.services.rememberService
import com.zhuinden.simplestackftuecomposesample.core.compose.TextFieldWithCursorAtEnd
import com.zhuinden.simplestackftuecomposesample.utils.set
import com.zhuinden.simplestackftuecomposesample.utils.subscribeAsState

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
) {
    val loginViewModel = rememberService<LoginViewModel>()

    val username = loginViewModel.username.subscribeAsState()
    val password = loginViewModel.password.subscribeAsState()

    val isLoginEnabled = loginViewModel.isLoginEnabled.subscribeAsState(initial = false)

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextFieldWithCursorAtEnd(
            value = username.value ?: "",
            placeholder = { Text("Username") },
            onValueChange = loginViewModel.username::set,
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextFieldWithCursorAtEnd(
            value = password.value ?: "",
            placeholder = { Text("Password") },
            onValueChange = loginViewModel.password::set,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = loginViewModel::onLoginClicked,
            enabled = isLoginEnabled.value,
        ) {
            Text(text = "LOGIN")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = loginViewModel::onRegisterClicked,
        ) {
            Text(text = "REGISTER")
        }
    }
}