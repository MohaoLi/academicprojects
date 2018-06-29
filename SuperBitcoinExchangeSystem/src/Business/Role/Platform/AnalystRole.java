/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role.Platform;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Enterprise.PlatformEnterprise;
import Business.Organization.BitcoinPlatform.AnalysisOrganization;
import Business.Organization.Organization;
import Business.Role.Role;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import userInterface.Platform.Analyst.AnalystWorkAreaJPanel;

/**
 *
 * @author Lei
 */
public class AnalystRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business) {
        return new AnalystWorkAreaJPanel(userProcessContainer, account, (AnalysisOrganization) organization, (PlatformEnterprise) enterprise,business);
    }
    
}
