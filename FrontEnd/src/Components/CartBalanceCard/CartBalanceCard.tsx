import React, { useContext } from 'react'
import styled, { keyframes } from 'styled-components'
import { Context } from '../../Context/ProductContext'
import { ProductContextState } from '../../Types/Product'
import CartCard from '../CartCard/CartCard'

const fadeIn = keyframes`
    0% {opacity: 0%},
    100% {opacity: 100%}
`
const Container = styled.div`
    width: 300px;
    display: flex;
    flex-direction: column;
    padding: 5px;
    box-shadow: 0 0 10px 5px rgba(0,0,0,0.2);
    background: #fff;
    animation: ${fadeIn} 1s;
`
const CartBalance = styled.h3`
    border-bottom: 1px solid #ccc;
`
const Wrapper = styled.div`
    display: flex;
    justify-content: space-between;
    flex-direction: column;
    padding: 10px;
    height: 80%;
`
const BottomWrapper = styled.div`
    display: flex;
    justify-content: right;
    padding: 5px;
    border-bottom: 1px solid #ccc;
`
const FinalWrapper = styled.div`
    display: flex;
    justify-content: right;
    padding-top: 15px;
    padding-inline: 5px;
`
const Subtotal = styled.div`
    flex: 1;
    padding-bottom: 5px;
    font-size: 16px;
`
const Shipping = styled.div`
    flex: 1;
    padding-bottom: 5px;
    font-size: 16px;
`
const Tax = styled.div`
    flex: 1;
    padding-bottom: 5px;
    font-size: 16px;
`
const Total = styled.div`
    flex: 1;
    font-weight: bold;
    font-size: 20px;
    padding-bottom: 5px;
`

const CartBalanceCard: React.FC = () => {

    const { products, cartTotal } = useContext(Context) as ProductContextState;

    let subtotal = cartTotal(products);

    let shipping: number = 2.00;

    if (subtotal > 952.38) {
        shipping = 0;
    }

    let total = ((cartTotal(products) * 1.59) + shipping).toFixed(2);

    return (
        <Container>
            <CartBalance>Summary</CartBalance>
            {
                products.map((product, index) => {
                    return (
                        <CartCard key={index} productId={product.productId} img={product.img} title={product.title} desc={product.desc} price={product.price} amount={product.amount} itemOrder={index + 1} />
                    );
                })
            }
            <Wrapper>
                <BottomWrapper>
                    <Subtotal>Subtotal </Subtotal>
                    ${subtotal.toFixed(2)}
                </BottomWrapper>
                <BottomWrapper>
                    <Shipping>Shipping </Shipping>
                    ${shipping.toFixed(2)}
                </BottomWrapper>
                <BottomWrapper>
                    <Tax>Tax </Tax>
                    59%
                </BottomWrapper>
                <FinalWrapper>
                    <Total>Total </Total>
                    ${total}
                </FinalWrapper>
            </Wrapper>
        </Container>
    )
}

export default CartBalanceCard
