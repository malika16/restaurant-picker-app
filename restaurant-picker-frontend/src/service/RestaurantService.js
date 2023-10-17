export const CreateRestaurantRequest = {
    restaurant_name: ""
  };
  
  export const Restaurant = {
    name: "",
  };
  
class RestaurantService {
    static host = "http://localhost:8080";
    static apiPrefix = "/sessions";
    static resource = "/restaurants";

    
    addRestaurant(restaurant, sessionId) {
      console.log("Sending add restaurant request", restaurant);

      const targetObject = {
        "restaurant_name": restaurant.restaurant
      };
    
      const requestOptions = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(targetObject),
      };
  
      return fetch(this.getRestaurantURI(sessionId), requestOptions)
  .then((response) => {
    if (response.ok) {
      return true; // API request was successful
    } else {
      throw new Error("API request failed"); // Throw an error for non-successful responses
    }
  })
  .catch((error) => {
    console.error(error);
    return false; // Handle the error and return a value or status indicating failure
  });
    }
  
    async getAllRestaurants() {
      console.log("Fetching all restaurants");
      const requestOptions = {
        method: "GET",
        headers: { "Content-Type": "application/json" },
      };
  
      return fetch(this.getRestaurantURI(), requestOptions)
        .then((value) => {
          if (!value.ok) {
            return undefined;
          }
  
          return value.json().then((result) => {
            console.log(result);
            return result.restaurant.map((restaurant) => ({
              restaurant: restaurant
            }));
          });
        })
        .catch((e) => {
          console.error(e);
          return undefined;
        });
    }
  
  
    pickRestaurant(initiator, sessionId) {
      console.log("Picking rest for session", sessionId); 

    // Send a POST request to your API to create the session
     return fetch(RestaurantService.host +RestaurantService.apiPrefix + '/'+sessionId+'/pickRestaurant', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(initiator),
      }).then(async (response) => {
        if (!response.ok) {
          return undefined;
        }
        if (response.ok) {
          // Session created successfully
          const data =  await response.json();
          return data;
        } else {
          throw new Error("API request failed"); 
        }
      }).catch((error) => {
        console.error(error);
        return false;
      });
    }
  
    
  
    getRestaurantURI(sessionId) {
      return (
        RestaurantService.host +
        RestaurantService.apiPrefix + '/' + sessionId +
        RestaurantService.resource
      );
    }
  }
  
  export default RestaurantService;
  