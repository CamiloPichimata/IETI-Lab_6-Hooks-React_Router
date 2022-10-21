import { useReducer } from "react";
import AppRoutes from "./routes/AppRoutes";
import { ThemeContext } from "./ThemeContext";
import { initialState, themeReducer } from "./utils";

function App() {

    const [state, dispatch] = useReducer(themeReducer, initialState);

    return (
        <ThemeContext.Provider value={{ state, dispatch }}>
            <AppRoutes />
        </ThemeContext.Provider>
    );
}

export default App;
