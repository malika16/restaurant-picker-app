
  class SessionService {
    static host = "http://localhost:8080";
    static apiPrefix = "/sessions";
  
    createSession(initiator) {
      console.log("Sending add session request", initiator);
  
      const requestOptions = {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(initiator),
      };
  
      return fetch(SessionService.host +
        SessionService.apiPrefix, requestOptions)
        .then(async (response) => {
          if (!response.ok) {
            return undefined;
          }
          if (response.ok) {
            const data = await response.json();
            return data;
          } else {
            throw new Error("API request failed");
          }
        })
        .catch((error) => {
          console.error(error);
          return false;
        });
    }
  
    inviteUsers(users, sessionId) {
      console.log("Sending invite users request", users);
  
      const requestOptions = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
      };
  
      return fetch(
        this.getSessionURI(sessionId)+
          '/invite?userIds=' +
          users.users,
        requestOptions
      )
        .then((response) => {
          if (response.ok) {
            return true; // API request was successful
          } else {
            throw new Error("API request failed");
          }
        })
        .catch((error) => {
          console.error(error);
          return false;
        });
    }
  
    getSessionURI(sessionId) {
      return (
        SessionService.host +
        SessionService.apiPrefix +
        '/' +
        sessionId
      );
    }
  }
  
  export default SessionService;
  