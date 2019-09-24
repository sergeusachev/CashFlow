package com.serge.usachev.cashflow.location

import android.annotation.SuppressLint
import com.google.android.gms.location.FusedLocationProviderClient
import com.serge.usachev.cashflow.model.Coordinate
import timber.log.Timber

class TransactionLocationManager {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @SuppressLint("MissingPermission")
    fun requestCurrentCoordinates(coordsFind: (Coordinate) -> Unit) {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            Timber.d("LATITUDE: ${location.latitude}, LONGITUDE: ${location.longitude}")
            if (location == null) {

            } else {

            }
        }
    }
}