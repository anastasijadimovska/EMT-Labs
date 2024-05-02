import React, {useEffect, useState} from "react";
import BookingAppRepo from "../../repository/bookingAppRepo";
import {useLocation, useNavigate, useParams} from 'react-router-dom';


const EditAccommodation = (props) => {
    const{ id} = useParams();
    const location = useLocation();
    const accommodation = location.state.accommodation;
    const navigate = useNavigate();

    const [hosts, setHosts] = useState([]);
    const [categories, setCategories] = useState([]);
    const [formData, setFormData] = useState({
        name: accommodation.name,
        category: accommodation.category,
        host: accommodation.host.id,
        numRooms: accommodation.numRooms
    })


    useEffect(() => {
        BookingAppRepo.getHosts()
            .then(data => {
                setHosts(data.data);
            });

        BookingAppRepo.getCategories()
            .then(data => {
                setCategories(data.data);
            });
    }, []);

    const handleChange = (event) => {
        if (Object.keys(formData).includes(event.target.name)) {
            if(formData[event.target.name] !== event.target.value && event.target.value !== ""){
                setFormData({
                    ...formData,
                    [event.target.name]: event.target.value
                })
            }
        }
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        BookingAppRepo.editAccommodation(id,formData);

        navigate("/accommodations");
    }

    return (
        <form onSubmit={handleSubmit}>
            <div className="form-group">
                <label htmlFor="name">Accommodation's name</label>
                <input onChange={handleChange}  type="text" name="name" className="form-control" id="name"
                       placeholder={accommodation.name}/>
            </div>

            <div className="form-group">
                <label htmlFor="category">Category</label>
                <select className="form-control" name="category" onChange={handleChange} >
                    {categories.map(category => {
                        if(accommodation.category !== undefined &&
                            accommodation.category === category)
                            return <option selected={accommodation.category} value={category}>{category}</option>
                       else return <option value={category}>{category}</option>
                    })}
                </select>
            </div>
            <div className="form-group">
                <label htmlFor="host">Host</label>
                <select className="form-control" name="host" onChange={handleChange} >
                    {hosts.map(host => {
                        if(accommodation.host !== undefined &&
                            accommodation.host.id === host.id)
                            return <option selected={accommodation.host.id} value={host.id}>{host.name}</option>
                        else return <option value={host.id}>{host.name}</option>
                    })}
                </select>
            </div>

            <div className="form-group">
                <label htmlFor="numRooms">Number of rooms</label>
                <input onChange={handleChange} type="number" name="numRooms" className="form-control" id="numRooms"
                       placeholder={accommodation.numRooms}/>
            </div>
            <hr/>
            <button type="submit" className="btn btn-primary">Submit</button>
        </form>
    )
}

export default EditAccommodation;