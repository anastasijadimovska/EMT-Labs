import React, {useEffect, useState} from "react";
import BookingAppRepo from "../../repository/bookingAppRepo";
import {useNavigate} from 'react-router-dom';


const AddAccommodation = () => {
    const navigate = useNavigate();
    const [hosts, setHosts] = useState([]);
    const [categories, setCategories] = useState([]);
    const [formData, setFormData] = useState({
        name: "",
        category: "ROOM",
        host: 1,
        numRooms: 0
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
        setFormData({
            ...formData,
            [event.target.name]: event.target.value
        })
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log(formData.category)

        BookingAppRepo.addAccommodation(formData);

        navigate("/accommodations");
    }

    return (
        <form onSubmit={handleSubmit}>
            <div className="form-group">
                <label htmlFor="name">Accommodation's name</label>
                <input onChange={handleChange}  type="text" name="name" className="form-control" id="name"
                       placeholder="Enter accommodation's name"/>
            </div>

            <div className="form-group">
                <label htmlFor="category">Category</label>
                <select className="form-control" name="category" onChange={handleChange} >
                    {categories.map(category => {
                        return <option value={category}>{category}</option>
                    })}
                </select>
            </div>
            <div className="form-group">
                <label htmlFor="host">Host</label>
                <select className="form-control" name="host" onChange={handleChange} >
                    {hosts.map(host => {
                        return <option value={host.id}>{host.name} {host.surname}</option>
                    })}
                </select>
            </div>
            <div className="form-group">
                <label htmlFor="numRooms">Number of rooms</label>
                <input onChange={handleChange} type="number" name="numRooms" className="form-control" id="numRooms"
                       placeholder="Enter number of rooms"/>
            </div>
            <hr/>
            <button type="submit" className="btn btn-primary">Submit</button>
        </form>
    )
}

export default AddAccommodation;