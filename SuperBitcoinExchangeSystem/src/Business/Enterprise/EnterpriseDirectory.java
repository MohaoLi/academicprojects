/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import java.util.ArrayList;

/**
 *
 * @author Lei
 */
public class EnterpriseDirectory {
    private ArrayList<Enterprise> enterpriseList;

    public ArrayList<Enterprise> getEnterpriseList() {
        return enterpriseList;
    }

    public void setEnterpriseList(ArrayList<Enterprise> enterpriseList) {
        this.enterpriseList = enterpriseList;
    }

    public EnterpriseDirectory() {
        this.enterpriseList = new ArrayList<>();
    }
    
    public Enterprise createAndAddEnterprise(String name, Enterprise.EnterpriseType type){
        Enterprise enterprise = null;
        if(type == Enterprise.EnterpriseType.BitcoinExchangePlatform){
            enterprise = new PlatformEnterprise(name);
            enterpriseList.add(enterprise);
            return enterprise;
        } else if(type == Enterprise.EnterpriseType.Depository){
            enterprise = new DepositoryEnterprise(name);
            enterpriseList.add(enterprise);
            return enterprise;
        } else if(type == Enterprise.EnterpriseType.Miner){
            enterprise = new MinerEnterprise(name);
            enterpriseList.add(enterprise);
            return enterprise;
        } else if (type == Enterprise.EnterpriseType.Bank){
            enterprise = new BankEnterprise(name);
            enterpriseList.add(enterprise);
            return enterprise;
        }
        return enterprise;
    }
}
