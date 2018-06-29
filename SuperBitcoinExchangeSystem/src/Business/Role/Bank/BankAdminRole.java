/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role.Bank;

import Business.EcoSystem;
import Business.Enterprise.BankEnterprise;
import Business.Enterprise.Enterprise;
import Business.Organization.Bank.BankAdminOrganization;
import Business.Organization.Organization;
import Business.Role.Role;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import userInterface.Bank.BankAdmin.BankAdminWorkAreaJPanel;

/**
 *
 * @author Lei
 */
public class BankAdminRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business) {
        return new BankAdminWorkAreaJPanel(userProcessContainer, account, (BankAdminOrganization) organization, (BankEnterprise) enterprise, business);
    }
    
}
