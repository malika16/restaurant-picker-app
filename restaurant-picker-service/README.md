## Restaurant Picker API

## API Endpoints

For detailed information on these endpoints and their request/response formats, please refer to the [API documentation](https://github.com/malika16/restaurant-picker-app/blob/main/restaurant-picker-service/src/main/resources/openapi/restaurant-picker-api.yaml).

## API Scope

1. **Create a Session API**: This API allows a user to create a session, becoming the initiator.

2. **Join a Session API**: Initiators can invite others to join the session.

3. **Submit a Restaurant API**: Once in a session, users can submit their restaurant of choice.

4. **View Submitted Restaurants API**: All users within the session can view the list of submitted restaurants. Backend APIs are used to fetch all restaurants added against the same session ID.

5. **Pick Restaurant and End a Session API**: The session initiator can end the session, triggering the random selection of a restaurant from the submitted list.

7. Central exception handling (`ControllerAdvice`) is implemented at the service end, providing error codes and messages.

9. The OpenAPI code generator plugin is employed in the backend service to create APIs and DTOs. Please refer to the `pom.xml` for more details.

10. Validators have been added to perform common validations.

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

    cd restaurant-picker-service

3. Build the project using Maven: mvn clean install

4. To run the API, execute the following command: java -jar target/restaurant-picker-service-1.0-SNAPSHOT.jar
   The API should be running at http://localhost:8080.

### Database Setup

H2 Database has been used here. The credentials are added in the application.properties. No script execution is required.

