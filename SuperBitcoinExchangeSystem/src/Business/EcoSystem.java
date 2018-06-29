/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Business.Enterprise.Enterprise;
import Business.Enterprise.PlatformEnterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.Role.Role;
import Business.Role.SystemAdminRole;
import java.util.ArrayList;

/**
 *
 * @author Lei
 */
public class EcoSystem extends Organization{

    private static EcoSystem business;
    private ArrayList<Network> networkList;
    private double bitcoinPrice;

    public ArrayList<Network> getNetworkList() {
        return networkList;
    }
    
    public static EcoSystem getInstance(){
        if(business == null){
            business = new EcoSystem();
        }
        return business;
    }
    
    public Network createAndAddNetwork(){
        Network network = new Network();
        networkList.add(network);
        return network;
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(new SystemAdminRole());
        return roleList;
    }
    
    private EcoSystem(){
        super(null);
        networkList = new ArrayList<>();
    }
    
    public boolean checkIfUserNameIsUnique(String userName){
        if(!this.getUserAccountDirectory().checkIfUsernameIsUnique(userName)){
            return false;
        }
        boolean unique = true;
        for(Network n : networkList){
            for(Enterprise enterprise : n.getEnterpriseDirectory().getEnterpriseList()){
                if(enterprise instanceof PlatformEnterprise){
                    if(((PlatformEnterprise) enterprise).getCustomerUserAccountDirectory().checkIfUsernameIsUnique(userName) == false ||
                            ((PlatformEnterprise)enterprise).getUserAccountDirectory().checkIfUsernameIsUnique(userName) == false){
                        unique = false;
                        break;
                    }
             
                }
                else{
                    if( enterprise.getUserAccountDirectory().checkIfUsernameIsUnique(userName) == false ){
                        unique = false;
                        break;
                    }else{
                        for(Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()){
                            if(organization.getUserAccountDirectory().checkIfUsernameIsUnique(userName) == false){
                                unique = false;
                                break;
                            }
                        }
                        if(unique == false){
                            break;
                        }
                   
                    }
                    
                }
                if(unique == false){
                    break;
                }
            }
            if(unique == false){
                break;
            }
            
            
        }
        return unique;
    }
    
    
    
//    public static int getId(){
//        ArrayList<Integer> listId = new ArrayList<Integer>(); 
//        for(Network network : business.getNetworkList()){
//            
//            for(Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()){
//                
//            }
//        }
//    }

    public double getBitcoinPrice() {
        return bitcoinPrice;
    }

    public void setBitcoinPrice(double bitcoinPrice) {
        this.bitcoinPrice = bitcoinPrice;
    }
    
}
