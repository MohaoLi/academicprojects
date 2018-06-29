/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role.Depository;

import Business.EcoSystem;
import Business.Enterprise.DepositoryEnterprise;
import Business.Enterprise.Enterprise;
import Business.Organization.Depository.DepositoryAdminOrganization;
import Business.Organization.Organization;
import Business.Role.Role;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import userInterface.Depository.DepositoryAdmin.DepositoryAdminWorkAreaJPanel;

/**
 *
 * @author Lei
 */
public class DepositoryAdminRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business) {
        return new DepositoryAdminWorkAreaJPanel(userProcessContainer, account, (DepositoryAdminOrganization)organization, (DepositoryEnterprise)enterprise,business);
    }
    
}
