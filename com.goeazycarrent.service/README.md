# GoEazy Car Rent Service 

####Description
This Service provides endpoint for Car Rent Service app. Service contains CRUD operations 
to Create , Update, Delete Product and Warehouses.

####BITBUCKET REPO

https://github.com/robosoto/reservation-system/tree/main/com.goeazycarrent.service

####Swagger Docs
Once app is started by running the project as Spring boot 

http://localhost:8080/swagger-ui/index.html#/

#### API Description

 We created a class `VehiclesServiceController` with several methods for handling incoming http requests. Each method is associated with a unique api endpoint-url and http-verb combination. 

    The request handlers we wrote were:

    | Http Verb | Api Endpoint URL | 
    |----------|------------------|
    | GET | /vehicles/type/{type} |
    | GET | /vehicles/all |
    | GET | /vehicles/location/{location}
   

We created a class `ReservationServiceController` with several methods for handling incoming http requests. Each method is associated with a unique api endpoint-url and http-verb combination. 

    The request handlers we wrote were:

    | Http Verb | Api Endpoint URL | 
    |----------|------------------|
    | GET | /reservation/id/{id} |
    | POST | /reservation/confirm |
    | POST | /reservation/cancel/{id} |
    | PUT | /reservation/modify |

We created a class `CustomerServiceController` with several methods for handling incoming http requests. Each method is associated with a unique api endpoint-url and http-verb combination. 

    The request handlers we wrote were:

    | Http Verb | Api Endpoint URL | 
    |----------|------------------|
    | POST | /customer/signup |
 


### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.14/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.14/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.14/reference/htmlsinge/index.html#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)