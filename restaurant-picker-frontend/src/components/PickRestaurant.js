import React, { useState } from 'react';
import Swal from "sweetalert2"

const PickRestaurant = ({onSave}) => {
 

  const handlePickRestaurant = () => {
    onSave();
  }
   

    return (
        <div>
            <header className="header">
          <h4 className="app-header">Pick Random Restaurant</h4>
          <button class='submit-btn' color='green' onClick={handlePickRestaurant}>Pick a Random Restaurant</button>
          </header>
        </div>
      );
}

export default PickRestaurant;
