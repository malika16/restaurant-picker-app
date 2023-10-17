import React, { useState } from 'react';
import Swal from "sweetalert2"

const InviteUsers = ({onSave}) => {
  const [users, setUsers] = useState('');
 

  const handleSubmit = (e) => {
      e.preventDefault();
      if (!users) {
          Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: 'Fill in the users to invite or close the form!'
          })
      } else {
          onSave({ users });
      }
      setUsers('');
  }
   

  return (
    <div>
      <h4>Invite Users</h4>
      <form className="add-form" onSubmit={handleSubmit}>
            <div className="form-control">
                <label>Invite Users</label>
                <input type="text" placeholder="add comma separated users" value={users} onChange={(e) => setUsers(e.target.value)} />
            </div>
            <input type="submit" className="submit-btn submit-btn-block" value="Invite Users" />
        </form>
    </div>
  );
}

export default InviteUsers;
