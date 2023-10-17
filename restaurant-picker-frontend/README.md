# Restaurant Picker App

#  UI Scope

1. App will be accessible in your web browser at http://localhost:3000. The Landing page will display component to create the session.
There will be alert generated if no initiator name is added.
Once session is created, the text box and the create session button will be disabled.

2. Once the session is created, the initiator can invite users to submit their choices against the session. Comma seperated user names can be passed e.g user1,user2

3. The invited users can submit their preferences for the particular session via Add Restaurant component. This component is agnostic to session initiator and the other session users. The users can submit their choices multiple times. There is Add/Close button to the right to show/close this component.

4. All the users can view the restaurants added. The list is fetched from backend.

5. The initiator can pick the random restaurant. This will mark the session as Closed in backend DB.


## Getting Started

To run the Restaurant Picker App locally, follow these steps:

### Prerequisites
Node.js installed on your machine.

### Installation

1. Clone this repository to your local machine
2. Navigate to the project directory:

    ### cd restaurant-picker-frontend

3. Install the required dependencies: 
    
    ### npm install

4. Once you have the application installed, you can start the development server with the following command:

    ### npm start

    This will start the development server, and the Restaurant Picker App will be accessible in your web browser at http://localhost:3000.


  
