import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import { AuthProvider } from './utils/auth';
import './styles/index.css';
import * as serviceWorker from './serviceWorkerRegistration';

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
                <App />
            </AuthProvider>
        );
    }
}

// ==================================================================

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<Index />);

serviceWorker.register();