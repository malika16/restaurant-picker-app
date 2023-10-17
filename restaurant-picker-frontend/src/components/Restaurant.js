import "../index.css"
const Restaurant = ({ restaurant }) => {
    return (
      <div>
        <div className="task">
          <div>
            <p className="taskName">
              <span className="textBold">Restaurant:</span> {restaurant.restaurant}
            </p>
            </div>
            <div>
          </div>
        </div>
      </div>
    )
}
export default Restaurant;