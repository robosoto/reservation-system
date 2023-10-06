This directory contains SQL files for generating the tables of our database as well as our mock data. 

Also included is an EER diagram of our database schema. The schema is as follows:

## Vehicles
|  Column        |  Data Type |
| -------------- | ---------- |
|  id            |  int       |\
|  make          |  varchar   |\
|  model         |  varchar   |\
|  type          |  varchar   |\
|  price_per_day |  int       |\

## Reservations
|  Column        |  Data Type |  Foreign Key  |
| -------------- | ---------- | ------------- |
|  id            |  varchar   |
|  start_date    |  datetime  |
|  end_date      |  datetime  |
|  location      |  varchar   |
|  customer_id   |  int       |  FK Customers  |\
|  vehicle_id    |  int       |  FK Vehicles   |\
|  status        |  varchar   |

## Customers
|  Column        |  Data Type |
| -------------- | ---------- |
|  id            |  int       |\
|  email         |  varchar   |\
|  name          |  varchar   |\
