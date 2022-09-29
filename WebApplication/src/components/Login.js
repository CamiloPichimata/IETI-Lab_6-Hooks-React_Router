import React, {useState} from 'react';
import {redirect} from 'react-router-dom';
import {Form, Input, Button} from 'antd';
import useLocalStorage, { writeStorage } from '@rehooks/local-storage';
import md5 from 'md5';
import 'antd/dist/antd.min.css';
import '../styles/login.css';


function Login() {
    const [email, setEmail] = useState(null);
    const [password, setPassword] = useState(null);
    
    const onFinish = (values) => {
        console.log('Success:', values);
        setEmail(values.email);
        setPassword(md5(values.password));

        console.log("Email: " + email + " -> " + "Password: " + password);

        //redirect("/home");
        window.location = '/';
    };
     
    const onFinishFailed = (errorInfo) => {
        console.log('Failed:', errorInfo);
        alert("Login failed");
    };

    return (
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
    );
}

export default Login;
