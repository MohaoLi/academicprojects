/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization.BitcoinPlatform;

import Business.Organization.Organization;
import Business.Role.Platform.BrokerRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Lei
 */
public class BrokerOrganization extends Organization{
    public BrokerOrganization(){
        super(Organization.Type.Broker.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new BrokerRole());
        return roles;
    }
}
