package com.abanoub.cities.feature.cities.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abanoub.cities.core.ui.CircularProgress
import com.abanoub.cities.core.ui.TopAppBar
import com.abanoub.cities.feature.cities.domain.model.City
import com.abanoub.cities.feature.cities.presentation.component.SearchBox
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun CitiesRoute(
    modifier: Modifier = Modifier,
    viewModel: CitiesViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    CitiesScreen(
        modifier = modifier,
        uiState = uiState,
        onQueryChange = { query -> viewModel.onEvent(CitiesUiEvent.SearchCities(query)) },
        onCityClick = {}
    )
}

@Composable
private fun CitiesScreen(
    modifier: Modifier = Modifier,
    uiState: CitiesUiState,
    onQueryChange: (String) -> Unit,
    onCityClick: (City) -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    var searchQuery by rememberSaveable { mutableStateOf("") }

    // Show snack bar if there is an error
    LaunchedEffect(uiState.isError) {
        when {
            uiState.isError -> scope.launch {
                snackBarHostState.showSnackbar(uiState.errorMessage.asString(context))
            }
        }
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        topBar = { TopAppBar(title = stringResource(R.string.nav_cities_title)) },
        snackbarHost = { SnackbarHost(snackBarHostState) },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Search box
            item {
                SearchBox(
                    modifier = Modifier.fillMaxWidth(),
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                        onQueryChange(it)
                    }
                )
            }

            item { Spacer(modifier = Modifier.height(14.dp)) }

            // Cities list
            items(uiState.cities, key = { it.name }) { city ->
                CityItem(city = city, onClick = { onCityClick(city) })
            }
        }

        // Show progress bar if loading
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (uiState.isLoading) {
                CircularProgress(
                    modifier = Modifier.size(36.dp)
                )
            }
        }

        // Show empty screen if there are no cities
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (uiState.cities.isEmpty()) {
                EmptyScreen()
            }
        }
    }
}

@Composable
private fun CityItem(
    modifier: Modifier = Modifier,
    city: City,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "${city.name}, ${city.country}",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${city.longitude}, ${city.latitude}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun EmptyScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(R.string.empty_cities_message),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )
    }
}