/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization.Miner;

import Business.Organization.Organization;
import Business.Role.Miner.WriterRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Lei
 */
public class WritingOrganization extends Organization{
    public WritingOrganization(){
        super(Type.Writing.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new WriterRole());
        return roles;
    }
    
    
}
