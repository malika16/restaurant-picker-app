# Restaurant Picker Application

This application consists of two repositories: one for the backend APIs and another for the frontend. Please refer to the respective README files for detailed instructions on setting up and running the application.

## API Documentation

Explore the API using the Swagger documentation:

- [Restaurant Picker API Swagger](https://github.com/malika16/restaurant-picker-app/blob/main/restaurant-picker-service/src/main/resources/openapi/restaurant-picker-api.yaml)

## Scope

The Restaurant Picker Application offers the following features:

1. **Create a Session:** Users can create a session, becoming the initiators. A session ID is generated at the backend and used abstractly in the frontend code.

2. **Join a Session:** Initiators have the ability to invite others to join their sessions.

3. **Submit a Restaurant:** Within a session, users can submit their restaurant of choice.

4. **View Submitted Restaurants:** All users within a session can view the list of restaurants submitted. The backend API is used to fetch all restaurants added under the same session ID.

5. **End a Session:** Session initiators can end the session, which triggers the random selection of a restaurant from the submitted list.

6. **Basic Validations:** The frontend code includes basic validations with a simple alert mechanism.

7. **Exception Handling:** Centralized exception handling is implemented on the service side with error codes and messages.

8. **OpenAPI Specification:** The application uses the OpenAPI specification to design APIs, and the code generator plugin is employed on the backend service to create APIs and DTOs. You can find more information in the `pom.xml` file.

Feel free to explore the API documentation and the corresponding README files for further details on using the Restaurant Picker Application.
