/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.Employee.Person;
import Business.Other.Bitcoin;
import java.util.ArrayList;

/**
 *
 * @author WPF95
 */
public class BrokerSellWorkRequest extends WorkRequest{
    Person customer_sell;
    int price;
    ArrayList<Bitcoin> bitcoinlist;
    int quantity;    
    
    public BrokerSellWorkRequest(){
        super();
        bitcoinlist = new ArrayList<>();
    }

    public Person getCustomer_sell() {
        return customer_sell;
    }

    public void setCustomer_sell(Person customer_sell) {
        this.customer_sell = customer_sell;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ArrayList<Bitcoin> getBitcoinlist() {
        return bitcoinlist;
    }

    public void setBitcoinlist(ArrayList<Bitcoin> bitcoinlist) {
        this.bitcoinlist = bitcoinlist;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    
    
    
    
}
