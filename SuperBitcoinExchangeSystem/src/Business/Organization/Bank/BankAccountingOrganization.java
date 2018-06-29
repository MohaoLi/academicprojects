/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization.Bank;

import Business.Organization.Organization;
import Business.Role.Bank.BankAccountantRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Lei
 */
public class BankAccountingOrganization extends Organization{
    
    public BankAccountingOrganization(){
        super(Type.BankAdmin.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new BankAccountantRole());
        return roles;
    }
    
}
