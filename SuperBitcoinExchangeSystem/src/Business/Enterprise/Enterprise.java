/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Organization.Organization;
import Business.Organization.OrganizationDirectory;

/**
 *
 * @author Lei
 */
public abstract class Enterprise extends Organization{
    
    private EnterpriseType enterpriseType;
    private OrganizationDirectory organizationDirectory;
    
    public OrganizationDirectory getOrganizationDirectory(){
        return organizationDirectory;
    }
    
    public enum EnterpriseType{
        BitcoinExchangePlatform("Bitcoin Exchange Platform"), 
        Depository("Depository"),
        Miner("Miner"),
        Bank("Bank");
        
        private String value;
        
        public String getValue(){
            return value;
        }
        
        private EnterpriseType(String value){
            this.value = value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
        
        
        
    }
    
    public  EnterpriseType getEnterpriseType(){
        return enterpriseType;
    }
    
    public void setEnterpriseType(EnterpriseType enterpriseType){
        this.enterpriseType = enterpriseType;
    }
    
    public Enterprise(String name, EnterpriseType type){
        super(name);
        this.enterpriseType = type;
        organizationDirectory = new OrganizationDirectory();
    }
    
    @Override
    public String toString() {
        return super.getName() + "（" + enterpriseType +")";
    }
    
    
    
}
