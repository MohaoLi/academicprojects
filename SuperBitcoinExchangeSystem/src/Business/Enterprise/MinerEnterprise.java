/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Organization.Organization;
import Business.Role.Miner.MinerAdminRole;
import Business.Role.Platform.BitcoinMatcherRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Lei
 */
public class MinerEnterprise extends Enterprise{
    public MinerEnterprise(String name){
        super(name, EnterpriseType.Miner);
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new MinerAdminRole());
        return roles;
    }
    
    public static ArrayList<Organization.Type> getSupportedOrganization(){
       ArrayList<Organization.Type> types = new ArrayList<>();
       types.add(Organization.Type.Mining);
       types.add(Organization.Type.Writing);
       return types;
       
   }
}
