/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Employee.EmployeeDirectory;
import Business.Role.Role;
import Business.UserAccount.UserAccountDirectory;
import Business.WorkQueue.WorkQueue;
import java.util.ArrayList;

/**
 *
 * @author Lei
 */
public abstract class Organization {

    private String name;
    private WorkQueue workQueue;
    private EmployeeDirectory employeeDirectory;
    private UserAccountDirectory userAccountDirectory;
    private int organizationID;
    private static int counter;

    public enum Type {
        //Platform      
        CustomerManagement("Customer Management Organization"), PlatformAdministrator("Platform Administrator Organization"), Accounting("Accounting Organization"),
        Broker("Broker Organization"), Analysis("Analysis Organization"), BitcoinMatcher("BitcoinMatcher Organization"),
        //Depository
        Authentication("Authentication Organization"), Records("Records Organization"), DepositoryAdmin("Depository Administrator Organization"),
        //Miner
        Mining("Mining Organization"), Writing("Writing Organization"), MinerAdmin("Miner Administrator Organization"),
        //Bank
        BankAccouting("Bank Accouting Organization"), BankAdmin("Bank Administrator Organization");

        private String value;

        private Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }

    public Organization(String name) {
        this.name = name;
        workQueue = new WorkQueue();
        employeeDirectory = new EmployeeDirectory();
        userAccountDirectory = new UserAccountDirectory();
        organizationID = counter;
        ++counter;
    }

    public abstract ArrayList<Role> getSupportedRole();

    public String getName() {
        return name;
    }

    public WorkQueue getWorkQueue() {
        return workQueue;
    }

    public EmployeeDirectory getEmployeeDirectory() {
        return employeeDirectory;
    }

    public UserAccountDirectory getUserAccountDirectory() {
        return userAccountDirectory;
    }

    public int getOrganizationID() {
        return organizationID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWorkQueue(WorkQueue workQueue) {
        this.workQueue = workQueue;
    }

    public static void setCount(int id) {
        counter = id + 1;
    }

    @Override
    public String toString() {
        return name;
    }

}
