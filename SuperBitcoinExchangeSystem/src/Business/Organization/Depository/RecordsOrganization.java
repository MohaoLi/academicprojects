/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization.Depository;

import Business.Organization.Organization;
import Business.Role.Depository.RecorderRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Lei
 */
public class RecordsOrganization extends Organization{
    public RecordsOrganization(){
        super(Type.Records.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new RecorderRole());
        return roles;
    }
    
    
}
