import {useEffect, useState} from "react";
import BookingAppRepo from "../../repository/bookingAppRepo";

const CountryList = () => {
    const [countries, setCountries] = useState([]);

    useEffect(() => {
        BookingAppRepo.getCountries()
            .then(data => {
                setCountries(data.data);
            });
    }, []);

    return (<div>
        <p className="display-5">All Countries</p>
        <ul className="list-group shadow">
            {countries.map(country => {
                return <li className="list-group-item" key={country.id}>
                    <div className="media align-items-lg-center d-flex flex-row p-3">{country.name}</div>
                </li>
            })}
        </ul>
    </div>)
}

export default CountryList;