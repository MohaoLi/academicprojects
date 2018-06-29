/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Organization.Bank.BankAccountingOrganization;
import Business.Organization.Bank.BankAdminOrganization;
import Business.Organization.BitcoinPlatform.AccountingOrganization;
import Business.Organization.BitcoinPlatform.AnalysisOrganization;
import Business.Organization.BitcoinPlatform.BitcoinMatcherOrganization;
import Business.Organization.BitcoinPlatform.BrokerOrganization;
import Business.Organization.BitcoinPlatform.CustomerManagementOrganization;
import Business.Organization.BitcoinPlatform.PlatformAdminOrganization;
import Business.Organization.Depository.AuthenticationOrganization;
import Business.Organization.Depository.DepositoryAdminOrganization;
import Business.Organization.Depository.RecordsOrganization;
import Business.Organization.Miner.MinerAdminOrganization;
import Business.Organization.Miner.MiningOrganization;
import Business.Organization.Miner.WritingOrganization;
import Business.Organization.Organization.Type;
import java.util.ArrayList;

/**
 *
 * @author Lei
 */
public class OrganizationDirectory {
    
    private ArrayList<Organization> organizationList;
    
    public OrganizationDirectory(){
        organizationList = new ArrayList<>();
    }
    
    public ArrayList<Organization> getOrganizationList(){
        return organizationList;
    }
    
    public Organization createOrganization(Type type){
        Organization organization = null;
        
        if (type.getValue().equals(Type.Accounting.getValue())){
            organization = new AccountingOrganization();
            organizationList.add(organization);
        } else if (type.getValue().equals(Type.Analysis.getValue())){
            organization = new AnalysisOrganization();
            organizationList.add(organization);
        } else if(type.getValue().equals(Type.BankAccouting.getValue())){
            organization = new BankAccountingOrganization();
            organizationList.add(organization);
        } else if(type.getValue().equals(Type.BankAdmin.getValue())){
            organization = new BankAdminOrganization();
            organizationList.add(organization);
        } else if(type.getValue().equals(Type.BitcoinMatcher.getValue())){
            organization = new BitcoinMatcherOrganization();
            organizationList.add(organization);
        } else if(type.getValue().equals(Type.Broker.getValue())){
            organization = new BrokerOrganization();
            organizationList.add(organization);
        } else if(type.getValue().equals(Type.CustomerManagement.getValue())){
            organization = new CustomerManagementOrganization();
            organizationList.add(organization);
        } else if(type.getValue().equals(Type.PlatformAdministrator.getValue())){
            organization = new PlatformAdminOrganization();
            organizationList.add(organization);
        } else if(type.getValue().equals(Type.Authentication.getValue())){
            organization = new AuthenticationOrganization();
            organizationList.add(organization);
        } else if(type.getValue().equals(Type.DepositoryAdmin.getValue())){
            organization = new DepositoryAdminOrganization();
            organizationList.add(organization);
        } else if(type.getValue().equals(Type.Records.getValue())){
            organization = new RecordsOrganization();
            organizationList.add(organization);
        } else if(type.getValue().equals(Type.Mining.getValue())){
            organization = new MiningOrganization();
            organizationList.add(organization);
        } else if (type.getValue().equals(Type.Writing.getValue())) {
            organization = new WritingOrganization();
            organizationList.add(organization);
        } else if(type.getValue().equals(Type.MinerAdmin.getValue())){
            organization = new MinerAdminOrganization();
            organizationList.add(organization);
        } 
        return organization;
    }
    
}
