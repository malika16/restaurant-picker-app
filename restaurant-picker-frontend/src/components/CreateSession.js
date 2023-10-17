import React, { useState } from 'react';
import Swal from "sweetalert2"

const CreateSession = ({onSave, sessionId}) => {
  const [initiator, setInitiator] = useState('');
 

  const handleSubmit = (e) => {
      e.preventDefault();
      if (!initiator) {
          Swal.fire({
              icon: 'error',
              title: 'Error',
              text: 'Fill in the initiator name or close the form!'
          })
      } else {
        setInitiator(initiator);
        
        onSave({ initiator });
      }
      setInitiator('');
  }
   

  return (
    <div>
      <h4>Create a New Session</h4>
      <form className="add-form" onSubmit={handleSubmit}>
            <div className="form-control">
                <label>Initiator</label>
                <input type="text" placeholder="initiator" value={initiator} onChange={(e) => setInitiator(e.target.value)} disabled={sessionId} />
            </div>
            <input type="submit" className="submit-btn submit-btn-block" value="Create Session" disabled={sessionId}/>
        </form>
    </div>
  );
}

export default CreateSession;
