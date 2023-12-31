# Go Eazy Car Rentals

Go Eazy Car Rentals is a web application that allows users to easily reserve and manage car rentals. Whether you're planning a road trip or need a rental car for daily use, our system makes the reservation process smooth and convenient.

Make a reservation on Go Eazy Car Rentals! \
http://goeazystore.s3-website-us-east-1.amazonaws.com/

The application is hosted on AWS -- The user interface is deployed in an S3 bucket, the back end on AWS Elastic Beanstalk, and the database is running on RDS Aurora Serverless.

## Table of Contents

- [Features](#features)
- [Demo](#demo)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)


## Features

- Browse and search for available cars
- Reserve cars for specific dates and locations
- Manage, update, and cancel existing reservations
- User-friendly interface
- Full application can be translated between English and Spanish with the click of a button

## Demo

Check out our live demo at: http://goeazystore.s3-website-us-east-1.amazonaws.com/

## Getting Started

### Prerequisites

Before you begin, ensure you have met the following requirements:

- [Node.js](https://nodejs.org/) installed
- [Angular CLI](https://angular.io/cli) installed
- [Spring](https://spring.io/quickstart) 

### Installation

1. Clone the repository:

   ```
   git clone https://github.com/robosoto/reservation-system.git
   cd reservation-system

   ```

2. Run the back end service:

   ```
   cd com.goeazycarrent.service
   mvn spring-boot:run
   ```

3. Install dependencies for the front end Angular application:

   ```
   cd ../reservation-system-frontend
   npm install
   ```

5. Start the frontend Angular server: 

    ```
    ng serve
    ```

## Usage 

1. Visit http://localhost:4200 in your web browser.

2. Browse available cars, select your desired vehicle, and reserve it for 
   your preferred dates and location.

3. Manage your reservations through the user flow.

If you have any questions, issues, or suggestions, please feel free to contact us.

Thank you for using Go Eazy Car Rentals!
