/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

/**
 *
 * @author WPF95
 */
public class WithdrawMoneyWorkRequest extends WorkRequest{
    double money;
    int bankaccount;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getBankaccount() {
        return bankaccount;
    }

    public void setBankaccount(int bankaccount) {
        this.bankaccount = bankaccount;
    }
    
    
}
