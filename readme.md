
# Cryptospot App

An intuitive app to browse and monitor cryptocurrency markets and details.


## Tech Stack

- MVVM architecture design (Model View ViewModel)
- Repository Pattern
- Coroutine Flow
- Koin Dependency Injection
- Coil Image Loader
- Retrofit
- firebase

## How to Use

#### First Run: Splash Screen

- When you first launch the app, it will start with a splash screen to initialize.

#### Home Screen

- After the splash screen, you will land on the Home screen.
- Here, you can view a list of coins along with their prices displayed in both IDR (Indonesian Rupiah) and USD formats.

#### View Coin Details

- To explore detailed information about a specific coin:
    - Tap on the coin from the list on the Home screen.
    - This will take you to a detailed view where you can see:
        - **Name**: The name of the coin.
        - **Price**: Current price of the coin.
        - **Image**: Visual representation of the coin.
        - **Description**: Brief overview and details about the coin.
    - You can also navigate to CoinGecko for more details by clicking the "Open in CoinGecko" button.

#### Profile

- In the Profile section, you can view your profile data relevant to using the app.

## Filmfolio API Used
[CoinGecko](https://www.coingecko.com/en/api)

#### Get All Coins

```http
GET api/v3/coins/markets
```

| Query | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. CoinGecko Api Key   |
| `vs_currency` | `String` |  Currency for coin price |
| `ids` | `string` | Id for coin |



#### Get Coin by Id

```http
GET api/v3/coins/{id}
```

| Query | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. CoinGecko Api Key   |
| `id` | `string` | Id for coin |
