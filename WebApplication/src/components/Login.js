import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';
import {Form, Input, Button} from 'antd';
//import useLocalStorage, { writeStorage } from '@rehooks/local-storage';
import 'antd/dist/antd.min.css';
import { useAuth } from '../utils/auth';
import '../styles/login.css';

function Login() {
    const [userEmail, setUserEmail] = useState(null);
    const auth = useAuth();
    const navigate = useNavigate();
    
    const redirect = () => {
        if (auth.userEmail != null) {
            navigate("/");
        } 
        // else {
        //     alert("Login failed...\n Please check the specified credentials")
        // }
    }

    const onFinish = (values) => {
        console.log('Login Values:', values);
        auth.login(values.email, values.password, redirect);
        setUserEmail(values.email);
        console.log("Login -> userEmail:", userEmail);
    };
     
    const onFinishFailed = (errorInfo) => {
        console.log('Failed:', errorInfo);
        alert("Login failed:\nPlease fill in all the fields...");
    };

    return (
        <div className='all-centered'>
            <div className="login-container">
                <h3>Login</h3>
                <div className='form-container'>
                    <Form 
                        name='LoginForm' 
                        initialValues={{
                            remember: true,
                        }}
                        onFinish={onFinish}
                        onFinishFailed={onFinishFailed}
                        autoComplete="off"
                    >
                        <Form.Item 
                            label="Email" 
                            name="email" 
                            rules={[{
                                required: true,
                                message: "Please enter your email",
                            }]}
                            >
                            <Input />
                        </Form.Item>
                        
                        <Form.Item
                            label="Password" 
                            name="password" 
                            rules={[{
                                required: true,
                                message: "Please enter your password",
                            }]}
                        >
                            <Input.Password />
                        </Form.Item>

                        <Form.Item>
                            <Button className='login-button' type='primary' htmlType='submit'>
                                Login
                            </Button>
                        </Form.Item>
                    </Form>
                </div>
            </div>
        </div>
    );
}

export default Login;
