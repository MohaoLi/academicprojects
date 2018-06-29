/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.Platform.Customer;

import Business.EcoSystem;
import Business.Employee.Employee;
import Business.Employee.Person;
import Business.Enterprise.BankEnterprise;
import Business.Enterprise.DepositoryEnterprise;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Bank.BankAccountingOrganization;
import Business.Organization.BitcoinPlatform.BrokerOrganization;
import Business.Organization.BitcoinPlatform.CustomerManagementOrganization;
import Business.Organization.Organization;
import Business.Other.Bitcoin;
import Business.Other.ExchangeHistory;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.AssignBrokerWorkRequest;
import Business.WorkQueue.BrokerSellWorkRequest;
import Business.WorkQueue.RechargeMoneyWorkRequest;
import Business.WorkQueue.UploadBitcoinWorkRequest;
import Business.WorkQueue.WithdrawBitcoinWorkRequest;
import Business.WorkQueue.WithdrawMoneyWorkRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import userInterface.Depository.Custodian.CustodianBitcoinDetailJPanel;

/**
 *
 * @author Lei
 */
public class CustomerWorkAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form CustomerRelatedJPanel
     */
    JPanel userProcessContainer;
    UserAccount account;
    Organization organization;
    Enterprise enterprise;
    EcoSystem ecoSystem;
    double fee;

    public CustomerWorkAreaJPanel(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem ecoSystem) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.organization = organization;
        this.enterprise = enterprise;
        this.ecoSystem = ecoSystem;
        passwordJTextField.setEditable(false);
        passwordJTextField.setEnabled(false);
        saveJButton.setEnabled(false);
        populateBasicInformation();
        

        jTabbedPane1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
                int selectedIndex = tabbedPane.getSelectedIndex();
                switch (selectedIndex) {
                    case 0:
                        populateBasicInformation();
                        break;
                    case 1:
                        populateAssetsManagement();
                        break;
                    case 2:
                        populateRechargeTable();
                        break;
                    case 3:
                        populateWithdrawTable();
                        break;
                    case 4:
                        populateLocalWalletTable();
                        break;
                    case 5:
                        populateEntrust();
                        break;
                    case 6:
                        populateOrderhistoryTable();
                        break;
                    case 7:
                        populateWithdrawBitcoin();
                        populateWithdrawBitcoinrequest();
                        break;
                        

                }

            }
        });
    }

    public void populateBasicInformation() {
        Person person = account.getPerson();
        nameJTextField.setText(person.getName());
        customerJTextField.setText(String.valueOf(person.getId()));
        bankaccountTextField.setText(String.valueOf(person.getBankAccount()));
        usernameJTextField.setText(account.getUsername());
        passwordJTextField.setEditable(false);
        passwordJTextField.setText(account.getPassword());

    }

    public void populateAssetsManagement() {
        Person person = account.getPerson();
        DecimalFormat df = new DecimalFormat("0.00");
        availableBitcoinJTextField.setText(String.valueOf(person.getWallet().getBitcoinList().size()));
        UserAccount broker_account = null;
        for (WorkRequest request : account.getWorkQueue().getWorkRequestList()) {
            if (request instanceof AssignBrokerWorkRequest) {
                if (request.getReceiver() != null) {
                    broker_account = request.getReceiver();
                }
            }
        }
        int pending = 0;
        if (broker_account != null) {
            for (WorkRequest request : broker_account.getWorkQueue().getWorkRequestList()) {
                if (request instanceof BrokerSellWorkRequest) {
                    if (request.getStatusString().equals("Pending") && ((BrokerSellWorkRequest) request).getCustomer_sell() == account.getPerson()) {
                        pending += ((BrokerSellWorkRequest) request).getBitcoinlist().size();
                    }
                }
            }
        }
        pendingBitcoinJTextField.setText(String.valueOf(pending));
        availableMoneyJTextField.setText(String.valueOf(df.format(account.getPerson().getWallet().getFlat())));
        double total = (person.getWallet().getBitcoinList().size() + pending) * ecoSystem.getBitcoinPrice() + person.getWallet().getFlat();
        totalAssetsJTextField.setText(String.valueOf(df.format(total)));

        int input_money = 0;
        for (WorkRequest request : account.getWorkQueue().getWorkRequestList()) {
            if (request instanceof UploadBitcoinWorkRequest) {
                if (request.getMessage().equals("True")) {
                    input_money += ((UploadBitcoinWorkRequest) request).getBitcoin_price();
                }
            }
        }
        for (WorkRequest request : account.getWorkQueue().getWorkRequestList()) {
            if (request instanceof RechargeMoneyWorkRequest) {
                if (request.getStatusString().equals("Completed")) {
                    input_money += ((RechargeMoneyWorkRequest) request).getMoney();
                }
            }
        }

        for (WorkRequest request : account.getWorkQueue().getWorkRequestList()) {
            if (request instanceof WithdrawMoneyWorkRequest) {
                input_money -= ((WithdrawMoneyWorkRequest) request).getMoney();
            }
        }
        
        for(WorkRequest request : account.getWorkQueue().getWorkRequestList()){
            if(request instanceof WithdrawBitcoinWorkRequest){
//                int size = ((WithdrawBitcoinWorkRequest) request).getBitcoin().getExchangeHistoryList().size();
//                if(size == 0){
//                    continue;
//                }else{
                input_money -= ((WithdrawBitcoinWorkRequest) request).getPrice();
                }
            
        }
        
        double profits = total - input_money;
        profitsJTextField.setText(String.valueOf(df.format(profits)));

    }

    private void populateLocalWalletTable() {
        DefaultTableModel model = (DefaultTableModel) localWalletJTable.getModel();

        model.setRowCount(0);
        Person person = account.getPerson();
        for (Bitcoin bitcoin : person.getLocalBitcoinWallet()) {
            Object[] row = new Object[3];
            row[0] = bitcoin;
            row[1] = bitcoin.getPrivateKey();
            row[2] = bitcoin.getBirthday();
            model.addRow(row);
        }

    }

    private void populateEntrust() {
        DefaultTableModel model = (DefaultTableModel) entrustJTable.getModel();
        model.setRowCount(0);
        for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
            if (organization instanceof BrokerOrganization) {
                for (Employee employee : organization.getEmployeeDirectory().getEmployeeList()) {
                    Object[] row = new Object[2];
                    row[0] = employee;
                    model.addRow(row);
                }
            }
        }
        for (WorkRequest request : account.getWorkQueue().getWorkRequestList()) {
            if (request instanceof AssignBrokerWorkRequest) {
                if (request.getReceiver() != null) {
                    brokerJTextField.setText(request.getReceiver().getPerson().getName());
                }
            }
        }

    }

    private void populateWithdrawTable() {
        DefaultTableModel model = (DefaultTableModel) withdrawJTable.getModel();
        model.setRowCount(0);
        for (WorkRequest request : account.getWorkQueue().getWorkRequestList()) {
            if (request instanceof WithdrawMoneyWorkRequest) {
                Object[] row = new Object[5];
                row[0] = request;
                row[1] = ((WithdrawMoneyWorkRequest) request).getMoney();
                row[2] = request.getRequestDate();
                row[3] = request.getResolveDate() == null ? null : request.getResolveDate();
                row[4] = request.getStatusString();
                model.addRow(row);

            }
        }
    }

    private void populateRechargeTable() {
        DefaultTableModel model = (DefaultTableModel) RechargeTable1.getModel();
        model.setRowCount(0);
        for (WorkRequest request : account.getWorkQueue().getWorkRequestList()) {
            if (request instanceof RechargeMoneyWorkRequest) {
                Object[] row = new Object[5];
                row[0] = request;
                row[1] = ((RechargeMoneyWorkRequest) request).getMoney();
                row[2] = request.getRequestDate();
                row[3] = request.getResolveDate() == null ? null : request.getResolveDate();
                row[4] = request.getStatusString();
                model.addRow(row);

            }
        }
    }

    private void populateOrderhistoryTable() {
        UserAccount broker_account = null;
        DefaultTableModel model = (DefaultTableModel) orderHistoryJTable.getModel();
        model.setRowCount(0);
        for (Network network : ecoSystem.getNetworkList()) {
            for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                if (enterprise instanceof DepositoryEnterprise) {
                    for (Bitcoin bitcoin : ((DepositoryEnterprise) enterprise).getBitcoinList().keySet()) {
                        for (ExchangeHistory exchangeHistory : bitcoin.getExchangeHistoryList()) {
                            if (exchangeHistory.getBuyer() == account.getPerson()) {
                                Object[] row = new Object[4];
                                row[0] = bitcoin;
                                row[1] = "Buy";
                                row[2] = exchangeHistory.getExchangePrize();
                                fee += exchangeHistory.getExchangePrize() * (0.002);
                                row[3] = exchangeHistory;
                                model.addRow(row);
                            }
                            if (exchangeHistory.getSeller() == account.getPerson()) {
                                Object[] row = new Object[4];
                                row[0] = bitcoin;
                                row[1] = "Sell";
                                row[2] = exchangeHistory.getExchangePrize();
                                fee += exchangeHistory.getExchangePrize() * (0.003);
                                row[3] = exchangeHistory;
                                model.addRow(row);
                            }

                        }
                    }
                }
            }
        }
        DecimalFormat df = new DecimalFormat("0.00");
        transactionfeeJTextField.setText(String.valueOf(df.format(fee)));
    }

    private void populateWithdrawBitcoin() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (Bitcoin bitcoin : account.getPerson().getWallet().getBitcoinList()) {
            Object[] row = new Object[3];
            row[0] = bitcoin;
            row[1] = bitcoin.getPrivateKey();
            row[2] = bitcoin.getBirthday();
            model.addRow(row);
        }
    }

    private void populateWithdrawBitcoinrequest() {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        for (WorkRequest request : account.getWorkQueue().getWorkRequestList()) {
            if (request instanceof WithdrawBitcoinWorkRequest) {
                Object[] row = new Object[5];
                row[0] = request.getMessage();
                row[1] = ((WithdrawBitcoinWorkRequest) request).getBitcoin().getSerialNum();
                row[2] = request.getSender();
                row[3] = request.getReceiver() != null ? request.getReceiver() : null;
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        basicInfoJPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        usernameJTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        customerJTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        passwordJTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        bankaccountTextField = new javax.swing.JTextField();
        changePassJButton = new javax.swing.JButton();
        saveJButton = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        nameJTextField = new javax.swing.JTextField();
        assetsManagementJPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        availableBitcoinJTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        pendingBitcoinJTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        availableMoneyJTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        totalAssetsJTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        profitsJTextField = new javax.swing.JTextField();
        RechargeJPanel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        AmountJTextField = new javax.swing.JTextField();
        rechargeJButton1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        RechargeTable1 = new javax.swing.JTable();
        WithdrawJPanel = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        AmountJTextField1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        withdrawJButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        withdrawJTable = new javax.swing.JTable();
        localWalletJPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        localWalletJTable = new javax.swing.JTable();
        showJButton = new javax.swing.JButton();
        showJButton1 = new javax.swing.JButton();
        entrustJPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        entrustJTable = new javax.swing.JTable();
        selectJButton = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        brokerJTextField = new javax.swing.JTextField();
        orderHistoryJPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        orderHistoryJTable = new javax.swing.JTable();
        showDetailJButton = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        serialnumTextField = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        typeTextField = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        buyerTextField = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        sellerTextField = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        priceTextField = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        dateTextField = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        transactionfeeJTextField = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 102, 255));

        basicInfoJPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("User Name");

        usernameJTextField.setEditable(false);

        jLabel4.setText("Person ID");

        customerJTextField.setEditable(false);

        jLabel5.setText("Password");

        jLabel10.setText("Bank Account");

        bankaccountTextField.setEditable(false);
        bankaccountTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bankaccountTextFieldActionPerformed(evt);
            }
        });

        changePassJButton.setText("Change Password");
        changePassJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePassJButtonActionPerformed(evt);
            }
        });

        saveJButton.setText("Save");
        saveJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveJButtonActionPerformed(evt);
            }
        });

        jLabel11.setText("Name");

        nameJTextField.setEditable(false);

        javax.swing.GroupLayout basicInfoJPanelLayout = new javax.swing.GroupLayout(basicInfoJPanel);
        basicInfoJPanel.setLayout(basicInfoJPanelLayout);
        basicInfoJPanelLayout.setHorizontalGroup(
            basicInfoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basicInfoJPanelLayout.createSequentialGroup()
                .addGroup(basicInfoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(basicInfoJPanelLayout.createSequentialGroup()
                        .addGap(282, 282, 282)
                        .addGroup(basicInfoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(basicInfoJPanelLayout.createSequentialGroup()
                                .addGroup(basicInfoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5))
                                .addGap(72, 72, 72)
                                .addGroup(basicInfoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(usernameJTextField)
                                    .addComponent(customerJTextField)
                                    .addComponent(passwordJTextField)
                                    .addComponent(bankaccountTextField)
                                    .addComponent(nameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(basicInfoJPanelLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(changePassJButton))))
                    .addGroup(basicInfoJPanelLayout.createSequentialGroup()
                        .addGap(572, 572, 572)
                        .addComponent(saveJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(324, Short.MAX_VALUE))
        );
        basicInfoJPanelLayout.setVerticalGroup(
            basicInfoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, basicInfoJPanelLayout.createSequentialGroup()
                .addContainerGap(82, Short.MAX_VALUE)
                .addGroup(basicInfoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(nameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(basicInfoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(28, 28, 28)
                .addGroup(basicInfoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bankaccountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(37, 37, 37)
                .addGroup(basicInfoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(usernameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(basicInfoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(34, 34, 34)
                .addGroup(basicInfoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(changePassJButton)
                    .addComponent(saveJButton))
                .addGap(75, 75, 75))
        );

        jTabbedPane1.addTab("Basic Information", basicInfoJPanel);

        assetsManagementJPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setText("Available Bitcoin");

        availableBitcoinJTextField.setEditable(false);

        jLabel7.setText("Pending Bitcoin");

        pendingBitcoinJTextField.setEditable(false);

        jLabel8.setText("Available Money");

        availableMoneyJTextField.setEditable(false);

        jLabel9.setText("Total Assets($)");

        totalAssetsJTextField.setEditable(false);

        jLabel3.setText("Profits");

        profitsJTextField.setEditable(false);

        javax.swing.GroupLayout assetsManagementJPanelLayout = new javax.swing.GroupLayout(assetsManagementJPanel);
        assetsManagementJPanel.setLayout(assetsManagementJPanelLayout);
        assetsManagementJPanelLayout.setHorizontalGroup(
            assetsManagementJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assetsManagementJPanelLayout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addGroup(assetsManagementJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel3))
                .addGap(93, 93, 93)
                .addGroup(assetsManagementJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pendingBitcoinJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                    .addComponent(availableBitcoinJTextField)
                    .addComponent(availableMoneyJTextField)
                    .addComponent(totalAssetsJTextField)
                    .addComponent(profitsJTextField))
                .addContainerGap(469, Short.MAX_VALUE))
        );
        assetsManagementJPanelLayout.setVerticalGroup(
            assetsManagementJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assetsManagementJPanelLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(assetsManagementJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(availableBitcoinJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(assetsManagementJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pendingBitcoinJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(43, 43, 43)
                .addGroup(assetsManagementJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(availableMoneyJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(45, 45, 45)
                .addGroup(assetsManagementJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalAssetsJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(50, 50, 50)
                .addGroup(assetsManagementJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(profitsJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(111, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Assets Management", assetsManagementJPanel);

        RechargeJPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel13.setText("Recharge Money");

        jLabel14.setText("Amount");

        rechargeJButton1.setText("Submit");
        rechargeJButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechargeJButton1ActionPerformed(evt);
            }
        });

        RechargeTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Message", "Amount", "Requset Date", "Resolved Date", "Statu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(RechargeTable1);

        javax.swing.GroupLayout RechargeJPanelLayout = new javax.swing.GroupLayout(RechargeJPanel);
        RechargeJPanel.setLayout(RechargeJPanelLayout);
        RechargeJPanelLayout.setHorizontalGroup(
            RechargeJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RechargeJPanelLayout.createSequentialGroup()
                .addGap(336, 336, 336)
                .addComponent(jLabel14)
                .addGap(34, 34, 34)
                .addComponent(AmountJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(rechargeJButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RechargeJPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(259, 259, 259))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RechargeJPanelLayout.createSequentialGroup()
                .addContainerGap(202, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145))
        );
        RechargeJPanelLayout.setVerticalGroup(
            RechargeJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RechargeJPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(RechargeJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RechargeJPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rechargeJButton1)
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(RechargeJPanelLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(RechargeJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(AmountJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(104, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Recharge Money", RechargeJPanel);

        WithdrawJPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel15.setText("Withdraw Money");

        jLabel16.setText("Amount");

        withdrawJButton.setText("Submit");
        withdrawJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                withdrawJButtonActionPerformed(evt);
            }
        });

        withdrawJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Message", "Amount", "Request Date", "Resolved Date", "Statu"
            }
        ));
        jScrollPane4.setViewportView(withdrawJTable);

        javax.swing.GroupLayout WithdrawJPanelLayout = new javax.swing.GroupLayout(WithdrawJPanel);
        WithdrawJPanel.setLayout(WithdrawJPanelLayout);
        WithdrawJPanelLayout.setHorizontalGroup(
            WithdrawJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WithdrawJPanelLayout.createSequentialGroup()
                .addGap(357, 357, 357)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(AmountJTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(withdrawJButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, WithdrawJPanelLayout.createSequentialGroup()
                .addContainerGap(189, Short.MAX_VALUE)
                .addGroup(WithdrawJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, WithdrawJPanelLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(251, 251, 251))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, WithdrawJPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(161, 161, 161))))
        );
        WithdrawJPanelLayout.setVerticalGroup(
            WithdrawJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WithdrawJPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(WithdrawJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(WithdrawJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(AmountJTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(withdrawJButton))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Withdraw Money", WithdrawJPanel);

        localWalletJPanel.setBackground(new java.awt.Color(255, 255, 255));

        localWalletJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bitcoin Serial No.", "Private Key", "Birthday"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(localWalletJTable);
        if (localWalletJTable.getColumnModel().getColumnCount() > 0) {
            localWalletJTable.getColumnModel().getColumn(1).setResizable(false);
            localWalletJTable.getColumnModel().getColumn(2).setResizable(false);
        }

        showJButton.setText("Show Detail");
        showJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showJButtonActionPerformed(evt);
            }
        });

        showJButton1.setText("Upload");
        showJButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showJButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout localWalletJPanelLayout = new javax.swing.GroupLayout(localWalletJPanel);
        localWalletJPanel.setLayout(localWalletJPanelLayout);
        localWalletJPanelLayout.setHorizontalGroup(
            localWalletJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, localWalletJPanelLayout.createSequentialGroup()
                .addContainerGap(101, Short.MAX_VALUE)
                .addGroup(localWalletJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(localWalletJPanelLayout.createSequentialGroup()
                        .addComponent(showJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(showJButton1))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(99, 99, 99))
        );
        localWalletJPanelLayout.setVerticalGroup(
            localWalletJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, localWalletJPanelLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(localWalletJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showJButton1)
                    .addComponent(showJButton))
                .addGap(141, 141, 141))
        );

        jTabbedPane1.addTab("Local Wallet", localWalletJPanel);

        entrustJPanel.setBackground(new java.awt.Color(255, 255, 255));

        entrustJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Broker Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(entrustJTable);
        if (entrustJTable.getColumnModel().getColumnCount() > 0) {
            entrustJTable.getColumnModel().getColumn(0).setResizable(false);
        }

        selectJButton.setText("Send Request");
        selectJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectJButtonActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel12.setText("Broker");

        brokerJTextField.setEditable(false);
        brokerJTextField.setEnabled(false);

        javax.swing.GroupLayout entrustJPanelLayout = new javax.swing.GroupLayout(entrustJPanel);
        entrustJPanel.setLayout(entrustJPanelLayout);
        entrustJPanelLayout.setHorizontalGroup(
            entrustJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entrustJPanelLayout.createSequentialGroup()
                .addGap(196, 196, 196)
                .addGroup(entrustJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(entrustJPanelLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(brokerJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectJButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(185, Short.MAX_VALUE))
        );
        entrustJPanelLayout.setVerticalGroup(
            entrustJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entrustJPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(entrustJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(brokerJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(selectJButton)
                .addContainerGap(177, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Entrust", entrustJPanel);

        orderHistoryJPanel.setBackground(new java.awt.Color(255, 255, 255));

        orderHistoryJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bitcoin Serial No.", "Type", "Transaction Price", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(orderHistoryJTable);
        if (orderHistoryJTable.getColumnModel().getColumnCount() > 0) {
            orderHistoryJTable.getColumnModel().getColumn(0).setResizable(false);
            orderHistoryJTable.getColumnModel().getColumn(1).setResizable(false);
            orderHistoryJTable.getColumnModel().getColumn(2).setResizable(false);
        }

        showDetailJButton.setText("Show Detail");
        showDetailJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showDetailJButtonActionPerformed(evt);
            }
        });

        jLabel17.setText("Serial Num");

        serialnumTextField.setEditable(false);

        jLabel18.setText("Type");

        typeTextField.setEditable(false);

        jLabel19.setText("Buyer");

        buyerTextField.setEditable(false);

        jLabel20.setText("Seller");

        sellerTextField.setEditable(false);

        jLabel21.setText("Price");

        priceTextField.setEditable(false);

        jLabel22.setText("Date");

        dateTextField.setEditable(false);

        jLabel23.setText("Transaction Fee");

        transactionfeeJTextField.setEditable(false);
        transactionfeeJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transactionfeeJTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout orderHistoryJPanelLayout = new javax.swing.GroupLayout(orderHistoryJPanel);
        orderHistoryJPanel.setLayout(orderHistoryJPanelLayout);
        orderHistoryJPanelLayout.setHorizontalGroup(
            orderHistoryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, orderHistoryJPanelLayout.createSequentialGroup()
                .addGroup(orderHistoryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(orderHistoryJPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel23)
                        .addGap(18, 18, 18)
                        .addComponent(transactionfeeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(orderHistoryJPanelLayout.createSequentialGroup()
                        .addGap(0, 97, Short.MAX_VALUE)
                        .addGroup(orderHistoryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(showDetailJButton)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 814, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(orderHistoryJPanelLayout.createSequentialGroup()
                                .addGroup(orderHistoryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(orderHistoryJPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(serialnumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(orderHistoryJPanelLayout.createSequentialGroup()
                                        .addGroup(orderHistoryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(orderHistoryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(typeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(buyerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(sellerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(151, 151, 151)
                                .addGroup(orderHistoryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(orderHistoryJPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(orderHistoryJPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(87, 87, 87))
        );
        orderHistoryJPanelLayout.setVerticalGroup(
            orderHistoryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderHistoryJPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(orderHistoryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(transactionfeeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(showDetailJButton)
                .addGap(25, 25, 25)
                .addGroup(orderHistoryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(serialnumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(orderHistoryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(orderHistoryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buyerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(orderHistoryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sellerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Order History", orderHistoryJPanel);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Series Number", "Private Key", "Birthday"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
        }

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Message", "Series Number", "Sender", "Receiver", "Statu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(4).setResizable(false);
        }

        jButton1.setText("Withdraw");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(241, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
                        .addComponent(jScrollPane7))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(158, 158, 158))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Withdraw Bitcoin", jPanel1);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Customer Related Information");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(318, 318, 318))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bankaccountTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bankaccountTextFieldActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_bankaccountTextFieldActionPerformed

    private void changePassJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePassJButtonActionPerformed
        // TODO add your handling code here:
        passwordJTextField.setEditable(true);
        passwordJTextField.setEnabled(true);
        changePassJButton.setEnabled(false);
        saveJButton.setEnabled(true);
    }//GEN-LAST:event_changePassJButtonActionPerformed

    private void saveJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveJButtonActionPerformed
        // TODO add your handling code here:
        if (passwordJTextField.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "You have to input your password");
            return;
        }
        passwordJTextField.setEditable(false);
        passwordJTextField.setEnabled(false);
        changePassJButton.setEnabled(true);
        saveJButton.setEnabled(false);
        account.setPassword(passwordJTextField.getText());
        JOptionPane.showMessageDialog(null, "Updated Successfully!");
    }//GEN-LAST:event_saveJButtonActionPerformed

    private void selectJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectJButtonActionPerformed
        // TODO add your handling code here:

        int selectedRow = entrustJTable.getSelectedRow();
        if (brokerJTextField.getText() == "") {
            JOptionPane.showMessageDialog(null, "You have selected Broker");
            return;
        }
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please Select a row");
            return;
        }
        Employee broker = (Employee) entrustJTable.getValueAt(selectedRow, 0);
        for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
            if (organization instanceof BrokerOrganization) {
                for (WorkRequest request : organization.getWorkQueue().getWorkRequestList()) {
                    if (request instanceof AssignBrokerWorkRequest) {
                        if (request.getSender() == account) {
                            JOptionPane.showMessageDialog(null, "You have selected one broker");
                            return;
                        }
                    }

                }
            }
        }
        AssignBrokerWorkRequest request = new AssignBrokerWorkRequest();
        request.setMessage("Assign Broker");
        request.setSender(account);
        request.setStatusString("Pending");
        request.setBroker(broker);

        Organization org = null;
        for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
            if (organization instanceof BrokerOrganization) {
                org = organization;
                break;
            }
        }
        if (org != null) {
            org.getWorkQueue().getWorkRequestList().add(request);
            account.getWorkQueue().getWorkRequestList().add(request);
            JOptionPane.showMessageDialog(null, "Submitted successfully");
        }


    }//GEN-LAST:event_selectJButtonActionPerformed

    private void showJButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showJButton1ActionPerformed
        // TODO add your handling code here:
        int selectedRow = localWalletJTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please Select a row");
            return;
        } else {
            Bitcoin bitcoin = (Bitcoin) localWalletJTable.getValueAt(selectedRow, 0);
            UploadBitcoinWorkRequest request = new UploadBitcoinWorkRequest();
            request.setBitcoin(bitcoin);
            request.setMessage("Upload Bitcoin");
            request.setSender(account);
            request.setStatusString("Pending");
            request.setReceiver(null);
            request.setBitcoin_price(ecoSystem.getBitcoinPrice());
            account.getPerson().getLocalBitcoinWallet().remove(bitcoin);

            Organization org = null;
            for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
                if (organization instanceof CustomerManagementOrganization) {
                    org = organization;
                    break;
                }
            }
            if (org != null) {
                org.getWorkQueue().getWorkRequestList().add(request);
                account.getWorkQueue().getWorkRequestList().add(request);
                populateLocalWalletTable();
                JOptionPane.showMessageDialog(null, "Upload successfuly! Need to be authenticated");
            }

        }
    }//GEN-LAST:event_showJButton1ActionPerformed

    private void showJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showJButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = localWalletJTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "You have to select one row");
            return;
        }
        Bitcoin bitcoin = (Bitcoin) localWalletJTable.getValueAt(selectedRow, 0);
        CustodianBitcoinDetailJPanel cbdjp = new CustodianBitcoinDetailJPanel(userProcessContainer, bitcoin);
        userProcessContainer.add("cbdjp", cbdjp);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_showJButtonActionPerformed

    private void showDetailJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showDetailJButtonActionPerformed
        // TODO add your handling code here:
        int selecedRow = orderHistoryJTable.getSelectedRow();
        if (selecedRow < 0) {
            JOptionPane.showMessageDialog(null, "You should select one row");
            return;
        }
        Bitcoin bitcoin = (Bitcoin) orderHistoryJTable.getValueAt(selecedRow, 0);
        String type = (String) orderHistoryJTable.getValueAt(selecedRow, 1);
        ExchangeHistory exchangeHistory = (ExchangeHistory) orderHistoryJTable.getValueAt(selecedRow, 3);
        serialnumTextField.setText(String.valueOf(bitcoin.getSerialNum()));
        typeTextField.setText(type);
        buyerTextField.setText(exchangeHistory.getBuyer().getName());
        sellerTextField.setText(exchangeHistory.getSeller().getName());
        priceTextField.setText(String.valueOf(exchangeHistory.getExchangePrize()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dateTextField.setText(String.valueOf(sdf.format(exchangeHistory.getDate())));
    }//GEN-LAST:event_showDetailJButtonActionPerformed

    private void rechargeJButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechargeJButton1ActionPerformed
        // TODO add your handling code here:
        if (AmountJTextField.getText().trim().equals("") || !isNumeric(AmountJTextField.getText().trim()) || Integer.parseInt(AmountJTextField.getText().trim()) <= 0 ) {
            JOptionPane.showMessageDialog(null, "Please enter a correct and not zero number.");
            return;
        }
        double amount = Double.parseDouble(AmountJTextField.getText());
        RechargeMoneyWorkRequest request = new RechargeMoneyWorkRequest();
        request.setBankaccount(account.getPerson().getBankAccount());
        request.setMessage("Recharge Money");
        request.setSender(account);
        request.setStatusString("Pending");
        request.setMoney(amount);

        Organization org = null;
        for (Network network : ecoSystem.getNetworkList()) {
            for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                if (enterprise instanceof BankEnterprise) {
                    for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
                        if (organization instanceof BankAccountingOrganization) {
                            org = (BankAccountingOrganization) organization;
                        }
                    }
                }
            }
        }

        if (org == null) {
            JOptionPane.showMessageDialog(null, "You should create BankAccounting Organization first");
        } else {
            account.getWorkQueue().getWorkRequestList().add(request);
            org.getWorkQueue().getWorkRequestList().add(request);
            JOptionPane.showMessageDialog(null, "Submitted Successfully");
            populateRechargeTable();
        }
    }//GEN-LAST:event_rechargeJButton1ActionPerformed

    private void withdrawJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_withdrawJButtonActionPerformed
        // TODO add your handling code here:
        
        if (AmountJTextField1.getText().trim().equals("") || !isNumeric(AmountJTextField1.getText().trim()) ||Integer.parseInt(AmountJTextField1.getText().trim()) <= 0 ) {
            JOptionPane.showMessageDialog(null, "Please enter a correct and not zero number.");
            return;
        }
        double amount = Double.parseDouble(AmountJTextField1.getText());
        if (amount > account.getPerson().getWallet().getFlat()) {
            JOptionPane.showMessageDialog(null, "You have no enough money to withdraw");
            return;
        }
        WithdrawMoneyWorkRequest request = new WithdrawMoneyWorkRequest();
        request.setBankaccount(account.getPerson().getBankAccount());
        request.setMessage("Withdraw Money");
        request.setSender(account);
        request.setStatusString("Pending");
        request.setMoney(amount);

        Organization org = null;
        for (Network network : ecoSystem.getNetworkList()) {
            for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                if (enterprise instanceof BankEnterprise) {
                    for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
                        if (organization instanceof BankAccountingOrganization) {
                            org = (BankAccountingOrganization) organization;
                        }
                    }
                }
            }
        }

        if (org == null) {
            JOptionPane.showMessageDialog(null, "You should create BankAccounting Organization first");
        } else {
            account.getWorkQueue().getWorkRequestList().add(request);
            account.getPerson().getWallet().setFlat(account.getPerson().getWallet().getFlat() - amount);
            org.getWorkQueue().getWorkRequestList().add(request);
            JOptionPane.showMessageDialog(null, "submit successfully");
            populateWithdrawTable();
            populateAssetsManagement();
        }


    }//GEN-LAST:event_withdrawJButtonActionPerformed

    private void transactionfeeJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transactionfeeJTextFieldActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_transactionfeeJTextFieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please Select a row");
            return;
        }else{
            Bitcoin bitcoin = (Bitcoin)jTable1.getValueAt(selectedRow, 0);
            WithdrawBitcoinWorkRequest request = new WithdrawBitcoinWorkRequest();
            request.setBitcoin(bitcoin);
            request.setMessage("Withdraw Bitcoin");
            request.setSender(account);
            request.setPrice(ecoSystem.getBitcoinPrice());
            request.setStatusString("Pending");
            account.getWorkQueue().getWorkRequestList().add(request);
            account.getPerson().getWallet().getBitcoinList().remove(bitcoin);
            
            for(Organization organization :enterprise.getOrganizationDirectory().getOrganizationList()){
                if(organization instanceof CustomerManagementOrganization){
                    organization.getWorkQueue().getWorkRequestList().add(request);
                }
            }
            populateWithdrawBitcoin();
            populateWithdrawBitcoinrequest();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            //System.out.println(str.charAt(i));
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AmountJTextField;
    private javax.swing.JTextField AmountJTextField1;
    private javax.swing.JPanel RechargeJPanel;
    private javax.swing.JTable RechargeTable1;
    private javax.swing.JPanel WithdrawJPanel;
    private javax.swing.JPanel assetsManagementJPanel;
    private javax.swing.JTextField availableBitcoinJTextField;
    private javax.swing.JTextField availableMoneyJTextField;
    private javax.swing.JTextField bankaccountTextField;
    private javax.swing.JPanel basicInfoJPanel;
    private javax.swing.JTextField brokerJTextField;
    private javax.swing.JTextField buyerTextField;
    private javax.swing.JButton changePassJButton;
    private javax.swing.JTextField customerJTextField;
    private javax.swing.JTextField dateTextField;
    private javax.swing.JPanel entrustJPanel;
    private javax.swing.JTable entrustJTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JPanel localWalletJPanel;
    private javax.swing.JTable localWalletJTable;
    private javax.swing.JTextField nameJTextField;
    private javax.swing.JPanel orderHistoryJPanel;
    private javax.swing.JTable orderHistoryJTable;
    private javax.swing.JTextField passwordJTextField;
    private javax.swing.JTextField pendingBitcoinJTextField;
    private javax.swing.JTextField priceTextField;
    private javax.swing.JTextField profitsJTextField;
    private javax.swing.JButton rechargeJButton1;
    private javax.swing.JButton saveJButton;
    private javax.swing.JButton selectJButton;
    private javax.swing.JTextField sellerTextField;
    private javax.swing.JTextField serialnumTextField;
    private javax.swing.JButton showDetailJButton;
    private javax.swing.JButton showJButton;
    private javax.swing.JButton showJButton1;
    private javax.swing.JTextField totalAssetsJTextField;
    private javax.swing.JTextField transactionfeeJTextField;
    private javax.swing.JTextField typeTextField;
    private javax.swing.JTextField usernameJTextField;
    private javax.swing.JButton withdrawJButton;
    private javax.swing.JTable withdrawJTable;
    // End of variables declaration//GEN-END:variables
}
