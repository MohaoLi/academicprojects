/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Organization.Organization;
import Business.Other.Bitcoin;
import Business.Role.Depository.DepositoryAdminRole;
import Business.Role.Role;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Lei
 */
public class DepositoryEnterprise extends Enterprise {

    private HashMap<Bitcoin, Boolean> bitcoinList;

    public DepositoryEnterprise(String name) {
        super(name, EnterpriseType.Depository);
         bitcoinList = new HashMap<>();
         mineBitcoin(bitcoinList);
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new DepositoryAdminRole());
        return roles;
    }

    public HashMap<Bitcoin, Boolean> getBitcoinList() {
        return bitcoinList;
    }

    public void setBitcoinList(HashMap<Bitcoin, Boolean> bitcoinList) {
        this.bitcoinList = bitcoinList;
    }
    
    public static ArrayList<Organization.Type> getSupportedOrganization(){
       ArrayList<Organization.Type> types = new ArrayList<>();
       types.add(Organization.Type.Authentication);
       types.add(Organization.Type.Records);
       return types;
       
   }
    private void mineBitcoin(HashMap<Bitcoin, Boolean> bitcoinList){
        for(int i = 0; i < 10000; i ++){
            Bitcoin bitcoin = new Bitcoin();
            bitcoin.setPrivateKey(getRandomString());
            bitcoinList.put(bitcoin, false);
        }
    }
    
    public static String getRandomString() {
        
        String str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
       
        for (int i = 0; i < 32; ++i) {
           
            int number = random.nextInt(62);
            
            sb.append(str.charAt(number));
        }
        
        return sb.toString();
    }
}
