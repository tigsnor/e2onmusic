import axios from "axios";

// axios.defaults.headers.common['Content-Type'] = 'application/x-www-form-urlencoded'
// axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
export default axios.create({
    baseURL:"http://localhost:9090",
    withCredentials: true
});