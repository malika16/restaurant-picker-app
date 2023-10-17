# Restaurant Picker API

### API Endpoints

Refer to the API documentation: https://github.com/malika16/restaurant-picker-app/blob/main/restaurant-picker-service/src/main/resources/openapi/restaurant-picker-api.yaml
for detailed information on these endpoints and their request/response formats.
 

## API Scope

1. Create a Session API: The api allows a user to create a session, becoming the initiator.

2. Join a Session API: Initiators can invite others to join the session. 

3. Submit a Restaurant API: Once in a session, users can submit their restaurant of choice. 

4. View Submitted Restaurants API: All users within the session can view the list of submitted restaurants. Backend api is used to fetch all restaurants added against the same session id

5. Pick Restaurant and end a Session API: The session initiator can end the session, which triggers the random selection of a restaurant from the submitted list.

7. Central exception handling(ControllerAdvice) is done at the service end with error code and message.
   
9. Open API code generator plugin is used at backend service to create API and DTOs, please refer to pom.xml.

10. Validator has been added to do common validations.


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

H2 Database has been used here. The credentials are added in the application.properties. No script execution is required.


### Features
 1. Create and manage sessions.
 2. Invite users to sessions.
 3. Submit restaurant choices within a session.
 4. Randomly pick a restaurant from submitted choices. End the session to prevent new entries.


