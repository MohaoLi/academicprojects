/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.Other.Bitcoin;
import java.util.ArrayList;

/**
 *
 * @author WPF95
 */
public class WithdrawBitcoinWorkRequest extends WorkRequest{
    Bitcoin  bitcoin;
    Double price;
    
    public WithdrawBitcoinWorkRequest(){
        super();
        
    }

    public Bitcoin getBitcoin() {
        return bitcoin;
    }

    public void setBitcoin(Bitcoin bitcoin) {
        this.bitcoin = bitcoin;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
    
    
}
