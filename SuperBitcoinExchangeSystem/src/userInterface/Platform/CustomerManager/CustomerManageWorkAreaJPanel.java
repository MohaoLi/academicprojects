/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.Platform.CustomerManager;

import Business.EcoSystem;
import Business.Employee.Person;
import Business.Enterprise.DepositoryEnterprise;
import Business.Enterprise.Enterprise;
import Business.Enterprise.PlatformEnterprise;
import Business.Network.Network;
import Business.Organization.BitcoinPlatform.CustomerManagementOrganization;
import Business.Organization.Organization;
import Business.Other.Bitcoin;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.UploadBitcoinWorkRequest;
import Business.WorkQueue.WithdrawBitcoinWorkRequest;
import Business.WorkQueue.WorkRequest;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lei
 */
public class CustomerManageWorkAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form CustomerManageWorkAreaJPanel
     */
    JPanel userProcessContainer;
    UserAccount account;
    CustomerManagementOrganization customerManagementOrganization;
    PlatformEnterprise platformEnterprise;
    EcoSystem ecoSystem;

    public CustomerManageWorkAreaJPanel(JPanel userProcessContainer, UserAccount account, CustomerManagementOrganization customerManagementOrganization, PlatformEnterprise platformEnterprise, EcoSystem ecoSystem) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.customerManagementOrganization = customerManagementOrganization;
        this.platformEnterprise = platformEnterprise;
        this.ecoSystem = ecoSystem;
        populateUnresovledTable();
        populateResovledTable();
        populateCustomerListTable();
        populateUnresovledRequest();
        populateResovledRequest();

    }

    public void populateUnresovledTable() {
        DefaultTableModel model = (DefaultTableModel) requestJTable.getModel();
        model.setRowCount(0);
        for (Organization organization : platformEnterprise.getOrganizationDirectory().getOrganizationList()) {
            if (organization instanceof CustomerManagementOrganization) {
                for (WorkRequest request : organization.getWorkQueue().getWorkRequestList()) {
                    if (request instanceof UploadBitcoinWorkRequest && request.getReceiver() == null) {
                        Object[] row = new Object[5];
                        row[0] = request;
                        row[1] = request.getSender().getPerson().getId();
                        row[2] = request.getSender().getPerson().getName();
                        row[3] = ((UploadBitcoinWorkRequest) request).getBitcoin();
                        row[4] = request.getStatusString();
                        model.addRow(row);
                    }
                }
            }
        }
    }

    public void populateResovledTable() {
        DefaultTableModel model = (DefaultTableModel) resovledJTable.getModel();
        model.setRowCount(0);
        for (Organization organization : platformEnterprise.getOrganizationDirectory().getOrganizationList()) {
            if (organization instanceof CustomerManagementOrganization) {
                for (WorkRequest request : organization.getWorkQueue().getWorkRequestList()) {
                    if (request instanceof UploadBitcoinWorkRequest && request.getReceiver() != null) {
                        Object[] row = new Object[6];
                        row[0] = request;
                        row[1] = request.getSender().getPerson().getId();
                        row[2] = request.getSender().getPerson().getName();
                        row[3] = request.getReceiver().getPerson().getName();
                        row[4] = ((UploadBitcoinWorkRequest) request).getBitcoin();
                        row[5] = request.getStatusString();
                        model.addRow(row);
                    }
                }
            }
        }
    }

    public void populateCustomerListTable() {
        DefaultTableModel model = (DefaultTableModel) populateCustomerlistJTable.getModel();
        model.setRowCount(0);
        for (UserAccount userAccount : platformEnterprise.getCustomerUserAccountDirectory().getUserAccountList()) {
            Object[] row = new Object[2];
            row[0] = userAccount.getPerson().getId();
            row[1] = userAccount.getPerson();
            model.addRow(row);
        }
    }

    public void populateUnresovledRequest() {
        DefaultTableModel model = (DefaultTableModel) requestJTable1.getModel();
        model.setRowCount(0);
        for (WorkRequest request : customerManagementOrganization.getWorkQueue().getWorkRequestList()) {
            if (request instanceof WithdrawBitcoinWorkRequest && request.getReceiver() == null) {
                Object[] row = new Object[4];
                row[0] = request;
                row[1] = request.getSender().getPerson().getId();
                row[2] = request.getSender().getPerson();
                row[3] = request.getStatusString();
                model.addRow(row);
            }
        }
    }

    public void populateResovledRequest() {
        DefaultTableModel model = (DefaultTableModel) resovledJTable1.getModel();
        model.setRowCount(0);
        for (WorkRequest request : customerManagementOrganization.getWorkQueue().getWorkRequestList()) {
            if (request instanceof WithdrawBitcoinWorkRequest && request.getReceiver() != null) {
                Object[] row = new Object[5];
                row[0] = request;
                row[1] = request.getSender().getPerson().getId();
                row[2] = request.getSender().getPerson();
                row[3] = request.getReceiver().getPerson();
                row[4] = request.getStatusString();
                model.addRow(row);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        requestJTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        resovledJTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        populateCustomerlistJTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        idJTextField = new javax.swing.JTextField();
        quantityJTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nameJTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        moneyTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        passwordJTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        bankaccountJTextField = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        resovledJTable1 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        requestJTable1 = new javax.swing.JTable();

        setBackground(new java.awt.Color(0, 102, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Customer Management Work Area");

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1000, 618));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 618));

        requestJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Message", "Id", "Sender", "Bitcoin Serial", "Statu"
            }
        ));
        jScrollPane2.setViewportView(requestJTable);
        if (requestJTable.getColumnModel().getColumnCount() > 0) {
            requestJTable.getColumnModel().getColumn(3).setHeaderValue("Quantity");
        }

        resovledJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Message", "Id", "Sender", "Receiver", "Bitcoin Num", "Statu"
            }
        ));
        jScrollPane3.setViewportView(resovledJTable);
        if (resovledJTable.getColumnModel().getColumnCount() > 0) {
            resovledJTable.getColumnModel().getColumn(4).setHeaderValue("Quantity");
        }

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Unresovled Request");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Resovled Request");

        jButton5.setText("Submit");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
                    .addComponent(jButton5)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3))
                .addContainerGap(131, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addGap(18, 18, 18)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Authentication", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        populateCustomerlistJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer ID", "Customer Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(populateCustomerlistJTable);

        jButton1.setText("Select");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Customer Detail Information");

        jLabel7.setText("Available Bitcoin Quantity");

        idJTextField.setEditable(false);

        quantityJTextField.setEditable(false);

        jLabel4.setText("Person ID");

        jLabel5.setText("Customer Name");

        nameJTextField.setEditable(false);
        nameJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameJTextFieldActionPerformed(evt);
            }
        });

        jLabel8.setText("Money");

        moneyTextField.setEditable(false);

        jLabel10.setText("Password");

        usernameTextField.setEditable(false);

        jLabel6.setText("Username");

        jLabel9.setText("Account");

        bankaccountJTextField.setEditable(false);

        jButton4.setText("Update Password");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel4)
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jButton1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton3))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(passwordJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(222, 222, 222)
                                            .addComponent(jLabel9)))
                                    .addComponent(jLabel8)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton4)
                                        .addComponent(jLabel7)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bankaccountJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(moneyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(quantityJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                .addContainerGap(182, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton1))
                .addGap(30, 30, 30)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4)
                                            .addComponent(idJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(22, 22, 22))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel7)
                                            .addComponent(quantityJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(nameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(moneyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(bankaccountJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(passwordJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addContainerGap(115, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Customer List", jPanel2);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Unresovled Request");

        jButton6.setText("Submit");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Resovled Request");

        resovledJTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Message", "Id", "Sender", "Receiver", "Statu"
            }
        ));
        jScrollPane4.setViewportView(resovledJTable1);

        requestJTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Message", "Id", "Sender", "Statu"
            }
        ));
        jScrollPane5.setViewportView(requestJTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
                    .addComponent(jButton6)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4))
                .addContainerGap(131, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6)
                .addGap(18, 18, 18)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Withdraw Bitcoin", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(277, 277, 277))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nameJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameJTextFieldActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        int selectedRow = requestJTable.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "You should selected one row");
        } else {
            UploadBitcoinWorkRequest request = (UploadBitcoinWorkRequest) requestJTable.getValueAt(selectedRow, 0);
            request.setReceiver(account);
            request.setStatusString("Authenticated");
            Bitcoin bitcoin = request.getBitcoin();
            boolean authenticate = false;
            for (Network network : ecoSystem.getNetworkList()) {
                for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                    if (enterprise instanceof DepositoryEnterprise) {
                        if (((DepositoryEnterprise) enterprise).getBitcoinList().get(bitcoin) == true) //                        for(Bitcoin bit : ((DepositoryEnterprise) enterprise).getBitcoinList().keySet()){
                        //                            if(bitcoin.getPrivateKey().equals(bit.getPrivateKey())){
                        {
                            authenticate = true;
                        }
                        break;
//                            }
//                        }
                    }
                }
            }
            if (authenticate == true) {
                request.setMessage("True");
                request.getSender().getPerson().getWallet().getBitcoinList().add(bitcoin);
            } else {
                request.setMessage("False");
            }
            populateResovledTable();
            populateUnresovledTable();

        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int row = populateCustomerlistJTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row from the table first.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Person b = (Person) populateCustomerlistJTable.getValueAt(row, 1);
        UserAccount u = null;
        for (UserAccount userAccount : platformEnterprise.getCustomerUserAccountDirectory().getUserAccountList()) {
            if (userAccount.getPerson() == b) {
                u = userAccount;
            }
        }
        idJTextField.setText(String.valueOf(u.getPerson().getId()));
        nameJTextField.setText(u.getPerson().getName());
        usernameTextField.setText(u.getUsername());
        passwordJTextField.setText(u.getPassword());
        quantityJTextField.setText(String.valueOf(u.getPerson().getWallet().getBitcoinList().size()));
        moneyTextField.setText(String.valueOf(u.getPerson().getWallet().getFlat()));
        bankaccountJTextField.setText(String.valueOf(u.getPerson().getBankAccount()));

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (passwordJTextField.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter a new password.");
            return;
        }
        for (UserAccount userAccount : platformEnterprise.getCustomerUserAccountDirectory().getUserAccountList()) {
            if (userAccount.getPerson().getId() == Integer.parseInt(idJTextField.getText())) {
                userAccount.setPassword(passwordJTextField.getText());
                JOptionPane.showMessageDialog(null, "Updated Successfully");
                break;
            }
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int row = populateCustomerlistJTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row from the table first.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Person b = (Person) populateCustomerlistJTable.getValueAt(row, 1);
        for (UserAccount userAccount : platformEnterprise.getCustomerUserAccountDirectory().getUserAccountList()) {
            if (userAccount.getPerson() == b) {
                platformEnterprise.getCustomerUserAccountDirectory().getUserAccountList().remove(userAccount);
                JOptionPane.showMessageDialog(null, "Successfully deleted");
                populateCustomerListTable();
                break;
            }
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        int selectedRow = requestJTable1.getSelectedRow();
        if(selectedRow < 0){
            JOptionPane.showMessageDialog(null, "You have to selected one row");
            return;
        }else{
            WithdrawBitcoinWorkRequest request = (WithdrawBitcoinWorkRequest)requestJTable1.getValueAt(selectedRow, 0);
            request.setReceiver(account);
            request.setStatusString("Resolved");
            request.getSender().getPerson().getLocalBitcoinWallet().add(request.getBitcoin());
            populateUnresovledRequest();
            populateResovledRequest();
        }
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bankaccountJTextField;
    private javax.swing.JTextField idJTextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField moneyTextField;
    private javax.swing.JTextField nameJTextField;
    private javax.swing.JTextField passwordJTextField;
    private javax.swing.JTable populateCustomerlistJTable;
    private javax.swing.JTextField quantityJTextField;
    private javax.swing.JTable requestJTable;
    private javax.swing.JTable requestJTable1;
    private javax.swing.JTable resovledJTable;
    private javax.swing.JTable resovledJTable1;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}
