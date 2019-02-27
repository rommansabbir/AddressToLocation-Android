package com.rommansabbir.getlocationfromaddress;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.util.Log;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class AddressToLocationListener {
    private static final String TAG = "AddressToLocation";
    private Context context;
    private AddressToLocationListenerInterface listenerInterface;

    /**
     * Set the parent context
     * Instantiate callback interface
     * @param context
     */
    public AddressToLocationListener(Context context) {
        this.context = context;
        listenerInterface = (AddressToLocationListenerInterface) context;
    }

    /**
     * This method is responsible for returning location from a address using Geocoder
     * Pass the address and result limit
     * Address can be a place/area/city/university name, which is known to Google
     * Result limit for limit the list of result
     * @param address
     * @param resultLimit
     */
    public void getLocationFromAddress(String address, Integer resultLimit){
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocationName(address, resultLimit);
        } catch (IOException e) {
            /**
             * Check for error
             * If error occur, notify the interface
             */
            if (e.getMessage().equals("Service not Available")){
                Log.d(TAG, "getLocationFromAddress: ");
                listenerInterface.onAddressToLocationFailure("Nothing found. Reboot your device to get location");
            }
            else {
                Log.d(TAG, "getLocationFromAddress: ");
                listenerInterface.onAddressToLocationFailure(e.getMessage());
            }
        }
        /**
         * Check if address is null or not
         * If not null, then extract the location from the address list
         * Notify the interface on location get success
         */
        if(addresses != null && addresses.size()!=0){
            Log.d(TAG, "getLocationFromAddress: ");
            Location location = new Location(addresses.get(0).getLocality());
            location.setLatitude(addresses.get(0).getLatitude());
            location.setLongitude(addresses.get(0).getLongitude());
            listenerInterface.onAddressToLocationSuccess(location);
        }
        else {
            listenerInterface.onAddressToLocationFailure("Nothing found. Please type a valid address");
        }
    }

    /**
     * This method is responsible for returning list of address from a address using Geocoder
     * Pass the address and result limit
     * Address can be a place/area/city/university name, which is known to Google
     * Result limit for limit the list of result
     * @param address
     * @param resultLimit
     */
    public void getDetailFromAddress(String address, Integer resultLimit){
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocationName(address, resultLimit);
        } catch (IOException e) {
            /**
             * Check for error
             * If error occur, notify the interface
             */
            if (e.getMessage().equals("Service not Available")){
                Log.d(TAG, "getDetailFromAddress: ");
                listenerInterface.onDetailFromAddressFailure("Nothing found. Reboot your device to get location");
            }
            else {
                Log.d(TAG, "getDetailFromAddress: ");
                listenerInterface.onAddressToLocationFailure(e.getMessage());
            }
        }
        /**
         * Check if address is null or not
         * If not null, then extract the list from the address list
         * Notify the interface on list get success
         */
        if(addresses != null){
            Log.d(TAG, "getDetailFromAddress: ");
            listenerInterface.onDetailFromAddressSuccess(addresses);
        }
    }

    /**
     * This method is responsible for returning list of address from a location using Geocoder
     * Pass the location and result limit
     * @param location
     * @param resultLimit
     */
    public void getAddressFromLocation(Location location, Integer resultLimit){
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), resultLimit);
        } catch (IOException e) {
            /**
             * Check for error
             * If error occur, notify the interface
             */
            if (e.getMessage().equals("Service not Available")){
                Log.d(TAG, "getDetailFromAddress: ");
                listenerInterface.onDetailFromAddressFailure("Nothing found. Reboot your device to get location");
            }
            else {
                Log.d(TAG, "getDetailFromAddress: ");
                listenerInterface.onAddressFromLocationFailure(e.getMessage());
            }
        }
        /**
         * Check if address is null or not
         * If not null, then extract the list from the address list
         * Notify the interface on list get success
         */
        if(addresses != null){
            Log.d(TAG, "getDetailFromAddress: ");
            listenerInterface.onAddressFromLocationSuccess(addresses);
        }
    }


    /**
     * Destroy the callback after usages
     */
    public void destroyCallback(){
        context = null;
        listenerInterface = null;
    }

    public interface AddressToLocationListenerInterface{
        void onAddressToLocationSuccess(Location location);
        void onAddressToLocationFailure(String errorMessage);

        void onDetailFromAddressSuccess(List<Address> address);
        void onDetailFromAddressFailure(String errorMessage);

        void onAddressFromLocationSuccess(List<Address> address);
        void onAddressFromLocationFailure(String errorMessage);

    }
}

