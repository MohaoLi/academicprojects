/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.Employee.Employee;

/**
 *
 * @author WPF95
 */
public class AssignBrokerWorkRequest extends WorkRequest{
    private Employee broker;

    public Employee getBroker() {
        return broker;
    }

    public void setBroker(Employee broker) {
        this.broker = broker;
    }
    
}
