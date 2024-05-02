import './App.css';
import Header from "./components/Header/Header";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import AccommodationsList from "./components/Accommodation/AccommodationsList";
import CountryList from "./components/Country/CountryList";
import HostList from "./components/Host/HostList";
import Footer from "./components/Footer/Footer";
import AddAccommodation from "./components/Accommodation/AddAccommodation";
import EditAccommodation from "./components/Accommodation/EditAccommodation";
import {useState} from "react";
import CategoriesList from "./components/Categories/CategoriesList";

function App() {
    const [searchText, setSearchText] = useState("");

    return (
        <Router>
            <Header setSearchText={setSearchText}/>
            <main style={{background: "rgb(230,235,250)"}} className="py-5">
                <div className="container m-auto">
                    <Routes>
                        <Route path={"/"} element={<AccommodationsList searchText={searchText}/>}/>
                        <Route path={"/accommodations"} element={<AccommodationsList searchText={searchText}/>}/>
                        <Route path={"/country"} element={<CountryList/>}/>
                        <Route path={"/host"} element={<HostList/>}/>
                        <Route path={"/accommodations/add"} element={<AddAccommodation/>}/>
                        <Route path={"/accommodations/edit/:id"} element={<EditAccommodation/>}/>
                        <Route path={"/categories"} element={<CategoriesList/>}/>
                    </Routes>
                </div>
            </main>
            <Footer/>
        </Router>
    );
}
export default App;
