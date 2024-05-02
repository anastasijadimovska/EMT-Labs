import React, {useState} from "react";
import {Link} from "react-router-dom";

const Header = (props) => {
    const [search, setSearch] = useState('');

    const handleChange = (event) => {
        props.setSearchText(event.target.value);
        setSearch(event.target.value);
    };


    return (
        <header>
            <nav className="navbar navbar-expand-md navbar-dark navbar-fixed p-0" style={{backgroundColor: "#7aabfb"}}>
                <a className="navbar-brand" href="/accommodations">
                    <img src="/imgs/logo.png" width="50" height="50" className="d-inline-block align-top m-2" alt=""/>
                </a>

                <div className="collapse navbar-collapse">
                    <ul className="navbar-nav mr-auto">
                        <li className="nav-item active">
                            <Link className="nav-link" to={"/accommodations"}>Accommodations</Link>
                        </li>
                    </ul>
                    <ul className="navbar-nav mr-auto">
                        <li className="nav-item active">
                            <Link className="nav-link" to={"/categories"}>Categories</Link>
                        </li>
                    </ul>
                    <ul className="navbar-nav mr-auto">
                        <li className="nav-item active">
                            <Link className="nav-link" to={"/country"}>Countries</Link>
                        </li>
                    </ul>
                    <ul className="navbar-nav mr-auto">
                        <li className="nav-item active">
                            <Link className="nav-link" to={"/host"}>Hosts</Link>
                        </li>
                    </ul>

                </div>
                <form className="form-inline d-flex m-3">
                    <input value={search} onChange={handleChange} className="form-control mr-sm-2" type="search"
                           placeholder="Search" aria-label="Search"/>
                </form>
            </nav>


        </header>
    )
}
export default Header;