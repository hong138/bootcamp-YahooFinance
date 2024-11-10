<!-- Task 1  -->
  <!-- - Backend Service For YH Finanace -->

<!-- Task 2 -->
  <!-- - CommandLine Runner -->

Task 3
  - Get Stock List(Cache Pattern)
    - If the list of stock symbols is available in Redis, return them directly; otherwise, read them from database and refresh them back to redis, with expiry in 24 hours.

    - Create commandLineRunner to clear the Redis entry during server start process.
    
    - Think about the design and structure of the symbol list being stored in Redis.
    
    - Sample entry in Redis (for stock list)
    {"STOCK-LIST": ["0388.HK", "0700.HK"]}

Task 4
  - Schedule For Extract and Load
    - Create a scheduler (every 5 minutes. i.e. 10:00:00, 10:05:00, etc) in order to get stock quote data, based on the list of symbols stored in Redis/Table (developed in Task 3), and then store the quote data in table
    TSTOCKS_PRICE .

    - Sample YH quote OpenAPI.

    GET: https://query1.finance.yahoo.com/v7/finance/quote?symbols=0388.HK&crumb=nklxXSQfYyr

    - • Store the required JSON data from Yahoo API.
      • symbol
      • regularMarketTime (real market time in Unix timestamp)
      • regularMarketPrice
      • regularMarketChangePercent
      • bid
      • bidSize
      • ask
      • askSize

      - Design the table TSTOCKS_PRICE and implement it by JPA & Entity. Besides the above fields from YH API, please add the fields below.
      • TYPE (5M = 5 Minutes, D = Daily, W = Weekly, M = Monthly)
      • API Datetime (current time stamp)
      • Market Unix Time in normal timestamp (converted from regularMarketTime in JSON)

Task 5 
  - API Design (Reading)
    - Now, we are going to develop an API to provide the 5-min data set for the latest trading day. Before moving to the Solution, let's understand the various scenarios for system behavior.

    - Scenario 1: During the timeframe on the trade date, this API should return all the 5-min data being stored in database or redis.
      - Examples: Before 09:00 a.m. (before the market opens), the API should return the set of 5-min data of the last trade day; after 09:00 a.m. (after the market opens), the API should return the 5-min data for that date only.
    
    - Scenario 2: During the holiday, the API should return a full set of 5-min data on the last trade date.
    - The idea is similar to the following SQL (PostgreSQL syntax).
    - Think about system design (Redis, DB & Scheduler) that is able to achieve the same purpose.

    SQL
    1 -- i.e. regular_market_time is formatted with timestamp.
    2 -- Given a symbol
    3 with max_unix_time as (
    4 select symbol
    5 max(regular_market_unix) as max_unix
    6 from TSTOCKS_PRICE
    7 group by symbol
    8), system_date as (
    9 select m.symbol, to_char (m. regular_market_time, 'YYYYMMDD') as sys_date_str
    10 from TSTOCKS_PRICE m, max_unix_time u
    11 where m. symbol = u. symbol
    12 and m. regular_market_unix = u.max_unix
    13 )
    14 select *
    15 from TSTOCKS_PRICE m, system_date s
    16 where to_char (m. regular_market_time, 'YYYYMMDD') = s.sys_date_str
    17 and m. symbol = ?
    18 order by m. regular_market_unix asc
    19 ;

Task 6 
  - API Development
    - Task 6a 
      - GET System Date (Cache Pattern)
        - Retrieve system date from Redis by stock symbol
          - If it is found, return the date value as System Date.
          - If it is not found, get max. of regular_market_time in YYYY-MM-DD from TSTOCKS_PRICE by symbol and then update the entry in Redis (expiry 8 hours).

          { "SYSDATE-0388.HK": "2024-10-30" }

    Task 6b 
      - API for 5-min Data (Cache Pattern)
        - Develop API to return the list of 5-min data on the system date.
          - Get 5-min Quote Data
            - If the redis entry is not found, retrieve it from the Database table by System date and then create the entry in Redis, with the list of 5-min data, if any. (expiry 12 hours)

          - Sample Redis Entry & the 5-min list (Key: "5MIN-0388.HK")

{
  "5MIN-0388.HK" : {
    "regularMarketTime" : "1717484711",
      "data" : [
        {
            "symbol" : "0388.HK",
            "marketTime": "2024-05-27 10:00:00",
            "regularMarketUnix" : "1717484711",
            "regularMarketPrice" : "270.32",
            "regularMarketChangePercent" : "1.32",
            //...
        },
        { //... }
              ]
        }
}
        - Or if the redis entry (5-min data) is found:
          - Then retrieve the max regularMarketTime from database on System date
            - If DB regularMarketT ime is not found, return empty 5-min data list.
            - If DB regularMarketTime is found, and if DB regularMarketTime > Redis regularMarketTime, replace the list to Redis entry.
            - Otherwise, return the Redis entry value directly.
    - Task 6c 
      - Clear Cache Data (System Date & 5-MIN)
        - Create a scheduler to clear all system date AND entries at 08:55 a.m. every day.
        - Create commandLineRunner to clear all system date Redis entries during server start process.
