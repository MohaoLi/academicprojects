/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Customer.CustomerDirectory;
import Business.Employee.Person;
import Business.Organization.Organization;
import Business.Role.Platform.PlatformAdminRole;
import Business.Role.Role;
import Business.UserAccount.UserAccountDirectory;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Lei
 */
public class PlatformEnterprise extends Enterprise{
    private CustomerDirectory customerDirectory;
    private UserAccountDirectory customerUserAccountDirectory;
    private double income;
    private HashMap<Person, Double> brokerIncome;
    
    public PlatformEnterprise(String name){
        super(name, EnterpriseType.BitcoinExchangePlatform);
        customerDirectory = new CustomerDirectory();
        customerUserAccountDirectory = new UserAccountDirectory();  
        this.income = 0;
        this.brokerIncome = new HashMap<>();
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new PlatformAdminRole());
        return roles;
    
    }

    public HashMap<Person, Double> getBrokerIncome() {
        return brokerIncome;
    }

    public void setBrokerIncome(HashMap<Person, Double> brokerIncome) {
        this.brokerIncome = brokerIncome;
    }

    public CustomerDirectory getCustomerDirectory() {
        return customerDirectory;
    }

    public void setCustomerDirectory(CustomerDirectory customerDirectory) {
        this.customerDirectory = customerDirectory;
    }

    public UserAccountDirectory getCustomerUserAccountDirectory() {
        return customerUserAccountDirectory;
    }

    public void setCustomerUserAccountDirectory(UserAccountDirectory customerUserAccountDirectory) {
        this.customerUserAccountDirectory = customerUserAccountDirectory;
    }
    
   public static ArrayList<Organization.Type> getSupportedOrganization(){
       ArrayList<Organization.Type> types = new ArrayList<>();
       types.add(Organization.Type.CustomerManagement);
       types.add(Organization.Type.Accounting);
       types.add(Organization.Type.Broker);
       types.add(Organization.Type.BitcoinMatcher);
       types.add(Organization.Type.Analysis);
       return types;
       
   }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }
    
    
}
