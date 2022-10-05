import { Navigate } from "react-router-dom";
import { useAuth } from "./auth";

export default function RequiereAuth({ children }) {
    const auth = useAuth();
    const oneHourInMilis = 60*60*1000;
    const tokenExpirationDate = new Date(auth.tokenExpirationDate);

    if (!auth.token) {
        return <Navigate to='/login' />
    }

    if (((tokenExpirationDate.getTime() + oneHourInMilis) - new Date().getTime()) < 0) {
        auth.logout();
    }
        
    return children;
}