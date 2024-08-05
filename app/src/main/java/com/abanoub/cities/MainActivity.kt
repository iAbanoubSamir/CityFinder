package com.abanoub.cities

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.abanoub.cities.feature.cities.presentation.Cities
import com.abanoub.cities.feature.cities.presentation.citiesScreen
import com.abanoub.cities.ui.theme.CityFinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CityFinderTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Cities) {
                        citiesScreen(
                            onCityClick = { city ->
                                openGoogleMaps(city.latitude, city.longitude)
                            }
                        )
                    }
                }
            }
        }
    }

    private fun openGoogleMaps(lat: Double, lng: Double) {
        val gmmIntentUri = Uri.parse("geo:$lat,$lng")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        try {
            startActivity(mapIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, getString(R.string.please_install_google_maps), Toast.LENGTH_SHORT)
                .show()
        }
    }
}
