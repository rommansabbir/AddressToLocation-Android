# AddressToLocation-Android
## Documentation

### Installation
---
Step 1. Add the JitPack repository to your build file 

```gradle
	allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
```

Step 2. Add the dependency

```gradle
	dependencies {
         implementation 'com.github.rommansabbir:AddressToLocation-Android:Tag'
	}
```

---

### Version available

| Releases        
| ------------- |
| v1.0.1        |
| v1.0          |


# Usages

### For Java: 

```java
public class MainActivity extends AppCompatActivity implements
        AddressToLocationListener.AddressToLocationListenerInterface {
    private AddressToLocationListener addressToLocationListener;
    /**
     * Declare a dummy LOCATION object
     */
    private Location LOCATION = new Location("MainActivity");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Instantiate AddressToLocationListener
         */
        addressToLocationListener = new AddressToLocationListener(this);

        /**
         * This method is responsible for getting LOCATION from  address
         * Pass the address & result limit through parameter
         * @param address
         * @param resultLimit
         */
        addressToLocationListener.getLocationFromAddress("ADDRESS_HERE", 1);

        /**
         * This method is responsible for getting address from a location
         * Pass the LOCATION & result limit through parameter
         * @param location
         * @param resultLimit
         */
        addressToLocationListener.getAddressFromLocation(LOCATION, 1);

        /**
         * This method is responsible for getting detail of a address
         * Pass the address & result limit through parameter
         * @param address
         * @param resultLimit
         */
        addressToLocationListener.getDetailFromAddress("YOUR_ADDRESS", 1);

    }

    @Override
    public void onAddressToLocationSuccess(Location location) {
        //TODO Implement your logic here
    }

    @Override
    public void onAddressToLocationFailure(String errorMessage) {
        //TODO Implement your logic here
    }

    @Override
    public void onDetailFromAddressSuccess(List<Address> address) {
        //TODO Implement your logic here
    }

    @Override
    public void onDetailFromAddressFailure(String errorMessage) {
        //TODO Implement your logic here
    }

    @Override
    public void onAddressFromLocationSuccess(List<Address> address) {
        //TODO Implement your logic here
    }

    @Override
    public void onAddressFromLocationFailure(String errorMessage) {
        //TODO Implement your logic here
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**
         * Destroy the callback after it usages for fail safe
         */
        addressToLocationListener.destroyCallback();
    }
}
```

### Contact me
[Portfolio](https://www.rommansabbir.com/) | [LinkedIn](https://www.linkedin.com/in/rommansabbir/) | [Twitter](https://www.twitter.com/itzrommansabbir/) | [Facebook](https://www.facebook.com/itzrommansabbir/)
