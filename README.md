# restaurant-picker-app

This application contains two repos, for backend APIs and for frontend.
 Please refer to the respective README files for the detailed scope and setting up the code and running the application.

## API Swagger

 https://github.com/malika16/restaurant-picker-app/blob/main/restaurant-picker-service/src/main/resources/openapi/restaurant-picker-api.yaml 

## Scope:

1. Create a Session: The application allows a user to create a session, becoming the initiator. A session Id will be generated at backend and is used abstractly in the front end code.

2. Join a Session: Initiators can invite others to join the session. 

3. Submit a Restaurant: Once in a session, users can submit their restaurant of choice.

4. View Submitted Restaurants: All users within the session can view the list of submitted restaurants. Backend api is used to fetch all restaurants added against the same session id

5. End a Session: The session initiator can end the session, which triggers the random selection of a restaurant from the submitted list.

6. Basic Validations are added in the front end code with simple alert mechanism

7. Central exception handling is done at the service end with error code and message.
   
9. Open API specification is used to design  APIS and code generator plugin is used at backend service to create API and DTOs, please refer to pom.xml.
 
