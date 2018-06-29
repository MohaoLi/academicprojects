/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Organization.Organization;
import Business.Role.Bank.BankAdminRole;
import Business.Role.Depository.DepositoryAdminRole;
import Business.Role.Role;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Lei
 */
public class BankEnterprise extends Enterprise{
    
    private HashMap<Integer,Double> accountList;
    
    public BankEnterprise(String name){
        super(name, EnterpriseType.Bank);
        accountList = new HashMap<>();
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new BankAdminRole());
        return roles;

    }
    
    public static ArrayList<Organization.Type> getSupportedOrganization(){
       ArrayList<Organization.Type> types = new ArrayList<>();
       types.add(Organization.Type.BankAccouting);
       return types;
       
   }

    public HashMap<Integer, Double> getAccountList() {
        return accountList;
    }

    public void setAccountList(HashMap<Integer, Double> accountList) {
        this.accountList = accountList;
    }
    
}
