import Restaurant from './Restaurant';
import "../index.css"
const Restaurants = ({ restaurants }) => {
  return (
    <>
      {
        restaurants.map((aa) => (
          <Restaurant restaurant={aa}/>
        ))
      }
    </>
    )
}
export default Restaurants;