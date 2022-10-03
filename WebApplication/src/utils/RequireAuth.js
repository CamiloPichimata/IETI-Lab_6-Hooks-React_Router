import { Navigate } from "react-router-dom";
import { useAuth } from "./auth";

export default function RequiereAuth({ children }) {
    const auth = useAuth();

    if (!auth.userEmail) {
        return <Navigate to='/login' />
    }

    return children;
}