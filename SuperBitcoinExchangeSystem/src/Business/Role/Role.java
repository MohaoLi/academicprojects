/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;

/**
 *
 * @author Lei
 */
public abstract class Role {
    public enum RoleType{
    //platform
        CustomerManager("Customer Manager"), PlatformAdminstrator("Platform Administrator"), 
        Accountant("Platform Accountant"), BitcoinBroker("Bitcoin Broker"), BitcoinMatcher("Bitcoin Matcher"),
        Analyst("Platform Analyst"),
        Customer("Customer"),
    //Depository
        DepositoryAdminstrator("Depository Adminstrator"), Custodian("Custodian"), Recorder("Desopistory Recorder"),
    //Miner
        MinerAdministrator("Miner Administrator"), BitcoinMiner("Bitcoin Miner"), Writer("Writer"),
    //Bank
        BankAdministrator("Bank Administrator"), BankAccountant("Bank Accountant");
        
        
        private String value;
        private RoleType(String value){
            this.value = value;
        }
        
        public String getValue(){
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
        
        
        
    }
    public abstract JPanel createWorkArea(JPanel userProcessContainer,
            UserAccount account,
            Organization organization,
            Enterprise enterprise,
            EcoSystem business);
    
    @Override
    public String toString(){
        return this.getClass().getName();
    }
    
}
