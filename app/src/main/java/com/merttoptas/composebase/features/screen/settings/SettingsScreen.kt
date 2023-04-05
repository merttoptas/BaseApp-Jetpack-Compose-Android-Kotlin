package com.merttoptas.composebase.features.screen.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.merttoptas.composebase.R
import androidx.hilt.navigation.compose.hiltViewModel
import com.merttoptas.composebase.BuildConfig
import com.merttoptas.composebase.features.component.RickAndMortyScaffold
import com.merttoptas.composebase.features.component.RickAndMortyText
import com.merttoptas.composebase.features.component.RickAndMortyTopBar

/**
 * Created by merttoptas on 22.03.2022
 */

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val viewState by viewModel.uiState.collectAsState()
    RickAndMortyScaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            RickAndMortyTopBar(
                text = stringResource(id = R.string.settings_screen_title),
            )
        },
        content = {
            Content(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                isDark = viewState.isDark,
                onTriggerEvent = viewModel::onTriggerEvent
            )
        },
        containerColor = MaterialTheme.colorScheme.surface
    )
}

@Composable
private fun Content(
    modifier: Modifier,
    isDark: Boolean = false,
    onTriggerEvent: (SettingsViewEvent) -> Unit
) {
    val icon: (@Composable () -> Unit)? = if (isDark) {
        {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = null,
                modifier = Modifier.size(SwitchDefaults.IconSize),
            )
        }
    } else {
        null
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .border(
                    border = BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                RickAndMortyText(
                    text = stringResource(id = R.string.settings_dark_theme),
                    style = MaterialTheme.typography.bodyMedium
                )
                Switch(
                    modifier = Modifier.semantics { contentDescription = "Ok with icon" },
                    thumbContent = icon,
                    checked = isDark,
                    onCheckedChange = {
                        onTriggerEvent.invoke(SettingsViewEvent.OnChangeTheme)
                    })
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                RickAndMortyText(
                    text = stringResource(id = R.string.settings_screen_app_version_title),
                    style = MaterialTheme.typography.bodyMedium
                )
                RickAndMortyText(
                    text = BuildConfig.VERSION_NAME,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
