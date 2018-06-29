/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Other;

import Business.Customer.Customer;
import Business.Employee.Person;
import java.util.Date;

/**
 *
 * @author Lei
 */
public class ExchangeHistory {
    private Person seller;
    private Person buyer;
    private Date date;
    private double exchangePrize;
    private Person sell_broker;
    private Person buy_broker;
    
    public ExchangeHistory(){
        seller = new Customer();
        buyer = new Customer();
        date = new Date();
    }

    public Person getSeller() {
        return seller;
    }

    public void setSeller(Person seller) {
        this.seller = seller;
    }

    public Person getBuyer() {
        return buyer;
    }

    public void setBuyer(Person buyer) {
        this.buyer = buyer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getExchangePrize() {
        return exchangePrize;
    }

    public void setExchangePrize(double exchangePrize) {
        this.exchangePrize = exchangePrize;
    }

    public Person getSell_broker() {
        return sell_broker;
    }

    public void setSell_broker(Person sell_broker) {
        this.sell_broker = sell_broker;
    }

    public Person getBuy_broker() {
        return buy_broker;
    }

    public void setBuy_broker(Person buy_broker) {
        this.buy_broker = buy_broker;
    }
    
    
    
    @Override
    public String toString(){
        return String.valueOf(date);
    }
    
}
