import { useState } from 'react';
import Swal from "sweetalert2"

const AddRestaurant = ({onSave}) => {
    const [restaurant, setRestaurant] = useState('');

    const onSubmit = (e) => {
        e.preventDefault();
        if (!restaurant) {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Fill in your restaurant or close the form!'
            })
        } else {
            onSave({ restaurant });
        }
        setRestaurant('');
    }

    return (
        <form className="add-form" onSubmit={onSubmit}>
            <div className="form-control">
                <label>Restaurant</label>
                <input type="text" placeholder="add restaurant" value={restaurant} onChange={(e) => setRestaurant(e.target.value)} />
            </div>
            <input type="submit" className="submit-btn submit-btn-block" value="Save Restaurant" />
        </form>
      )
  }
  export default AddRestaurant