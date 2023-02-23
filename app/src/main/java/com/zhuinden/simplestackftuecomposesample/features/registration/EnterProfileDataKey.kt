package com.zhuinden.simplestackftuecomposesample.features.registration

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zhuinden.simplestack.ScopeKey
import com.zhuinden.simplestackcomposeintegration.services.rememberService
import com.zhuinden.simplestackftuecomposesample.app.ComposeKey
import com.zhuinden.simplestackftuecomposesample.utils.set
import kotlinx.parcelize.Parcelize

@Parcelize
class EnterProfileDataKey : ComposeKey(), ScopeKey.Child {
    override fun getParentScopes(): List<String> = listOf(RegistrationViewModel::class.java.name)

    @Composable
    override fun ScreenComposable(modifier: Modifier) {
        val registrationViewModel = rememberService<RegistrationViewModel>()

        val fullName = registrationViewModel.fullName.subscribeAsState(initial = "")
        val bio = registrationViewModel.bio.subscribeAsState(initial = "")

        val isEnabled = registrationViewModel.isEnterProfileNextEnabled.subscribeAsState(initial = false)

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = fullName.value,
                singleLine = true,
                placeholder = { Text("Full name") },
                onValueChange = { username ->
                    registrationViewModel.fullName.set(username)
                })

            Spacer(modifier = Modifier.height(8.dp))

            TextField(value = bio.value,
                singleLine = true,
                placeholder = { Text("Bio") },
                onValueChange = { password ->
                    registrationViewModel.bio.set(password)
                })

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = {
                registrationViewModel.onEnterProfileNextClicked()
            }, enabled = isEnabled.value) {
                Text(text = "Next")
            }
        }
    }
}