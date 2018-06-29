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
 * @author KW
 */
public class BitcoinMiningToWritingWorkRequest extends WorkRequest{
    
    private ArrayList<Bitcoin> bitcoins = new ArrayList<>();

    public ArrayList<Bitcoin> getBitcoins() {
        return bitcoins;
    }

    public void setBitcoins(ArrayList<Bitcoin> bitcoins) {
        this.bitcoins = bitcoins;
    }
}
