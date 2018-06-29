/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Employee;

import Business.Other.Bitcoin;
import Business.Other.Wallet;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Lei
 */
public abstract class Person {
    
    private String name;
    private int id;
    private static int count = 1;
    private Wallet wallet;
    private ArrayList<Bitcoin> localBitcoinWallet;
    private Integer bankAccount;

    public Person(){
        wallet = new Wallet();
        localBitcoinWallet = new ArrayList<>();
        id = count;
        count++;
        bankAccount = null;
        
    } 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        
        this.wallet = wallet;
    }

    public ArrayList<Bitcoin> getLocalBitcoinWallet() {
        return localBitcoinWallet;
    }

    public void setLocalBitcoinWallet(ArrayList<Bitcoin> localBitcoinWallet) {
        this.localBitcoinWallet = localBitcoinWallet;
    }

    public Integer getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(Integer bankAccount) {
        this.bankAccount = bankAccount;
    }
    
    public static void setCount(int id){
        count = id +1;
    }

    
}
