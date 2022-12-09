import React from "react";
import { useContext, useState } from "react";
import styled from "styled-components";
import { Context } from "../../Context/UserContext";
import { receipts } from "../../testReceipt";
import { UserContextState, User } from "../../Types/User";
import AccountDetails from "../AccountDetails/AccountDetails";
import PastOrders from "../PastOrders/PastOrders";
import ProfileNavigation from "../ProfileNavigation/ProfileNavigation";
import ReceiptCard from "../ReceiptCard/ReceiptCard";
import axios from 'axios';
import { Navigate, useNavigate } from 'react-router-dom';

const Container = styled.div`
    height: 100vh;
    display: flex;
    justify-content: center;
`
const Wrapper = styled.div`
    display: flex;
    margin-block: 50px;
`
const ReceiptWrapper = styled.div`
    margin-top: 10px;
    background-color: white;
    height: fit-content;
    box-shadow: 0 0 10px 2px rgba(0,0,0,0.2);
`

export const UserProfile: React.FC<User> = ({
    id,
    firstName,
    lastName,
    email,
    phoneNumber,
    address,
    password,

}) => {

    const [user, setUser] = useState<User>();
    const [error, setError] = useState<boolean>(false);
    const navigate = useNavigate();

    
    const { updateUser, removeUser, currentTab } = useContext(Context) as UserContextState;

    const getTheUser = async () =>{
        try{
            const res = await axios.get('http://localhost:8000/users/user/'+id);
            setUser(await res.data);
            setError(false);
        }catch(e){
            setError(true);
        }
    };

    const editProfile = () => {
        updateUser(id);
    };

    const deleteProfile = () => {
        removeUser(id);
    };
    getTheUser();


    return (
        <Container>
            <Wrapper>
                <ProfileNavigation id={id} firstName={firstName} lastName={lastName} email={email} phoneNumber={phoneNumber} address={address} password={password} />
                {
                    (currentTab === '1') ?
                        <AccountDetails id={id} firstName={firstName} lastName={firstName} email={email} phoneNumber={phoneNumber} address={address} password={password} />
                        :
                        <ReceiptWrapper>
                            <PastOrders id={id} firstName={firstName} lastName={firstName} email={email} phoneNumber={phoneNumber} address={address} password={password} />
                        </ReceiptWrapper>
                }
            </Wrapper>
        </Container>
    );
};
