import { useState, createContext, useContext } from "react";
import axios from 'axios';

const AuthContext = createContext(null);

export const AuthProvider = ({children}) => {
    const [userEmail, setUserEmail] = useState(null);
    const [token, setToken] = useState(null);

    const login = (email, password, callback) => {
        axios.post('http://localhost:8081/api/v2/auth', {
            email: email, 
            password: password
        })
            .then(function (response) {
                setUserEmail(email);
                console.log(response.data);
                setToken(response.data.token);
            })
            .then(function () {
                console.log("userEmail: " + userEmail + " -> token: " + token);
                console.info("User Login");
                callback()})
            .catch(function (error) {
                console.log("Error:", error);
            });
    }

    const logout = () => {
        console.info("User Logout")
        setUserEmail(null);
        setToken(null);
    }

    return (
        <AuthContext.Provider value={{userEmail, token, login, logout}}>
            {children}
        </AuthContext.Provider>
    );
}

export const useAuth = () => {
    return useContext(AuthContext);
}