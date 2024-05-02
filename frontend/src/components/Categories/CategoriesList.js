import {useEffect, useState} from "react";
import BookingAppRepo from "../../repository/bookingAppRepo";

const CategoriesList = () => {
    const [categories, setCategories] = useState([]);

    useEffect(() => {
        BookingAppRepo.getCategories()
            .then(data => {
                setCategories(data.data)
            })
    }, []);

    return (<div>
        <p className="display-5">All Categories</p>
        <ul className="list-group shadow">
            {categories.map(category => {
                return <li className="list-group-item" key={category}>
                    <div className="media align-items-lg-center d-flex flex-row p-3">{category}</div>
                </li>
            })}
        </ul>
    </div>)
}

export default CategoriesList;