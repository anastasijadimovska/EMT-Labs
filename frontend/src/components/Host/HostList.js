import {useEffect, useState} from "react";
import BookingAppRepo from "../../repository/bookingAppRepo";

const HostList = () => {
    const [hosts, setHosts] = useState([]);

    useEffect(()=>{
        BookingAppRepo.getHosts()
            .then(data => {
                setHosts(data.data);
            })
    },[]);

    return (<div>
        <p className="display-5">All Hosts</p>
        <ul className="list-group">
            {hosts.map(host => {
                return <li className="list-group-item" key={host.id}>
                    <div className="media align-items-lg-center d-flex flex-row p-3">{host.name}</div>
                </li>
            })}
        </ul>
    </div>)
}

export default HostList;