import React, { useState } from 'react';
import { Navigate, useNavigate } from 'react-router-dom';
import axios from 'axios';
import styled, { keyframes } from 'styled-components';
import { User } from '../../../Types/User';


const fadeIn = keyframes`
    0% {opacity: 0%},
    100% {opacity: 100%}
`
const Container = styled.div`
    background: white;
    padding: 40px;
    animation: ${fadeIn} 1s;
`
const Form = styled.form`
    display: flex;
    flex-direction: column;
`
const InputWrapper = styled.div`
    width: 100%;
    text-align: center;
`
const FinalWrapper = styled.div`
    width: 100%;
    margin: 5px;
    text-align: center;
`
const Label = styled.label`
    font-weight: bold;
    margin: 10px;
    text-align: left;
`
const Input = styled.input`
    font-size: 20px;
    width: 95%;
    padding: 5px;
    padding-inline: 8px;
    margin-bottom: 15px;
    color: #222;
    outline: 1px solid #ccc;
    border: none;
`
const LoginButton = styled.button`
    border: none;
    background: #047d40;
    padding: 15px;
    font-size: 20px;
    color: white;
    cursor: pointer;
    &:hover {
        box-shadow: inset 0 0 10px 10px rgba(0,0,0,0.3);
    }
`


export const LoginForm: React.FC = () => {
    const [email, setEmail] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const [error, setError] = useState<boolean>(false);
    const [logged, setLogged] = useState<boolean>(false);
    

    localStorage.clear();
    const handleChange = (e: React.ChangeEvent<HTMLInputElement>): void => {
        if (e.target.name === "email") {
            setEmail(e.target.value);
        } else {
            setPassword(e.target.value);
        }
    }

    const navigate = useNavigate();
    
    const handleLogin = async () => {
        let login = {
            email,
            password
        }
        console.log(email, password)

        try {
            const headers = {
                'Access-Control-Allow-Origin' : '*'
            };
            let res = await axios.post('http://localhost:8000/users/logIn', login, {headers});
            setError(false);
            let user = await res.data;
            console.log(user);
            
            if (user) {
                
                localStorage.setItem('curUserI',user.userId);
               // setLogged(true);
                console.log("check")
                localStorage.setItem('curUserL', "true");
                console.log("second")
                navigate("/");
            } else {
                setError(true);
            }
        } catch (e) {
            setError(true);
        }
    }

    const handleLogout = () => {
        localStorage.clear();
        setLogged(false);
    }



    if (logged) {
        return (
            <Container>
                <Form>
                    <h3>Logged In as `${localStorage.getItem('id')}`</h3>
                    <LoginButton onClick={handleLogout}>Log out</LoginButton>
                </Form>
            </Container>
        );
    } else {
        return (
            <Container>
                {error ? <h4>Please try again.</h4> : <></>}
                <Form>
                    <Label>EMAIL ADDRESS</Label>
                    <InputWrapper>
                        <Input onChange={handleChange} name='email' type="email" />
                    </InputWrapper>
                    <Label>PASSWORD</Label>
                    <FinalWrapper>
                        <Input onChange={handleChange} name='password' type='password' />
                    </FinalWrapper>
                    <LoginButton type='button' onClick={handleLogin}>LOGIN</LoginButton>
                </Form>
            </Container>
        );
    }

};

