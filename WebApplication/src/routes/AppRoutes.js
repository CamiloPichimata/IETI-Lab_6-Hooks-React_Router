import React, { useContext } from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import About from '../components/About';
import Home from '../components/Home';
import Login from '../components/Login';
import Tasks from '../components/Tasks';
import { useAuth } from '../utils/auth';
import '../styles/routes.css';
import RequiereAuth from '../utils/RequireAuth';
import { ThemeContext } from '../ThemeContext';

export default function AppRoutes() {
    const auth = useAuth();

    const { state, dispatch} = useContext(ThemeContext);

    return (
        <Router>
            <header className={`header-${state.isDarkMode ? "dark" : "light"}-theme`}>
                <nav>
                    <ul>
                        <li>
                            <Link to="/">Home</Link>
                        </li>
                        <li>
                            <Link to="/tasks">Tasks</Link>
                        </li>
                        <li>
                            <Link to="/about">About</Link>
                        </li>
                        {!auth.userEmail && (
                            <li>
                                <Link to="/login">Login</Link>
                            </li>
                        )}
                        {auth.userEmail && (
                            <div className='navBar-right'>
                                <button className='logout-button' onClick={() => auth.logout()}>Logout</button>
                            </div>
                        )}
                        <div className='navBar-right'>
                            <button className='logout-button' onClick={() => auth.logout()}>Logout</button>
                        </div>
                        <div className='navBar-right'>
                            <button 
                                className={`button-${state.isDarkMode ? "light" : "dark"} theme-button`} 
                                onClick={() => {
                                    if (state.isDarkMode) {
                                        dispatch("SET_LIGHT_MODE");
                                    } else {
                                        dispatch("SET_DARK_MODE");
                                    }
                                }}
                            >
                                {state.isDarkMode ? "Light" : "Dark"} Theme
                            </button>
                        </div>
                    </ul>
                </nav>
            </header>

            <main>
                <div className='container'>
                    <Routes>
                        <Route path="/about" element={<About />} />
                        <Route path="/tasks" element={<RequiereAuth><Tasks /></RequiereAuth>} />
                        <Route path="/login" element={<Login />} />
                        <Route path="/" element={<Home />} />
                    </Routes>
                </div>
            </main>
        </Router>
    );
}
