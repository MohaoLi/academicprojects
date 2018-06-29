/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Other;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lei
 */
public class Wallet {
    private List<Bitcoin> bitcoinList;
    private double flatMoney;
    
    public Wallet(){
        bitcoinList = new ArrayList<>();
        flatMoney = 0.0;
    }

    public List<Bitcoin> getBitcoinList() {
        return bitcoinList;
    }

    public void setBitcoinList(List<Bitcoin> bitcoinList) {
        this.bitcoinList = bitcoinList;
    }

    public double getFlat() {
        return flatMoney;
    }

    public void setFlat(double flatMoney) {
        this.flatMoney = flatMoney;
    }
    
}
