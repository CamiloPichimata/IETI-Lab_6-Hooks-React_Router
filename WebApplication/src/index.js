import React from 'react';
import ReactDOM from 'react-dom/client';
import Routes from './routes/Routes'
import { AuthProvider } from './utils/auth';
import './styles/index.css';

class Index extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            userName: null,
            isLogged: false,
        }
    }
    
    render() {
        return (
            <AuthProvider>
                <Routes />
            </AuthProvider>
        );
    }
}

// ==================================================================

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<Index />);
