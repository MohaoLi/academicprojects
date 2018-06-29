/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.Bank.BankAccountant;

import Business.EcoSystem;
import Business.Employee.Person;
import Business.Enterprise.BankEnterprise;
import Business.Enterprise.Enterprise;
import Business.Enterprise.PlatformEnterprise;
import Business.Network.Network;
import Business.Organization.Bank.BankAccountingOrganization;
import Business.UserAccount.UserAccount;
import Business.UserAccount.UserAccountDirectory;
import Business.WorkQueue.RechargeMoneyWorkRequest;
import Business.WorkQueue.WithdrawMoneyWorkRequest;

import Business.WorkQueue.WorkQueue;
import Business.WorkQueue.WorkRequest;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lei
 */
public class BankAccountantWorkAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form BankAccountantWorkAreaJPanel
     */
    JPanel userProcessContainer;
    UserAccount account;
    BankAccountingOrganization bankAccountingOrganization;
    BankEnterprise bankEnterprise;
    ArrayList<Person> peopleList;
    EcoSystem ecoSystem;

    public BankAccountantWorkAreaJPanel(JPanel userProcessContainer, UserAccount account, BankAccountingOrganization bankAccountingOrganization, BankEnterprise bankEnterprise, EcoSystem ecoSystem) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.bankAccountingOrganization = bankAccountingOrganization;
        this.bankEnterprise = bankEnterprise;
        this.peopleList = new ArrayList<>();
        this.ecoSystem = ecoSystem;
        populatetblCustomerList();

        jTabbedPane1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
                int selectedIndex = tabbedPane.getSelectedIndex();
                switch (selectedIndex) {
                    case 0:
                        break;
                    case 1:
                        populateRechargeWorkRequestTable();
                        break;
                    case 2:
                        populateWithdrawWorkRequestTable();
                        break;
                    case 3:
                        populatetblCustomerList();
                        break;
                }
            }
        });

    }

    public void populateRechargeWorkRequestTable() {

        WorkQueue workQueue = bankAccountingOrganization.getWorkQueue();
        DefaultTableModel model = (DefaultTableModel) tblRechargeWorkrequst.getModel();
        model.setRowCount(0);
        for (WorkRequest workRequest : workQueue.getWorkRequestList()) {
            if (workRequest.getMessage().equals("Recharge Money")) {
                Object[] row = new Object[5];
                row[0] = workRequest;
                row[1] = ((RechargeMoneyWorkRequest)workRequest).getMoney();
                row[2] = workRequest.getSender();
                row[3] = workRequest.getReceiver();
                row[4] = workRequest.getStatusString();
                model.addRow(row);
            }
        }

    }

    public void populateWithdrawWorkRequestTable() {

        WorkQueue workQueue = bankAccountingOrganization.getWorkQueue();
        DefaultTableModel model = (DefaultTableModel) tblWithdrawWorkrequst.getModel();
        model.setRowCount(0);
        for (WorkRequest workRequest : workQueue.getWorkRequestList()) {
            if (workRequest.getMessage().equals("Withdraw Money")) {
                Object[] row = new Object[5];
                row[0] = workRequest;
                row[1] =((WithdrawMoneyWorkRequest)workRequest).getMoney();
                row[2] = workRequest.getSender();
                row[3] = workRequest.getReceiver();
                row[4] = workRequest.getStatusString();
                model.addRow(row);
            }
        }

    }

    public void populatetblCustomerList() {
        UserAccountDirectory customerUserAccountDirectory = new UserAccountDirectory();
        for (Network network : ecoSystem.getNetworkList()) {
            for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                if (enterprise instanceof PlatformEnterprise) {
                    customerUserAccountDirectory = ((PlatformEnterprise) enterprise).getCustomerUserAccountDirectory();
                    break;
                }
            }
            if (customerUserAccountDirectory.getUserAccountList().size() > 0) {
                break;
            }
        }

        for (UserAccount userAccount : customerUserAccountDirectory.getUserAccountList()) {
            if (!bankEnterprise.getAccountList().keySet().contains(userAccount.getPerson().getBankAccount())) {
                bankEnterprise.getAccountList().put(userAccount.getPerson().getBankAccount(), 0.0);
            }
        }

        DefaultTableModel model = (DefaultTableModel) tblCustomerList.getModel();
        model.setRowCount(0);
        for (UserAccount userAccount : customerUserAccountDirectory.getUserAccountList()) {
            Object[] row = new Object[4];
            row[0] = userAccount.getPerson().getId();
            row[1] = userAccount.getPerson().getName();
            row[2] = userAccount.getPerson().getBankAccount();
            row[3] = bankEnterprise.getAccountList().get(userAccount.getPerson().getBankAccount());
            model.addRow(row);
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
        jLabel2 = new javax.swing.JLabel();
        accountNoJTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        AmountJTextField = new javax.swing.JTextField();
        submitJButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRechargeWorkrequst = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtAvailableMoney = new javax.swing.JTextField();
        txtWithdrawnMoney = new javax.swing.JTextField();
        btnRechargeProcess = new javax.swing.JButton();
        btnRechargeCheck = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblWithdrawWorkrequst = new javax.swing.JTable();
        btnWithdrawCheck = new javax.swing.JButton();
        txtPlatformMoney = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtWithdrawnMoney1 = new javax.swing.JTextField();
        btnWithdrawProcess = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCustomerList = new javax.swing.JTable();

        setBackground(new java.awt.Color(0, 102, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Bank Accountant Work Area");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Bank Account");

        jLabel4.setText("Amount");

        submitJButton.setText("Submit");
        submitJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitJButtonActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Deposit");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(381, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(520, 520, 520))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(538, 538, 538)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(accountNoJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(AmountJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(293, 293, 293))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(393, 393, 393)
                .addComponent(submitJButton)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(accountNoJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(AmountJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80)
                .addComponent(submitJButton)
                .addGap(133, 133, 133))
        );

        jTabbedPane1.addTab("Deposit", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblRechargeWorkrequst.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Message", "Amount", "Sender", "Receiver", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblRechargeWorkrequst);
        if (tblRechargeWorkrequst.getColumnModel().getColumnCount() > 0) {
            tblRechargeWorkrequst.getColumnModel().getColumn(0).setResizable(false);
            tblRechargeWorkrequst.getColumnModel().getColumn(2).setResizable(false);
            tblRechargeWorkrequst.getColumnModel().getColumn(3).setResizable(false);
            tblRechargeWorkrequst.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel3.setText("Avaiable Money: ");

        jLabel5.setText("Recharged Money: ");

        txtAvailableMoney.setEnabled(false);

        txtWithdrawnMoney.setEnabled(false);

        btnRechargeProcess.setText("Process");
        btnRechargeProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRechargeProcessActionPerformed(evt);
            }
        });

        btnRechargeCheck.setText("Check");
        btnRechargeCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRechargeCheckActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setText("Recharge");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(454, 454, 454))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(333, 333, 333)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3))
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAvailableMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtWithdrawnMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(230, 230, 230)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRechargeProcess)
                    .addComponent(btnRechargeCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(234, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRechargeCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtAvailableMoney, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtWithdrawnMoney, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnRechargeProcess)
                .addGap(118, 118, 118))
        );

        jTabbedPane1.addTab("Recharge", jPanel2);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        tblWithdrawWorkrequst.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Message", "Amount", "Sender", "Receiver", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblWithdrawWorkrequst);
        if (tblWithdrawWorkrequst.getColumnModel().getColumnCount() > 0) {
            tblWithdrawWorkrequst.getColumnModel().getColumn(0).setResizable(false);
            tblWithdrawWorkrequst.getColumnModel().getColumn(2).setResizable(false);
            tblWithdrawWorkrequst.getColumnModel().getColumn(3).setResizable(false);
            tblWithdrawWorkrequst.getColumnModel().getColumn(4).setResizable(false);
        }

        btnWithdrawCheck.setText("Check");
        btnWithdrawCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWithdrawCheckActionPerformed(evt);
            }
        });

        txtPlatformMoney.setEnabled(false);

        jLabel6.setText("Platform Account Money: ");

        jLabel7.setText("Withdrawn Money: ");

        txtWithdrawnMoney1.setEnabled(false);

        btnWithdrawProcess.setText("Process");
        btnWithdrawProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWithdrawProcessActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Withdraw");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(449, 449, 449)
                        .addComponent(jLabel10))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnWithdrawCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(73, 73, 73)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7))
                                    .addGap(32, 32, 32)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtPlatformMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtWithdrawnMoney1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(btnWithdrawProcess))))
                .addGap(0, 234, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel10)
                .addGap(46, 46, 46)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnWithdrawCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPlatformMoney, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtWithdrawnMoney1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(btnWithdrawProcess)
                .addGap(71, 71, 71))
        );

        jTabbedPane1.addTab("Withdraw", jPanel4);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tblCustomerList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Customer Name", "Bank Account", "Available Money"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblCustomerList);
        if (tblCustomerList.getColumnModel().getColumnCount() > 0) {
            tblCustomerList.getColumnModel().getColumn(0).setResizable(false);
            tblCustomerList.getColumnModel().getColumn(1).setResizable(false);
            tblCustomerList.getColumnModel().getColumn(2).setResizable(false);
            tblCustomerList.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(168, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(219, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Customer List", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(331, 331, 331))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void submitJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitJButtonActionPerformed
        // TODO add your handling code here:
        if(!isNumeric(accountNoJTextField.getText().trim()) || accountNoJTextField.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "The account number must be all digits.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (Integer.parseInt(accountNoJTextField.getText().trim()) <= 0){
            JOptionPane.showMessageDialog(null, "Please enter correct account number.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        } else if(!isNumeric(AmountJTextField.getText().trim()) || AmountJTextField.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Please enter correct money amount.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        } else if(Integer.parseInt(AmountJTextField.getText().trim()) <= 0){
            JOptionPane.showMessageDialog(null, "Money amount must be equal or greater than zero.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        UserAccount uac = null;
        //Deposit Money
        for (Network network : ecoSystem.getNetworkList()) {
            for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                if (enterprise instanceof PlatformEnterprise) {
                    PlatformEnterprise platformEnterprise = (PlatformEnterprise) enterprise;
                    for (UserAccount userAccount : platformEnterprise.getCustomerUserAccountDirectory().getUserAccountList()) {
                        if (userAccount.getPerson().getBankAccount().equals(Integer.parseInt(accountNoJTextField.getText()))) {
                            uac = userAccount;
                            bankEnterprise.getAccountList().put(Integer.parseInt(accountNoJTextField.getText()), Double.parseDouble(AmountJTextField.getText()));
                            break;
                        }
                    }
                }
            }
        }
        if (uac == null) {
            JOptionPane.showMessageDialog(null, "Sorry, we can not find the account.");
        } else {
            accountNoJTextField.setText("");
            AmountJTextField.setText("");
            JOptionPane.showMessageDialog(null, "Successfully deposit money.");
        }
    }//GEN-LAST:event_submitJButtonActionPerformed

    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            //System.out.println(str.charAt(i));
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    private void btnRechargeCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRechargeCheckActionPerformed
        // TODO add your handling code here:
        int row = tblRechargeWorkrequst.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row from the table first.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        RechargeMoneyWorkRequest rechargeMoneyWorkRequest = (RechargeMoneyWorkRequest) tblRechargeWorkrequst.getValueAt(row, 0);
        txtAvailableMoney.setText(String.valueOf(bankEnterprise.getAccountList().get(rechargeMoneyWorkRequest.getBankaccount())));
        txtWithdrawnMoney.setText(String.valueOf(rechargeMoneyWorkRequest.getMoney()));
    }//GEN-LAST:event_btnRechargeCheckActionPerformed

    private void btnRechargeProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRechargeProcessActionPerformed
        // TODO add your handling code here:
        int row = tblRechargeWorkrequst.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row from the table first.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        RechargeMoneyWorkRequest rechargeMoneyWorkRequest = (RechargeMoneyWorkRequest) tblRechargeWorkrequst.getValueAt(row, 0);
        double avail = bankEnterprise.getAccountList().get(rechargeMoneyWorkRequest.getBankaccount());
        double recharge = rechargeMoneyWorkRequest.getMoney();
        if (rechargeMoneyWorkRequest.getReceiver() != null) {
            JOptionPane.showMessageDialog(null, "This work request was already processed.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (avail < recharge) {
            rechargeMoneyWorkRequest.setReceiver(account);
            rechargeMoneyWorkRequest.setResolveDate(new Date());
            rechargeMoneyWorkRequest.setStatusString("Rejected");
            JOptionPane.showMessageDialog(null, "The amount of recharged money is greater than the available money in this customer's account.", "Warning", JOptionPane.WARNING_MESSAGE);
            txtAvailableMoney.setText("");
            txtWithdrawnMoney.setText("");
            populateRechargeWorkRequestTable();
        } else {
            double change = avail - recharge;
            bankEnterprise.getAccountList().put(rechargeMoneyWorkRequest.getBankaccount(), change);

            PlatformEnterprise platformEnterprise = null;

            for (Network network : ecoSystem.getNetworkList()) {
                for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                    if (enterprise instanceof PlatformEnterprise) {
                        platformEnterprise = (PlatformEnterprise) enterprise;
                        for (UserAccount userAccount : platformEnterprise.getCustomerUserAccountDirectory().getUserAccountList()) {
                            if (userAccount.getPerson().getBankAccount().equals(rechargeMoneyWorkRequest.getBankaccount())) {
                                userAccount.getPerson().getWallet().setFlat(recharge + userAccount.getPerson().getWallet().getFlat());
                                rechargeMoneyWorkRequest.setReceiver(account);
                                rechargeMoneyWorkRequest.setResolveDate(new Date());
                                rechargeMoneyWorkRequest.setStatusString("Completed");
                                txtAvailableMoney.setText("");
                                txtWithdrawnMoney.setText("");
                                populateRechargeWorkRequestTable();
                                JOptionPane.showMessageDialog(null, "Recharged successfully", "Information", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            }
                        }
                    }
                    if (platformEnterprise != null) {
                        break;
                    }
                }
                if (platformEnterprise != null) {
                    break;
                }
            }
        }
    }//GEN-LAST:event_btnRechargeProcessActionPerformed

    private void btnWithdrawCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWithdrawCheckActionPerformed
        // TODO add your handling code here:
        int row = tblWithdrawWorkrequst.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row from the table first.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        WithdrawMoneyWorkRequest withdrawMoneyWorkRequest = (WithdrawMoneyWorkRequest) tblWithdrawWorkrequst.getValueAt(row, 0);
        UserAccountDirectory customerUserAccountDirectory = new UserAccountDirectory();
        for (Network network : ecoSystem.getNetworkList()) {
            for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                if (enterprise instanceof PlatformEnterprise) {
                    customerUserAccountDirectory = ((PlatformEnterprise) enterprise).getCustomerUserAccountDirectory();
                    break;
                }
            }
            if (customerUserAccountDirectory.getUserAccountList().size() > 0) {
                break;
            }
        }
        for (UserAccount a : customerUserAccountDirectory.getUserAccountList()) {
            if (a.getPerson().getBankAccount().equals(withdrawMoneyWorkRequest.getBankaccount())) {
                txtPlatformMoney.setText(String.valueOf(a.getPerson().getWallet().getFlat()));
                break;
            }
        }
        txtWithdrawnMoney1.setText(String.valueOf(withdrawMoneyWorkRequest.getMoney()));
    }//GEN-LAST:event_btnWithdrawCheckActionPerformed

    private void btnWithdrawProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWithdrawProcessActionPerformed
        // TODO add your handling code here:
        int row = tblWithdrawWorkrequst.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row from the table first.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        WithdrawMoneyWorkRequest withdrawMoneyWorkRequest1 = (WithdrawMoneyWorkRequest) tblWithdrawWorkrequst.getValueAt(row, 0);
        
        UserAccountDirectory customerUserAccountDirectory = new UserAccountDirectory();
        for (Network network : ecoSystem.getNetworkList()) {
            for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                if (enterprise instanceof PlatformEnterprise) {
                    customerUserAccountDirectory = ((PlatformEnterprise) enterprise).getCustomerUserAccountDirectory();
                    break;
                }
            }
            if (customerUserAccountDirectory.getUserAccountList().size() > 0) {
                break;
            }
        }
        
        double platformMon = 0.0;        
        for (UserAccount a : customerUserAccountDirectory.getUserAccountList()) {
            if (a.getPerson().getBankAccount().equals(withdrawMoneyWorkRequest1.getBankaccount())) {
                platformMon = a.getPerson().getWallet().getFlat();
                break;
            }
        }
        
        double withd = withdrawMoneyWorkRequest1.getMoney();
        
        if (withdrawMoneyWorkRequest1.getReceiver() != null) {
            JOptionPane.showMessageDialog(null, "This work request was already processed.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (platformMon < withd) {
            withdrawMoneyWorkRequest1.setReceiver(account);
            withdrawMoneyWorkRequest1.setResolveDate(new Date());
            withdrawMoneyWorkRequest1.setStatusString("Rejected");
            txtPlatformMoney.setText("");
            txtWithdrawnMoney1.setText("");
            populateWithdrawWorkRequestTable();
            JOptionPane.showMessageDialog(null, "The amount of withdrawn money is greater than the available money in this customer's account.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            double changeAccount = bankEnterprise.getAccountList().get(withdrawMoneyWorkRequest1.getBankaccount()) + withd;
            double change = platformMon;
            
            bankEnterprise.getAccountList().put(withdrawMoneyWorkRequest1.getBankaccount(), changeAccount);

            PlatformEnterprise platformEnterprise = null;

            for (Network network : ecoSystem.getNetworkList()) {
                for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                    if (enterprise instanceof PlatformEnterprise) {
                        platformEnterprise = (PlatformEnterprise) enterprise;
                        for (UserAccount userAccount : platformEnterprise.getCustomerUserAccountDirectory().getUserAccountList()) {
                            if (userAccount.getPerson().getBankAccount().equals(withdrawMoneyWorkRequest1.getBankaccount())) {
                                userAccount.getPerson().getWallet().setFlat(change);
                                withdrawMoneyWorkRequest1.setReceiver(account);
                                withdrawMoneyWorkRequest1.setResolveDate(new Date());
                                withdrawMoneyWorkRequest1.setStatusString("Completed");
                                txtAvailableMoney.setText("");
                                txtWithdrawnMoney.setText("");
                                populateWithdrawWorkRequestTable();
                                JOptionPane.showMessageDialog(null, "Recharged successfully", "Information", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            }
                        }
                    }
                    if (platformEnterprise != null) {
                        break;
                    }
                }
                if (platformEnterprise != null) {
                    break;
                }
            }
        }
    }//GEN-LAST:event_btnWithdrawProcessActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AmountJTextField;
    private javax.swing.JTextField accountNoJTextField;
    private javax.swing.JButton btnRechargeCheck;
    private javax.swing.JButton btnRechargeProcess;
    private javax.swing.JButton btnWithdrawCheck;
    private javax.swing.JButton btnWithdrawProcess;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton submitJButton;
    private javax.swing.JTable tblCustomerList;
    private javax.swing.JTable tblRechargeWorkrequst;
    private javax.swing.JTable tblWithdrawWorkrequst;
    private javax.swing.JTextField txtAvailableMoney;
    private javax.swing.JTextField txtPlatformMoney;
    private javax.swing.JTextField txtWithdrawnMoney;
    private javax.swing.JTextField txtWithdrawnMoney1;
    // End of variables declaration//GEN-END:variables
}
