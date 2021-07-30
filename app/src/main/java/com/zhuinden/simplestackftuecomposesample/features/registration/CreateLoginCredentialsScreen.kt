package com.zhuinden.simplestackftuecomposesample.features.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.zhuinden.simplestack.ScopeKey
import com.zhuinden.simplestackcomposeintegration.services.rememberService
import com.zhuinden.simplestackftuecomposesample.app.ComposeKey
import com.zhuinden.simplestackftuecomposesample.utils.set
import kotlinx.parcelize.Parcelize

@Parcelize
class CreateLoginCredentialsScreen : ComposeKey(), ScopeKey.Child {
    override fun getParentScopes(): List<String> = listOf(RegistrationViewModel::class.java.name)

    @Composable
    override fun ScreenComposable(modifier: Modifier) {
        val registrationViewModel = rememberService<RegistrationViewModel>()

        val username = registrationViewModel.username.subscribeAsState(initial = "")
        val password = registrationViewModel.password.subscribeAsState(initial = "")

        val isEnabled = registrationViewModel.isRegisterAndLoginEnabled.subscribeAsState(initial = false)

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = username.value,
                singleLine = true,
                placeholder = { Text("Username") },
                onValueChange = { username ->
                    registrationViewModel.username.set(username)
                })

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = password.value,
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                placeholder = { Text("Password") },
                onValueChange = { password ->
                    registrationViewModel.password.set(password)
                })

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = {
                registrationViewModel.onRegisterAndLoginClicked()
            }, enabled = isEnabled.value) {
                Text(text = "Register and login")
            }
        }
    }
}