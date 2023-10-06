# Go Eazy Car Rentals Back End Service

This Service provides API endpoints for our Go Eazy Car Rentals application. Service contains CRUD operations for creating and managing vehicle reservations.

####Swagger Docs
http://project2-goeazy-carrent-env.eba-xjdmbrhh.us-east-1.elasticbeanstalk.com:8080/swagger-ui/index.html#/

#### API Description

 We created a class `VehiclesServiceController` with several methods for handling incoming http requests. Each method is associated with a unique api endpoint-url and http-verb combination. 

    The request handlers we wrote were:

    | Http Verb | Api Endpoint URL | 
    |----------|------------------|
    | GET | /vehicles/type/{type} |
    | GET | /vehicles/all |
    | GET | /vehicles/location/{location} |
    | GET | /vehicles/filter?location={location}&vehicleType={vehicleType} |
   

We created a class `ReservationServiceController` with several methods for handling incoming http requests. Each method is associated with a unique api endpoint-url and http-verb combination. 

    The request handlers we wrote were:

    | Http Verb | Api Endpoint URL | 
    |----------|------------------|
    | GET | /reservation/id/{id} |
    | POST | /reservation/confirm |
    | POST | /reservation/cancel |
    | PUT | /reservation/modify |
    | GET | /reservation/{location}/{startDate}/{endDate} |

We created a class `CustomerServiceController` with several methods for handling incoming http requests. Each method is associated with a unique api endpoint-url and http-verb combination. 

    The request handlers we wrote were:

    | Http Verb | Api Endpoint URL | 
    |----------|------------------|
    | POST | /customer/signup |
