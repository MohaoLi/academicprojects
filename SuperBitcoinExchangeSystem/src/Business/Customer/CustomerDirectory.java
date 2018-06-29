/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Customer;

import Business.Employee.Person;
import java.util.ArrayList;

/**
 *
 * @author Lei
 */
public class CustomerDirectory {
    private ArrayList<Person> customerDirectory;

    public ArrayList<Person> getCustomerDirectory() {
        return customerDirectory;
    }

    public void setCustomerDirectory(ArrayList<Person> customerDirectory) {
        this.customerDirectory = customerDirectory;
    }
    
    public CustomerDirectory(){
        customerDirectory = new ArrayList<>();
    }
    
    public Customer createCustomer(String name){
        Customer customer = new Customer();
        customer.setName(name);
        customerDirectory.add(customer);
        return customer;
    }
    
    public void removeCustomer(Customer customer){
        customerDirectory.remove(customer);
    }
    
    public Person researchCustomer(int id){
        for(Person customer : customerDirectory){
            if(customer.getId() == id){
                return customer;
            }
        }
        return null;
    }
    
    
}
