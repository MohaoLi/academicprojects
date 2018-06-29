/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Business.Employee.Person;
import Business.Role.SystemAdminRole;
import Business.UserAccount.UserAccount;

/**
 *
 * @author Lei
 */
public class ConfigureSystem {
    public static EcoSystem configure(){
        EcoSystem system = EcoSystem.getInstance();
        
        Person employee = system.getEmployeeDirectory().createEmployee("Pengfei Wang");
        UserAccount ua = system.getUserAccountDirectory().createUserAccount("pfw", "pfw", employee, new SystemAdminRole());
        return system;
    }
}
