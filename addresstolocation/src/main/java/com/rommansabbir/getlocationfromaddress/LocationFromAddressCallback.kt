package com.rommansabbir.getlocationfromaddress

import android.location.Location

interface LocationFromAddressCallback {
    fun onSuccess(location: Location)
    fun onFailure(errorMessage: String)
}