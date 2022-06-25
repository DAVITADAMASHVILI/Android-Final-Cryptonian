![app](https://i.imgur.com/oA8E8nv.png)
# Android Final Project: Cryptonian

# About
Our app aims making it easier for everyone to understand value of most popular crypto currencies. And to do so without any extra hassle.

# Installation
Clone this repository and import into **Android Studio**
```bash
git clone git@github.com/DAVITADAMASHVILI/Android-Final-Cryptonian.git
```

# Technical Docs
- [`Gradle configuration`](#gradle-configuration)
- [`Navigation`](#navigation)
- [`API Calls`](#api-calls)
- [`Notification`](#notification)
- [`Room`](#room)
- [`Recycler View`](#recycler-view)


## Gradle configuration
   Here is main gradle configuration we use:
```bash
implementation 'com.android.databinding:viewbinding:7.2.1'
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.google.code.gson:gson:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
implementation 'com.squareup.picasso:picasso:2.8'
implementation "androidx.navigation:navigation-fragment-ktx:2.4.2"
implementation "androidx.navigation:navigation-ui-ktx:2.4.2"
implementation "androidx.room:room-runtime:2.4.2"
kapt "androidx.room:room-compiler:2.4.2"
```
## Navigation
  We have 2 main screens: Home screen, where all the coins are displayed, and converter page where user can convert any listed crypto currency to USD.
  Crypto coins are loaded automatically as soon as app starts and displayed on home page sorted by their rank. Also there are 2 ways to go to converter page:
  1) Using navigation controller - by pressing corresponding button on bottom navigation view. Doing so will load converter page fragment and automatically select 
     rank N1 Crypto currency.
  2) another way is to click on any coin from home page. This will open converter page with our coin selected already.

## API Calls
  For our app to work we are using [`CoinGecko`](https://www.coingecko.com/en/api/documentation) api method: https://api.coingecko.com/api/v3/coins/markets
  With this method our app loads all the crypto coins(50 for now). This is the only api service we use. We use retrofit2 and RestClient for this.
  
## Notification
   In here we have LowBatteryReceiver class which as the name suggests triggers as soon as battery level is low(Intent.ACTION_BATTERY_LOW). After that using 
   NotificationUtil user receives notification reminding them about our app.

## Room
   We are also room to store all fetched coins in database and then use them for converter page.
   
## Recycler View
   After data is loaded from api, we display it using Recycler view.

### Contributors
	- Davit Adamashvili | daviti.adamashvili.1@btu.edu.ge
    - Beka Baghaturia | beka.baghaturia.1@btu.edu.ge
