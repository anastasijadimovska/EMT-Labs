import { useEffect, useState} from "react";
import BookingAppRepo from "../../repository/bookingAppRepo";
import styled from "styled-components";
import AccommodationTerm from "./AccommodationTerm";
import ReactPaginate from "react-paginate";
import {Link} from "react-router-dom";

export const StyledButton = styled.button.attrs({
    className: "btn my-sm-0 text-uppercase"
})` 
  background: transparent;
  border-color: #7aabfb;
  color: #7aabfb;
  &:hover{
    background: #7aabfb;
    color: #E6EBFAFF;
    border-color: #7aabfb;
  }
`
const AccommodationsList = ({searchText}) => {
    const [accommodations, setAccommodations] = useState([]);
    const [page, setPage] = useState(0);
    const [filterData, setFilterData] = useState([]);
    const n = 5

    useEffect(() => {
            loadAccommodations();
    }, [page, searchText]);

    const loadAccommodations = () => {
        BookingAppRepo.getAccommodations()
            .then(data => {
                const allAccommodations = data.data.filter(a => a.numRooms > 0);
                const filteredData = allAccommodations.filter(a => a.name.toLowerCase().includes(searchText.toLowerCase())).reverse();
                if(searchText!==""){
                    setPage(0);
                }
                const pageData = filteredData.filter((item, index) => {
                    return (index >= page * n) & (index < (page + 1) * n);
                });
                setAccommodations(filteredData);
                setFilterData(pageData);
            });
    }

    const onDelete = (id) => {
        BookingAppRepo.deleteAccommodation(id)
            .then(() => {
                loadAccommodations()
            });
    }

    const onRent = (id) => {
        BookingAppRepo.rent(id)
            .then(data => {
                loadAccommodations();
            })
    }

    const handlePageClick = (data) => {
        setPage(data.selected)
    }

    const pageAccommodation = filterData.map((accommodation, index) => {
            return (<AccommodationTerm accommodation={accommodation} onDelete={onDelete} onRent={onRent}/>)
        })


    return (<div className="row">
            <div className="col-lg-8 mx-auto">
                <div className="d-flex justify-content-between align-items-center">
                    <p className="display-6">Available Accommodations</p>
                    <Link to={"/accommodations/add"}>
                        <StyledButton>Add new accommodation</StyledButton>
                    </Link>
                </div>
                <ul className="list-group shadow">
                    {pageAccommodation}
                </ul>
            </div>
        <ReactPaginate
            containerClassName={"pagination justify-content-center"}
            pageClassName={"page-link"}
            activeClassName={"page-item active"}
            onPageChange={handlePageClick}
            pageCount={Math.ceil(accommodations.length / n)}
            breakLabel="..."
            previousClassName={"page-link"}
            nextClassName={"page-link"}
            previousLabel={"Previous"}
            nextLabel={"Next"}
        />
        </div>)

}



export default AccommodationsList;