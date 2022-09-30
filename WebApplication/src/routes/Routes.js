import React from 'react';
import { BrowserRouter as Router, Routes as Routes_, Route, Link } from "react-router-dom";
import About from '../components/About';
import Home from '../components/Home';
import Login from '../components/Login';
import Tasks from '../components/Tasks';
import '../styles/routes.css';

export default function Routes() {
    return (
        <Router>
            <header>
                <nav>
                    <ul>
                        <li>
                            <Link to="/">Home</Link>
                        </li>
                        <li>
                            <Link to="/about">About</Link>
                        </li>
                        <li>
                            <Link to="/tasks">Tasks</Link>
                        </li>
                        <li>
                            <Link to="/login">Login</Link>
                        </li>
                    </ul>
                </nav>
            </header>

            <main>
                <div className='container'>
                    <Routes_>
                        <Route path="/about" element={<About />} />
                        <Route path="/tasks" element={<Tasks />} />
                        <Route path="/login" element={<Login />} />
                        <Route path="/" element={<Home />} />
                    </Routes_>
                </div>
            </main>
        </Router>
    );
}
