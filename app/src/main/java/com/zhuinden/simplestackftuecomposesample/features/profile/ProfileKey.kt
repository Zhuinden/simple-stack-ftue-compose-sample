package com.zhuinden.simplestackftuecomposesample.features.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.zhuinden.simplestack.ServiceBinder
import com.zhuinden.simplestackextensions.servicesktx.add
import com.zhuinden.simplestackextensions.servicesktx.lookup
import com.zhuinden.simplestackftuecomposesample.app.AuthenticationManager
import com.zhuinden.simplestackftuecomposesample.app.ComposeKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileKey(
    val username: String
) : ComposeKey() {
    @Suppress("RemoveExplicitTypeArguments")
    override fun bindServices(serviceBinder: ServiceBinder) {
        with(serviceBinder) {
            add(ProfileViewModel(lookup<AuthenticationManager>(), backstack))
        }
    }

    override fun getScopeTag(): String = toString()

    @Composable
    override fun ScreenComposable(modifier: Modifier) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Hello $username!")
        }
    }
}
