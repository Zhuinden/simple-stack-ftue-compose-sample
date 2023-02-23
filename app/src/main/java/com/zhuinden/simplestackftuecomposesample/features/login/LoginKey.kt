package com.zhuinden.simplestackftuecomposesample.features.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zhuinden.simplestack.ServiceBinder
import com.zhuinden.simplestackcomposeintegration.services.rememberService
import com.zhuinden.simplestackextensions.servicesktx.add
import com.zhuinden.simplestackextensions.servicesktx.lookup
import com.zhuinden.simplestackftuecomposesample.app.AuthenticationManager
import com.zhuinden.simplestackftuecomposesample.app.ComposeKey
import com.zhuinden.simplestackftuecomposesample.utils.set
import kotlinx.parcelize.Parcelize

@Parcelize
class LoginKey : ComposeKey() {
    @Suppress("RemoveExplicitTypeArguments")
    override fun bindServices(serviceBinder: ServiceBinder) {
        with(serviceBinder) {
            add(LoginViewModel(lookup<AuthenticationManager>(), backstack))
        }
    }

    @Composable
    override fun ScreenComposable(modifier: Modifier) {
        val loginViewModel = rememberService<LoginViewModel>()

        val username = loginViewModel.username.subscribeAsState(initial = "")
        val password = loginViewModel.password.subscribeAsState(initial = "")

        val isLoginEnabled = loginViewModel.isLoginEnabled.subscribeAsState(initial = false)

        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(value = username.value, placeholder = { Text("Username") }, onValueChange = { value: String ->
                loginViewModel.username.set(value)
            })

            Spacer(modifier = Modifier.height(8.dp))

            TextField(value = password.value, placeholder = { Text("Password") }, onValueChange = { value: String ->
                loginViewModel.password.set(value)
            })

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { loginViewModel.onLoginClicked() }, enabled = isLoginEnabled.value) {
                Text(text = "LOGIN")
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = { loginViewModel.onRegisterClicked() }) {
                Text(text = "REGISTER")
            }
        }
    }
}