import React from "react";
import {StyledButton} from "./AccommodationsList";
import {useNavigate} from "react-router-dom";
const AccommodationTerm = (props) => {
    const navigate = useNavigate();

    const handleEditClick = () => {
        navigate(`/accommodations/edit/${props.accommodation.id}`, {
            state: { accommodation: props.accommodation }
        });
    };
    return (<li className="list-group-item" key={props.accommodation.id}>
        <div className="media align-items-lg-center d-flex flex-row p-3">
            <img className="m-4" src="/imgs/accomm.png" alt="Accommodation img" width="180"
                 height="180"/>
            <div className="media-body flex-grow-1">
                <blockquote className="blockquote mb-3">
                    <p className="my-3 display-6">{props.accommodation.name}</p>
                    <footer className="blockquote-footer"><cite>{props.accommodation.category}</cite>
                    </footer>
                </blockquote>
                <div className="mb-1 lead">
                    Host: {props.accommodation.host.name} {props.accommodation.host.surname}
                </div>
                <div className="mb-2 lead">
                    Rooms left: {props.accommodation.numRooms}
                </div>
            </div>
            <span className="align-self-baseline">
                {/*<Link to={`/accommodations/edit/${props.accommodation.id}`}>*/}
                    <StyledButton onClick={handleEditClick} className="btn-sm">Edit</StyledButton>
                {/*</Link>*/}
                <button onClick={() => props.onDelete(props.accommodation.id)} className="btn btn-sm m-2 btn-outline-danger text-uppercase">Delete</button>
                <div className="col">
                        <button onClick={() => props.onRent(props.accommodation.id)} className="btn btn-sm btn-block btn-outline-success w-100 text-uppercase">Rent</button>
                </div>
            </span>
        </div>
    </li>)
}

export default AccommodationTerm;