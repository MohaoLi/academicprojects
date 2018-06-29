/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.Other.Bitcoin;

/**
 *
 * @author WPF95
 */
public class UploadBitcoinWorkRequest extends WorkRequest {
    Bitcoin bitcoin;
    Double bitcoin_price;

    public Double getBitcoin_price() {
        return bitcoin_price;
    }

    public void setBitcoin_price(Double bitcoin_price) {
        this.bitcoin_price = bitcoin_price;
    }

    public Bitcoin getBitcoin() {
        return bitcoin;
    }

    public void setBitcoin(Bitcoin bitcoin) {
        this.bitcoin = bitcoin;
    }
    
    
}
