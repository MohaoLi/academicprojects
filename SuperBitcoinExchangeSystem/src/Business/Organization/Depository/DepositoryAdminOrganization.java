/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization.Depository;

import Business.Organization.Organization;
import Business.Role.Depository.DepositoryAdminRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Lei
 */
public class DepositoryAdminOrganization extends Organization{
    public DepositoryAdminOrganization(){
        super(Type.DepositoryAdmin.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new DepositoryAdminRole());
        return roles;
    }
    
    
}
