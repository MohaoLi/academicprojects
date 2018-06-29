/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.Employee.Person;

/**
 *
 * @author WPF95
 */
public class BrokerBuyWorkRequest extends  WorkRequest{
    int quantity;
    int price;
    Person customer_buy;
    int buy_quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Person getCustomer_buy() {
        return customer_buy;
    }

    public void setCustomer_buy(Person customer_buy) {
        this.customer_buy = customer_buy;
    }

    public int getBuy_quantity() {
        return buy_quantity;
    }

    public void setBuy_quantity(int buy_quantity) {
        this.buy_quantity = buy_quantity;
    }

    
    
    
    
}
