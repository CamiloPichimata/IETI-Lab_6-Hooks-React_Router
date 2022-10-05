import { useState, createContext, useContext } from "react";
import axios from 'axios';

const AuthContext = createContext(null);

export const AuthProvider = ({children}) => {
    const [userEmail, setUserEmail] = useState(null);
    const [token, setToken] = useState(null);
    const [tokenExpirationDate, setTokenExpirationDate] = useState(null)

    const login = (email, password, callback) => {
        axios.post('http://localhost:8081/api/v2/auth', {
            email: email, 
            password: password
        })
            .then(function (response) {
                console.log(response.data);
                setUserEmail(email);
                setToken(response.data.token);
                setTokenExpirationDate(response.data.expirationDate);
            })
            .then(function () {
                //console.log("userEmail: " + userEmail + " -> token: " + token + " -> expirationDate: " + tokenExpirationDate);
                console.info("User Login");
                callback()})
            .catch(function (error) {
                console.log("Error:", error);
                alert("Login failed...\n Please try again");
            });
    }

    // async function login (email, password, callback) {
    //     const response = await axios.post('http://localhost:8081/api/v2/auth', {
    //         email: email, 
    //         password: password
    //     });

    //     setUserEmail(email);
    //     setToken(response.data.token);
    //     setTokenExpirationDate(response.data.expirationDate);

    //     console.log("response.data:", response.data);
    //     console.log("userEmail: " + userEmail + " -> token: " + token + " -> expirationDate: " + tokenExpirationDate);
    //     console.info("User Login");

    //     callback();
    // }

    const logout = () => {
        console.info("User Logout");
        setUserEmail(null);
        setToken(null);
        setTokenExpirationDate(null);
    }

    return (
        <AuthContext.Provider value={{userEmail, token, tokenExpirationDate, login, logout}}>
            {children}
        </AuthContext.Provider>
    );
}

export const useAuth = () => {
    return useContext(AuthContext);
}