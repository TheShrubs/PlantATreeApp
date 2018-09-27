package com.example.theshrubs.plantatree.activity;

import org.junit.Test;

import static org.junit.Assert.*;

public class OrderConfirmActivityTest {
    OrderConfirmActivity oca = new OrderConfirmActivity();
    CartTotalActivity cta = new CartTotalActivity();

    @Test
    //tests the method that calculates the total invoice cost from the items in the user's cart
    public void calcTotalsTest() {
        double discountCost = 0.00;
        double deliveryCost = 20.00;
        double productCost = 40.00;
        double expected = 60.00;
        double output;
        double delta = 0.1;

        output = cta.calcInv(deliveryCost,productCost,discountCost);
        assertEquals(expected,output,delta);
    }
    @Test
    // tests if delivery cost is correctly assigned based on if user has chosen to pay for delivery
    public void calcDeliveryTest(){

       boolean delivery = true;
        boolean deliveryResult = cta.calcDelivery(delivery);


        assertTrue (deliveryResult == true);
    }

    @Test
    //tests if an order number (aka invoice number) is being created successfully
    public void orderNoTest(){
        int outputOrderNo;

        outputOrderNo = oca.createOrder();
        assertTrue(outputOrderNo !=0);

    }
}