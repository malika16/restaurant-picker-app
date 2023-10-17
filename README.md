# Restaurant Picker Application

This application consists of two repositories: one for the backend APIs and another for the frontend. Please refer to the respective README files for detailed instructions on setting up and running the application.

## API Documentation

Explore the API using the Swagger documentation.Copy below yaml to https://editor.swagger.io/ to preview! :

- [Restaurant Picker API Swagger](https://github.com/malika16/restaurant-picker-app/blob/main/restaurant-picker-service/src/main/resources/openapi/restaurant-picker-api.yaml)

## Scope

The Restaurant Picker Application offers the following features.Screenshots attached:

## Functional Scope

### 1. Landing Page
- Accessible at http://localhost:3000
- The landing page displays a component for creating a session.
- An alert is generated if no initiator name is added.
- After creating a session, the text box and "Create Session" button will be disabled.

### 2. Inviting Users
- The initiator can invite users to submit their choices against the session.
- Comma-separated user names can be passed (e.g., user1, user2).

### 3. Submitting Preferences
- Invited users can submit their preferences for the session via the "Add Restaurant" component.
- This component is agnostic to the session initiator and other session users.
- Users can submit their choices multiple times.
- An "Add/Close" button is available to show/close this component.

### 4. Viewing Restaurants
- All users can view the restaurants added.
- The list is fetched from the backend.

### 5. Picking a Random Restaurant
- The initiator can pick a random restaurant.
- This action marks the session as closed in the backend database.

## Technical Scope

### 6. OpenAPI Specification
   - The application uses the OpenAPI specification to design APIs, and the code generator plugin is employed on the backend service to create APIs   
      and DTOs. You can find more information in the `pom.xml` file.

### 7. Basic Validations
 - The frontend code includes basic validations with a simple alert mechanism. The backend service also has a validator for checking some common 
   scenarios.

### 8. Exception Handling
  - Centralized exception handling is implemented on the service side with error codes and messages.

### 9: Test Cases
  - Integration and Unit test cases are covered for backend service  

### 10: Build and Run
 - Required steps to setup and run have been added in the respective README files [FrontEnd-README](https://github.com/malika16/restaurant-picker-app/blob/main/restaurant-picker-frontend/README.md) and [Service-README](https://github.com/malika16/restaurant-picker-app/blob/main/restaurant-picker-service/README.md)

## Screenshots

![CreateSession](https://github.com/malika16/restaurant-picker-app/assets/23330663/8c1cf953-9a2a-4306-812a-5b2d71deedde)
![InviteUsers](https://github.com/malika16/restaurant-picker-app/assets/23330663/6a9f5f56-f29b-41b3-8c42-ef0f068ef5c7)
![AddRest](https://github.com/malika16/restaurant-picker-app/assets/23330663/e81cfbb4-1380-4d6d-bb8d-a092740b355e)
![ChooseRandom](https://github.com/malika16/restaurant-picker-app/assets/23330663/39fa8f15-88ba-43e6-b34f-4b25b1796d22)







