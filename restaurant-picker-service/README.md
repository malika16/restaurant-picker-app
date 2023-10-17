# Restaurant Picker API

## Table of Contents

- [Introduction](#introduction)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
    - [Building](#building)
    - [Running](#running)
- [Database Setup](#database-setup)
- [API Endpoints](#api-endpoints)
- [Features](#features)
- [Contributing](#contributing)
- [License](#license)

## Introduction

The Restaurant Picker API is designed to facilitate the selection of restaurants within a group of users.
It allows users to create sessions, invite others, submit restaurant choices, and randomly select a restaurant from the submissions.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java 17
- Spring Boot 2.7+
- Maven 3.8+

## Getting Started

To get a local copy up and running, follow these simple steps.

### Building

1. Clone the repository:

   ```shell
   git clone https://github.com/malika16/restaurant-picker-api.git

2.Navigate to the project directory:

    cd restaurant-picker-api

3. Build the project using Maven: mvn clean install

4. To run the API, execute the following command: java -jar target/restaurant-picker-service-1.0-SNAPSHOT.jar
   The API should be running at http://localhost:8080.

### Database Setup

H2 Database has been used here. The credentials are added in the application.properties.

### API Endpoints

Refer to the API documentation: https://github.com/malika16/restaurant-picker-app/blob/main/restaurant-picker-service/src/main/resources/openapi/restaurant-picker-api.yaml
for detailed information on these endpoints and their request/response formats.

### Features
 1. Create and manage sessions.
 2. Invite users to sessions.
 3. Submit restaurant choices within a session.
 4. Randomly pick a restaurant from submitted choices. End the session to prevent new entries.


