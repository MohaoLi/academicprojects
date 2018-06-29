/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Other;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lei
 */
public class Bitcoin {
    private int serialNum;
    private String privateKey;
    private ArrayList<ExchangeHistory> exchangeHistoryList;
    private Date birthday;
    private static int count = 1000;
    
    public Bitcoin(){
        exchangeHistoryList = new ArrayList<>();
        birthday = new Date();
        this.serialNum = count;
        count ++;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public ArrayList<ExchangeHistory> getExchangeHistoryList() {
        return exchangeHistoryList;
    }

    public void setExchangeHistoryList(ArrayList<ExchangeHistory> exchangeHistoryList) {
        this.exchangeHistoryList = exchangeHistoryList;
    }

    public int getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(int seriaNum) {
        this.serialNum = seriaNum;
    }
    
    
    
    @Override
    public String toString(){
        return String.valueOf(serialNum);
    }
}
