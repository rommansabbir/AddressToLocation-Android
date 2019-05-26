package com.rommansabbir.getlocationfromaddress

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.util.Log
import java.io.IOException
import java.util.Locale

/**
 * Set the parent context
 * Instantiate callback interface
 * @param context
 */
object AddressToLocationListener {

    /**
     * This method is responsible for returning location from a address using Geocoder
     * Pass the address and result limit
     * Address can be a place/area/city/university name, which is known to Google
     * Result limit for limit the list of result
     * @param context
     * @param address
     * @param resultLimit
     * @param callback
     */
    fun getLocationFromAddress(context: Context, address: String, resultLimit: Int?, callback: LocationFromAddressCallback) {
        val geocoder = Geocoder(context, Locale.getDefault())
        var addresses: List<Address>? = null
        try {
            addresses = geocoder.getFromLocationName(address, resultLimit!!)
        } catch (e: IOException) {
            /**
             * Check for error
             * If error occur, notify the interface
             */
            if (e.message == "Service not Available") {
                callback.onFailure("Nothing found. Reboot your device to get location")
            } else {
                callback.onFailure(e.message!!)
            }
        }

        /**
         * Check if address is null or not
         * If not null, then extract the location from the address list
         * Notify the interface on location get success
         */
        if (addresses != null && addresses.isNotEmpty()) {
            val location = Location(addresses[0].locality)
            location.latitude = addresses[0].latitude
            location.longitude = addresses[0].longitude
            callback.onSuccess(location)
        } else {
            callback.onFailure("Nothing found. Please type a valid address")
        }
    }

    /**
     * This method is responsible for returning list of address from a address using Geocoder
     * Pass the address and result limit
     * Address can be a place/area/city/university name, which is known to Google
     * Result limit for limit the list of result
     * @param context
     * @param address
     * @param resultLimit
     * @param callback
     */
    fun getDetailFromAddress(context: Context, address: String, resultLimit: Int?, callback: DetailFromAddressCallback) {
        val geocoder = Geocoder(context, Locale.getDefault())
        var addresses: List<Address>? = null
        try {
            addresses = geocoder.getFromLocationName(address, resultLimit!!)
        } catch (e: IOException) {
            /**
             * Check for error
             * If error occur, notify the interface
             */
            if (e.message == "Service not Available") {

                callback.onFailure("Nothing found. Reboot your device to get location")
            } else {

                callback.onFailure(e.message!!)
            }
        }

        /**
         * Check if address is null or not
         * If not null, then extract the list from the address list
         * Notify the interface on list get success
         */
        if (addresses != null) {
            callback.onSuccess(addresses)
        }
    }

    /**
     * This method is responsible for returning list of address from a location using Geocoder
     * Pass the location and result limit
     * @param context
     * @param location
     * @param resultLimit
     * @param callback
     */
    fun getAddressFromLocation(context: Context, location: Location, resultLimit: Int?, callback: AddressFromLocationCallback) {
        val geocoder = Geocoder(context, Locale.getDefault())
        var addresses: List<Address>? = null
        try {
            addresses = geocoder.getFromLocation(location.latitude, location.longitude, resultLimit!!)
        } catch (e: IOException) {
            /**
             * Check for error
             * If error occur, notify the interface
             */
            if (e.message == "Service not Available") {
                callback.onFailure("Nothing found. Reboot your device to get location")
            } else {
                callback.onFailure(e.message!!)
            }
        }

        /**
         * Check if address is null or not
         * If not null, then extract the list from the address list
         * Notify the interface on list get success
         */
        if (addresses != null) {
            callback.onSuccess(addresses)
        }
    }
}

