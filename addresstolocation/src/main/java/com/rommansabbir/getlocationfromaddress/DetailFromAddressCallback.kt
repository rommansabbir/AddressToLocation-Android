package com.rommansabbir.getlocationfromaddress

import android.location.Address

interface DetailFromAddressCallback {
    fun onSuccess(address: List<Address>)
    fun onFailure(errorMessage: String)
}