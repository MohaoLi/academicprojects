/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role.Miner;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Enterprise.MinerEnterprise;
import Business.Organization.Miner.WritingOrganization;
import Business.Organization.Organization;
import Business.Role.Role;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import userInterface.Miner.Writer.WriterWorkAreaJPanel;

/**
 *
 * @author Lei
 */
public class WriterRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business) {
        return new WriterWorkAreaJPanel(userProcessContainer, account, (WritingOrganization)organization, (MinerEnterprise)enterprise,business);
    }
    
}
