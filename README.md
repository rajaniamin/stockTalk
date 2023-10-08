# stockTalk
# Candle API

This is a RESTful API for managing Stock data.

# Tech Stack
 **Java**
- **Spring Boot**
- **H2 Database**
- **Maven**
- **Postman**

## API Endpoints

### Add Candle Data

- **URL**: `/candles/add`
- **Method**: `GET`
- **Description**: Adds candle data to the database from a JSON file.
- **Request Parameters**: None
- **Response**: Success message or error message if something goes wrong.

### Get First Opening Range Breakout (ORB) Candle

- **URL**: `/candles/get-first-orb-candle`
- **Method**: `GET`
- **Description**: Retrieves the first Opening Range Breakout (ORB) candle within a specified time interval.
- **Request Parameters**:
  - `time` (integer): Time interval in minutes.
- **Response**: ORB candle information or an error message if not found.

### Get Candles from a New Interval

- **URL**: `/candles/get-candles-new-interval`
- **Method**: `GET`
- **Description**: Retrieves candles from a new time interval.
- **Request Parameters**:
  - `time` (integer): Time interval in minutes.
- **Response**: List of candles or an error message if not found.

## Usage

1. Add Candle Data:
   - Make a GET request to `/candles/add` to add candle data.

2. Get the First ORB Candle:
   - Make a GET request to `/candles/get-first-orb-candle?time=15` to retrieve the first ORB candle within a 15-minute interval.

3. Get Candles from a New Interval:
   - Make a GET request to `/candles/get-candles-new-interval?time=30` to retrieve candles from a 30-minute interval.


