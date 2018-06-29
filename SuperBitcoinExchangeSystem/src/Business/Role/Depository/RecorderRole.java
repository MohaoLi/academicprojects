/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role.Depository;

import Business.EcoSystem;
import Business.Enterprise.DepositoryEnterprise;
import Business.Enterprise.Enterprise;
import Business.Organization.Depository.RecordsOrganization;
import Business.Organization.Organization;
import Business.Role.Role;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import userInterface.Depository.Recorder.RecorderWorkAreaJPanel;

/**
 *
 * @author Lei
 */
public class RecorderRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business) {
        return new RecorderWorkAreaJPanel(userProcessContainer, account, (RecordsOrganization)organization, (DepositoryEnterprise)enterprise,business);
    }
    
}
