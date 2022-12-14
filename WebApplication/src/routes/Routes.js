import React, { useEffect } from 'react';
import { BrowserRouter as Router, Routes as Routes_, Route, Link, useNavigate } from "react-router-dom";
import About from '../components/About';
import Home from '../components/Home';
import Login from '../components/Login';
import Tasks from '../components/Tasks';
import { useAuth } from '../utils/auth';
import '../styles/routes.css';
import { Button } from 'antd';
import RequiereAuth from '../utils/RequireAuth';

export default function Routes() {
    const auth = useAuth();

    return (
        <Router>
            <header>
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
                            <div className='logout'>
                                <button className='logout-button' onClick={() => auth.logout()}>Logout</button>
                            </div>
                        )}
                    </ul>
                </nav>
            </header>

            <main>
                <div className='container'>
                    <Routes_>
                        <Route path="/about" element={<About />} />
                        <Route path="/tasks" element={<RequiereAuth><Tasks /></RequiereAuth>} />
                        <Route path="/login" element={<Login />} />
                        <Route path="/" element={<Home />} />
                    </Routes_>
                </div>
            </main>
        </Router>
    );
}
