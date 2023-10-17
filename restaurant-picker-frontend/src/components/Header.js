import React from 'react';
import Button from './Button';
import "../index.css";
const Header = ({ showForm, changeTextAndColor,sessionId }) => {

    
  return (
      <header className="header">
          <h4 className="app-header">Add Restaurant</h4>
          <Button   onClick={showForm} color={changeTextAndColor ? 'red' : '#05214ad9'} 
           text={<span style={{ color: 'white' }}>{changeTextAndColor ? 'Close' : 'Add'}</span>}/>
        </header>
    )
}
export default Header;