/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role.Miner;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Enterprise.MinerEnterprise;
import Business.Organization.Miner.MinerAdminOrganization;
import Business.Organization.Organization;
import Business.Role.Role;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import userInterface.Miner.MinerAdmin.MinerAdminWorkAreaJPanel;

/**
 *
 * @author Lei
 */
public class MinerAdminRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business) {
        return new MinerAdminWorkAreaJPanel(userProcessContainer, account, (MinerAdminOrganization)organization, (MinerEnterprise)enterprise,business);
    }
    
}
