/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role.Platform;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Enterprise.PlatformEnterprise;
import Business.Organization.BitcoinPlatform.AccountingOrganization;
import Business.Organization.BitcoinPlatform.CustomerManagementOrganization;
import Business.Organization.Organization;
import Business.Role.Role;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import userInterface.Platform.Customer.CustomerWorkAreaJPanel;

/**
 *
 * @author Lei
 */
public class CustomerRole extends Role{
    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business) {
 //       return new AdminWorkAreaJPanel(userProcessContainer, enterprise);
          return new CustomerWorkAreaJPanel(userProcessContainer, account, (CustomerManagementOrganization)organization, (PlatformEnterprise)enterprise,business);
    }
}
