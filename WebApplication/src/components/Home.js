import '../styles/home.css';
import { Link } from 'react-router-dom';

export default function Home() {

    return (
        <div className='home-container'>
            <h2 className='h2-title'>TaskApp</h2>
            <div className='home-text-centered'>
                <p className='large-text'>Manage and control your tasks easily and quickly</p>
                <p><Link className='little-text' to="/tasks">Let us help you!</Link></p>
            </div>
        </div>
    );

}
