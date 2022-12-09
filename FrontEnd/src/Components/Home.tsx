import React, { useState } from 'react'
import { Route, Routes } from 'react-router-dom'
import styled from 'styled-components'
import ProductProvider from '../Context/ProductContext'
import { UserProvider } from '../Context/UserContext'
import Announcement from './Announcement/Announcement'
import Cart from './Cart/Cart'
import Footer from './Footer/Footer'
import Login from './LoginRegister/Login'
import NewTabs from './LoginRegister/Tabs/NewTabs'
import Navbar from './Navbar/Navbar'
import ProductLayout from './ProductLayout/ProductLayout'
import Slider from './Slider/Slider'
import { UserProfile } from './UserProfile/UserProfile'
import axios from 'axios';
import { User } from '../Types/User'

const Container = styled.div`
    background-color: #eeeeee;
    display: flex;
    flex-direction: column;
    height: 100vh;
`

const Home: React.FC = () => {
    const id:number = Number(window.localStorage.getItem('curUserI'));
    const [user, setUser] = useState<User>();
    const log = localStorage.getItem("curUserL");

    

    const getTheUser  = async () => {
 
        try {
            let res = await axios.get('http://localhost:8000/users/'+ id);
            let tuser = await res.data;
            setUser(tuser);
            console.log(user);
        }catch(e){}
    };
   

    return (
        <Container>
            <UserProvider>
                <ProductProvider>
                    <Announcement />
                    <Navbar />
                    <Routes>
                        <Route path='/' element={<Slider />} />
                        <Route path='/shop' element={<ProductLayout />} />
                        {   log && user?
                            <Route path='/profile' element={<UserProfile userId={user.userId} firstName={user.firstName} lastName={user.lastName} email={user.email} phoneNumber={user.phoneNumber} address={user.address} password={user.password} />} />
                            :null
                        }
                        <Route path='/cart' element={<Cart />} />
                        <Route path='/login' element={<Login />} />
                    </Routes>
                    {/* <NewTabs /> */}
                    <Footer />
                </ProductProvider>
            </UserProvider>
        </Container>
    );
}

export default Home
