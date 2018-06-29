/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization.BitcoinPlatform;

import Business.Organization.Organization;
import Business.Customer.CustomerDirectory;
import Business.Role.Platform.CustomerManagerRole;
import Business.Role.Role;
import Business.UserAccount.UserAccountDirectory;
import java.util.ArrayList;

/**
 *
 * @author Lei
 */
public class CustomerManagementOrganization extends Organization{
    
    
    public CustomerManagementOrganization(){
        super(Type.CustomerManagement.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new CustomerManagerRole());
        return roles;
    }

    


    
    
}
