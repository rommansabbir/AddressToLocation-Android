package com.rommansabbir.getlocationfromaddress

import android.location.Address

interface AddressFromLocationCallback {
    fun onSuccess(address: List<Address>)
    fun onFailure(errorMessage: String)
}