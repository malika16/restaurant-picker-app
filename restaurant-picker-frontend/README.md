# Restaurant Picker FrontEnd

## UI Scope

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

## Getting Started

To run the Restaurant Picker App locally, follow these steps:

### Prerequisites
- Node.js installed on your machine.

### Installation

1. Clone this repository to your local machine.

2. Navigate to the project directory:

    ```
    cd restaurant-picker-frontend
    ```

3. Install the required dependencies:

    ```
    npm install
    ```

4. Once you have the application installed, start the development server with the following command:

    ```
    npm start
    ```

    This will start the development server, and the Restaurant Picker App will be accessible in your web browser at http://localhost:3000.
