import React, { useState, useEffect } from 'react';
import Swal from 'sweetalert2';
import RestaurantService from '../service/RestaurantService';
import SessionService from '../service/SessionService';
import CreateSession from './CreateSession';
import InviteUsers from './InviteUsers';
import PickRestaurant from './PickRestaurant';
import Header from './Header';
import Restaurants from './Restaurants';
import AddRestaurant from './AddRestaurant';

function RestaurantPickerContainer() {
  const [restaurants, setRestaurants] = useState([]);
  const [showAddRestaurant, setShowAddRestaurant] = useState(false);
  const [initiator, setInitiator] = useState('');
  const [sessionId, setSessionId] = useState('');
  const [users, setUsers] = useState('');

  const service = new RestaurantService();
  const sessionService = new SessionService();

  const handleError = (errorText) => {
    Swal.fire({
      icon: 'error',
      title: 'Error...',
      text: errorText,
    });
  };

  const handleSuccess = (message) => {
    Swal.fire({
      icon: 'success',
      title: 'Yay...',
      text: message,
    });
  };

  useEffect(() => {
    async function fetchData() {
      if (sessionId) {
        try {
          const restaurantData = await service.getAllRestaurants(sessionId);
          if (restaurantData) {
            setRestaurants([...restaurantData]);
          }
        } catch (error) {
          handleError('An error occurred while fetching restaurants.');
        }
      }
    }
    fetchData();
  }, [sessionId]);

  const createSession = async (initiator) => {
    try {
      const response = await sessionService.createSession(initiator);
      if (response) {
        setInitiator(initiator);
        setSessionId(response.sessionId);
        handleSuccess('Session created for: ' + initiator.initiator);
      } else {
        handleError('Something went wrong, please try again!');
      }
    } catch (error) {
      console.error(error);
      handleError('An error occurred while creating the session!');
    }
  };

  const addNewRestaurant = async (sessionId, restaurantData) => {
    try {
      const response = await service.addRestaurant(restaurantData, sessionId);
      if (response) {
        setRestaurants([...restaurants, restaurantData]);
        handleSuccess('You have successfully added a new restaurant!');
      } else {
        handleError('Something went wrong, please try again!');
      }
    } catch (error) {
      console.error(error);
      handleError('An error occurred while adding the restaurant!');
    }
  };

  const inviteUsers = async (users, sessionId) => {
    try {
      const response = await sessionService.inviteUsers(users, sessionId);
      if (response) {
        setUsers(users);
        handleSuccess('You have successfully invited the users!');
      } else {
        handleError('Something went wrong, please try again!');
      }
    } catch (error) {
      console.error(error);
      handleError('An error occurred while inviting the users!');
    }
  };

  const pickRandomRestaurant = async (sessionId, initiator) => {
    try {
      const response = await service.pickRestaurant(initiator, sessionId);
      if (response) {
        handleSuccess('Random restaurant picked for lunch: ' + response.pickedRestaurant);
      } else {
        handleError('Something went wrong, please try again!');
      }
    } catch (error) {
      console.error(error);
      handleError('An error occurred while picking the restaurant!');
    }
  };

  return (
    <div className="container">
      <CreateSession onSave={createSession} sessionId={sessionId} />
      {sessionId && <InviteUsers onSave={(users) => inviteUsers(users,sessionId)} sessionId={sessionId} />}
      {sessionId && (
        <Header
          showForm={() => setShowAddRestaurant(!showAddRestaurant)}
          changeTextAndColor={showAddRestaurant}
          sessionId={sessionId}
        />
      )}
      {showAddRestaurant && <AddRestaurant sessionId={sessionId}   onSave={(restaurantData) => addNewRestaurant(sessionId, restaurantData)} />}
      {restaurants.length > 0 ? (
        <Restaurants restaurants={restaurants} sessionId={sessionId} />
      ) : null}
      {sessionId && restaurants && <PickRestaurant onSave={() => pickRandomRestaurant(sessionId, initiator)} />}
    </div>
  );
}

export default RestaurantPickerContainer;
