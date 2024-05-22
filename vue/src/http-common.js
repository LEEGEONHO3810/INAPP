import axios from "axios"

export default axios.create({
    baseURL: "http://localhost:8080",
    // baseURL: "http://220.78.157.183:10021",
    // baseURL: "http://220.78.157.183:10021",
    header:{
        "Content-type":"application/json",
    }
});