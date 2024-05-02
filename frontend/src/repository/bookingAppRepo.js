import axios from "../custom-axios/axios";


const BookingAppService = {
    getAccommodations: () => {
        return axios.get("/accommodations");
    },
    getHosts: () => {
        return axios.get("/host");
    },
    getCountries: () => {
        return axios.get("/country");
    },
    getAccommodation: (id) => {
        return axios.get(`/accommodations/${id}`);
    },
    getHost: (id) => {
        return axios.get(`/host/${id}`);
    },
    getCountry: (id) => {
        return axios.get(`/country/${id}`);
    },
    addAccommodation: (accommodation) => {
        return axios.post("/accommodations/add",accommodation);
    },
    addHost: (host) => {
        return axios.post("/host/add", host);
    },
    addCountry: (country) => {
        return axios.post("/country/add", country);
    },
    editAccommodation: (id,accommodation) => {
        return axios.post(`/accommodations/edit/${id}`,accommodation);
    },
    editHost: (id, host) => {
        return axios.post(`/host/${id}`,host);
    },
    editCountry: (id, country) => {
        return axios.post(`/country/edit/${id}`, country);
    },
    deleteAccommodation: (id) => {
        return axios.post(`/accommodations/delete/${id}`);
    },
    deleteHost: (id) => {
        return axios.post(`/host/delete/${id}`);
    },
    deleteCountry: (id) => {
        return axios.post(`/country/delete/${id}`);
    },
    getCategories: () => {
        return axios.get("/accommodations/categories");
    },
    rent: (id) => {
        return axios.post(`/accommodations/rent/${id}`);
    }
}
export default BookingAppService;