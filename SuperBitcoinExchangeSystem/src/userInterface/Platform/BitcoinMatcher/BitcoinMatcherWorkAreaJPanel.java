/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.Platform.BitcoinMatcher;

import Business.EcoSystem;
import Business.Employee.Person;
import Business.Enterprise.DepositoryEnterprise;
import Business.Enterprise.Enterprise;
import Business.Enterprise.PlatformEnterprise;
import Business.Network.Network;
import Business.Organization.BitcoinPlatform.BitcoinMatcherOrganization;
import Business.Organization.Depository.AuthenticationOrganization;
import Business.Organization.Organization;
import Business.Other.Bitcoin;
import Business.Other.ExchangeHistory;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.AuthenticateTransactionWorkRequest;
import Business.WorkQueue.BrokerBuyWorkRequest;
import Business.WorkQueue.BrokerSellWorkRequest;
import Business.WorkQueue.WorkRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
public class BitcoinMatcherWorkAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form BitcoinMatcherWorkAreaJPanel
     */
    JPanel userProcessContainer;
    UserAccount account;
    BitcoinMatcherOrganization bitcoinMatcherOrganization;
    PlatformEnterprise platformEnterprise;
    ArrayList<Bitcoin> bitcoinlist = new ArrayList<>();
    EcoSystem ecoSystem;
    

    public BitcoinMatcherWorkAreaJPanel(JPanel userProcessContainer, UserAccount account, BitcoinMatcherOrganization bitcoinMatcherOrganization, PlatformEnterprise platformEnterprise, EcoSystem ecoSystem) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.bitcoinMatcherOrganization = bitcoinMatcherOrganization;
        this.platformEnterprise = platformEnterprise;
        this.ecoSystem = ecoSystem;
        populateUnprocessedBuyRequest(getbuyWorkRequests());
        populateUnprocessedSellRequest(getsellWorkRequests());

        requestJTabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
                int selectedIndex = tabbedPane.getSelectedIndex();
                switch (selectedIndex) {
                    case 0:
                        populateUnprocessedBuyRequest(getbuyWorkRequests());
                        populateUnprocessedSellRequest(getsellWorkRequests());
                        System.out.println("0");
                        break;
                    case 1:
                        populateProcessedBuyRequest(getbuyWorkRequests());
                        populateProcessedSellRequest(getsellWorkRequests());
                        System.out.println("1");
                        break;
                    case 2:
                        populateAuthenticateTable();
                        
                }

            }
        });
    }

    public void populateUnprocessedBuyRequest(ArrayList<BrokerBuyWorkRequest> buyrequestlist) {
        DefaultTableModel model = (DefaultTableModel) buyerRequestJTable.getModel();
        model.setRowCount(0);
        for (BrokerBuyWorkRequest request : buyrequestlist) {
            if (request.getReceiver() == null) {
                Object[] row = new Object[7];
                row[0] = request;
                row[1] = request.getSender().getPerson().getId();
                row[2] = request.getSender().getPerson().getName();
                row[3] = ((BrokerBuyWorkRequest) request).getQuantity();
                row[4] = ((BrokerBuyWorkRequest) request).getPrice();
                row[5] = request.getStatusString();
                row[6] = request.getBuy_quantity();
                model.addRow(row);
            }
        }
    }

    public void populateUnprocessedSellRequest(ArrayList<BrokerSellWorkRequest> sellrequestlist) {
        DefaultTableModel model = (DefaultTableModel) sellRequestJTable.getModel();
        model.setRowCount(0);
        for (BrokerSellWorkRequest request : sellrequestlist) {
            if (request.getReceiver() == null) {
                Object[] row = new Object[7];
                row[0] = request;
                row[1] = request.getSender().getPerson().getId();
                row[2] = request.getSender().getPerson().getName();
                row[3] = ((BrokerSellWorkRequest) request).getQuantity();
                row[4] = ((BrokerSellWorkRequest) request).getPrice();
                row[5] = request.getStatusString();
                row[6] = request.getBitcoinlist().size();
                model.addRow(row);
            }
        }
    }

    public void populateProcessedBuyRequest(ArrayList<BrokerBuyWorkRequest> buyrequestlist) {
        DefaultTableModel model = (DefaultTableModel) buyerRequestJTable2.getModel();
        model.setRowCount(0);
        for (BrokerBuyWorkRequest request : buyrequestlist) {
            if (request.getReceiver() != null) {
                Object[] row = new Object[6];
                row[0] = request;
                row[1] = request.getSender().getPerson().getName();
                row[2] = request.getCustomer_buy().getName();
                row[3] = ((BrokerBuyWorkRequest) request).getQuantity();
                row[4] = ((BrokerBuyWorkRequest) request).getPrice();
                row[5] = request.getStatusString();
                model.addRow(row);
            }
        }
    }

    public void populateProcessedSellRequest(ArrayList<BrokerSellWorkRequest> sellrequestlist) {
        DefaultTableModel model = (DefaultTableModel) sellerRequestJTable1.getModel();
        model.setRowCount(0);
        for (BrokerSellWorkRequest request : sellrequestlist) {
            if (request.getReceiver() != null) {
                Object[] row = new Object[6];
                row[0] = request;
                row[1] = request.getSender().getPerson().getName();
                row[2] = request.getCustomer_sell().getName();
                row[3] = ((BrokerSellWorkRequest) request).getQuantity();
                row[4] = ((BrokerSellWorkRequest) request).getPrice();
                row[5] = request.getStatusString();
                model.addRow(row);
            }
        }
    }
    
    public void populateAuthenticateTable(){
        DefaultTableModel model = (DefaultTableModel)authenticateJTable1.getModel();
        model.setRowCount(0);
        for(WorkRequest request : account.getWorkQueue().getWorkRequestList()){
            if(request instanceof AuthenticateTransactionWorkRequest){
                Object[] row = new Object[6];
                row[0] = request;
                row[1] = ((AuthenticateTransactionWorkRequest) request).getBitcoin().getSerialNum();
                row[2] = ((AuthenticateTransactionWorkRequest) request).getDate();
                row[3] = ((AuthenticateTransactionWorkRequest) request).getBuyer();
                row[4] = ((AuthenticateTransactionWorkRequest) request).getSeller();
                row[5] = request.getStatusString();
                model.addRow(row);
            }
        }
    }

    public ArrayList<BrokerBuyWorkRequest> getbuyWorkRequests() {
        ArrayList<BrokerBuyWorkRequest> buyrequestlist = new ArrayList<>();
        for (WorkRequest request : bitcoinMatcherOrganization.getWorkQueue().getWorkRequestList()) {
            if (request instanceof BrokerBuyWorkRequest) {
                buyrequestlist.add((BrokerBuyWorkRequest) request);
            }
        }
        return buyrequestlist;
    }

    public ArrayList<BrokerSellWorkRequest> getsellWorkRequests() {
        ArrayList<BrokerSellWorkRequest> sellrequestlist = new ArrayList<>();
        for (WorkRequest request : bitcoinMatcherOrganization.getWorkQueue().getWorkRequestList()) {
            if (request instanceof BrokerSellWorkRequest) {
                sellrequestlist.add((BrokerSellWorkRequest) request);
            }
        }
        return sellrequestlist;
    }

    public ArrayList<BrokerBuyWorkRequest> sorBuyWorkRequests() {
        ArrayList<BrokerBuyWorkRequest> buyrequestlist = getbuyWorkRequests();
        Collections.sort(buyrequestlist, new Comparator<BrokerBuyWorkRequest>() {
            @Override
            public int compare(BrokerBuyWorkRequest b, BrokerBuyWorkRequest a) {
                Integer s = null;
                if (a.getPrice() > b.getPrice()) {
                    s = 1;
                } else if (a.getPrice() < b.getPrice()) {
                    s = -1;
                } else {
                    if (a.getRequestDate().before(b.getRequestDate())) {
                        s = 1;
                    } else if (a.getRequestDate().after(b.getRequestDate())) {
                        s = -1;
                    } else {
                        s = 0;
                    }
                }
                return s;
            }

        });

        return buyrequestlist;
    }

    public ArrayList<BrokerSellWorkRequest> sorSellWorkRequests() {
        ArrayList<BrokerSellWorkRequest> sellrequestlist = getsellWorkRequests();
        Collections.sort(sellrequestlist, new Comparator<BrokerSellWorkRequest>() {
            @Override
            public int compare(BrokerSellWorkRequest b, BrokerSellWorkRequest a) {
                Integer s = null;
                if (a.getPrice() > b.getPrice()) {
                    s = 1;
                } else if (a.getPrice() < b.getPrice()) {
                    s = -1;
                } else {
                    if (a.getRequestDate().before(b.getRequestDate())) {
                        s = 1;
                    } else if (a.getRequestDate().after(b.getRequestDate())) {
                        s = -1;
                    } else {
                        s = 0;
                    }
                }
                return s;
            }

        });

        return sellrequestlist;

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
        requestJTabbedPane = new javax.swing.JTabbedPane();
        unprocessedRequestJPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        buyerRequestJTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        sellRequestJTable = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        processedRequestJPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        buyerRequestJTable2 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        sellerRequestJTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        authenticateJTable1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 102, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Matcher Work Area");

        unprocessedRequestJPanel.setBackground(new java.awt.Color(255, 255, 255));

        buyerRequestJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Message", "Broker ID", "Broker Name", "Quantity", "Min Selling Price", "Statu", "To be bought"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(buyerRequestJTable);
        if (buyerRequestJTable.getColumnModel().getColumnCount() > 0) {
            buyerRequestJTable.getColumnModel().getColumn(1).setResizable(false);
            buyerRequestJTable.getColumnModel().getColumn(2).setResizable(false);
            buyerRequestJTable.getColumnModel().getColumn(3).setResizable(false);
            buyerRequestJTable.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Seller Request List");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Buyer Request List");

        sellRequestJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Messsage", "Broker ID", "Broker Name", "Quantity", "Max Biding Price", "Statu", "To be sold"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(sellRequestJTable);
        if (sellRequestJTable.getColumnModel().getColumnCount() > 0) {
            sellRequestJTable.getColumnModel().getColumn(1).setResizable(false);
            sellRequestJTable.getColumnModel().getColumn(2).setResizable(false);
            sellRequestJTable.getColumnModel().getColumn(3).setResizable(false);
            sellRequestJTable.getColumnModel().getColumn(4).setResizable(false);
        }

        jButton3.setText("Match ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton6.setText("Confirm");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Sort");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Deal Price");

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField1.setEnabled(false);

        javax.swing.GroupLayout unprocessedRequestJPanelLayout = new javax.swing.GroupLayout(unprocessedRequestJPanel);
        unprocessedRequestJPanel.setLayout(unprocessedRequestJPanelLayout);
        unprocessedRequestJPanelLayout.setHorizontalGroup(
            unprocessedRequestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(unprocessedRequestJPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(unprocessedRequestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(unprocessedRequestJPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(unprocessedRequestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49))
                    .addGroup(unprocessedRequestJPanelLayout.createSequentialGroup()
                        .addGroup(unprocessedRequestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addContainerGap(803, Short.MAX_VALUE))
                    .addGroup(unprocessedRequestJPanelLayout.createSequentialGroup()
                        .addGroup(unprocessedRequestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(unprocessedRequestJPanelLayout.createSequentialGroup()
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(221, 221, 221)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton6))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        unprocessedRequestJPanelLayout.setVerticalGroup(
            unprocessedRequestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(unprocessedRequestJPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addGroup(unprocessedRequestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(unprocessedRequestJPanelLayout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, unprocessedRequestJPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(unprocessedRequestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addGap(41, 41, 41))
        );

        requestJTabbedPane.addTab("Unprocessed Requests", unprocessedRequestJPanel);

        processedRequestJPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Buyer Request List");

        buyerRequestJTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Message", "Broker Name", "Buyer Name", "Quantity", "Max Biding Price", "Statu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(buyerRequestJTable2);
        if (buyerRequestJTable2.getColumnModel().getColumnCount() > 0) {
            buyerRequestJTable2.getColumnModel().getColumn(1).setResizable(false);
            buyerRequestJTable2.getColumnModel().getColumn(3).setResizable(false);
            buyerRequestJTable2.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Seller Request List");

        sellerRequestJTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Message", "Broker Name", "Seller Name", "Quantity", "Minimum Selling Price", "Statu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(sellerRequestJTable1);
        if (sellerRequestJTable1.getColumnModel().getColumnCount() > 0) {
            sellerRequestJTable1.getColumnModel().getColumn(2).setResizable(false);
            sellerRequestJTable1.getColumnModel().getColumn(3).setResizable(false);
            sellerRequestJTable1.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout processedRequestJPanelLayout = new javax.swing.GroupLayout(processedRequestJPanel);
        processedRequestJPanel.setLayout(processedRequestJPanelLayout);
        processedRequestJPanelLayout.setHorizontalGroup(
            processedRequestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, processedRequestJPanelLayout.createSequentialGroup()
                .addContainerGap(168, Short.MAX_VALUE)
                .addGroup(processedRequestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(processedRequestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(processedRequestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(141, 141, 141))
        );
        processedRequestJPanelLayout.setVerticalGroup(
            processedRequestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(processedRequestJPanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel4)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
        );

        requestJTabbedPane.addTab("Processed Requests", processedRequestJPanel);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        authenticateJTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Message", "Serial Num", "Transaction date", "Buyer", "Seller", "Statu"
            }
        ));
        jScrollPane5.setViewportView(authenticateJTable1);

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setText("Authenticated Transaction");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(125, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(113, 113, 113))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(375, 375, 375))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel7)
                .addGap(51, 51, 51)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(170, Short.MAX_VALUE))
        );

        requestJTabbedPane.addTab("Authenticate Transaction", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(requestJTabbedPane)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(390, 390, 390))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(requestJTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        populateUnprocessedBuyRequest(sorBuyWorkRequests());
        populateUnprocessedSellRequest(sorSellWorkRequests());


    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        populateUnprocessedBuyRequest(sorBuyWorkRequests());
        populateUnprocessedSellRequest(sorSellWorkRequests());
        BrokerBuyWorkRequest buy_request = null;
        BrokerSellWorkRequest sell_request = null;
        int rowsell = sellRequestJTable.getRowCount();
        int rowbuy = buyerRequestJTable.getRowCount();
        if (rowsell == 0 || rowbuy == 0) {
            JOptionPane.showMessageDialog(null, "Can not match");
            return;
        } else {
            sell_request = (BrokerSellWorkRequest) sellRequestJTable.getValueAt(rowsell - 1, 0);
            buy_request = (BrokerBuyWorkRequest) buyerRequestJTable.getValueAt(0, 0);
            if(sell_request.getPrice() > buy_request.getPrice()){
                JOptionPane.showMessageDialog(null, "The sell price is bigger than buy price, can not match!");
                return;
            }
        }
        int min = Math.min(sell_request.getBitcoinlist().size(), buy_request.getBuy_quantity());
        if (min == 0) {
            JOptionPane.showMessageDialog(null, "Error");
            return;
        }
        for (int i = 0; i < min; i++) {
            Bitcoin bitcoin = sell_request.getBitcoinlist().get(i);
            ExchangeHistory history = new ExchangeHistory();
            history.setBuyer(buy_request.getCustomer_buy());
            history.setSeller(sell_request.getCustomer_sell());
            history.setDate(new Date());
            history.setSell_broker(sell_request.getSender().getPerson());
            history.setBuy_broker(buy_request.getSender().getPerson());
            history.setExchangePrize((sell_request.getPrice() + buy_request.getPrice()) / 2);
            bitcoin.getExchangeHistoryList().add(history);
            buy_request.getCustomer_buy().getWallet().getBitcoinList().add(bitcoin);
            buy_request.setBuy_quantity(buy_request.getBuy_quantity() - 1);
            bitcoinlist.add(bitcoin);
            
            AuthenticateTransactionWorkRequest request = new AuthenticateTransactionWorkRequest();
            request.setBuyer(buy_request.getCustomer_buy());
            request.setSeller(sell_request.getCustomer_sell());
            request.setBitcoin(bitcoin);
            request.setDate(new Date());
            request.setMessage("Authenticate Transaction");
            request.setSender(account);
            request.setStatusString("Pending");
            
            for(Network network : ecoSystem.getNetworkList()){
                for(Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()){
                    if(enterprise instanceof DepositoryEnterprise){
                        for(Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()){
                            if(organization instanceof AuthenticationOrganization){
                                organization.getWorkQueue().getWorkRequestList().add(request);
                                account.getWorkQueue().getWorkRequestList().add(request);
                                break;
                            }
                        }
                    }
                }
            }
            
        }

        for (int i = bitcoinlist.size() - min; i < bitcoinlist.size(); i++) {
            sell_request.getBitcoinlist().remove(bitcoinlist.get(i));
        }
        if (buy_request.getBuy_quantity() == 0) {
            buy_request.setReceiver(account);
            buy_request.setStatusString("Deal");
            buy_request.setResolveDate(new Date());
            
        }
        if (sell_request.getBitcoinlist().size() == 0) {
            sell_request.setReceiver(account);
            sell_request.setStatusString("Deal");
            sell_request.setResolveDate(new Date());
        }
        populateUnprocessedBuyRequest(sorBuyWorkRequests());
        populateUnprocessedSellRequest(sorSellWorkRequests());
        JOptionPane.showMessageDialog(null, "Successfully Matched");
        //workrequesJTable1.getValueAt(selectedRow, 0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if(bitcoinlist.size() == 0){
            JOptionPane.showMessageDialog(null, "You have to match first!");
            return;
        }
        double price = bitcoinlist.get(bitcoinlist.size()-1).getExchangeHistoryList().get(bitcoinlist.get(bitcoinlist.size()-1).getExchangeHistoryList().size()-1).getExchangePrize();
        for(int i=0; i<bitcoinlist.size();i++){
            bitcoinlist.get(i).getExchangeHistoryList().get((bitcoinlist.get(i).getExchangeHistoryList().size())-1).setExchangePrize(price);
            platformEnterprise.setIncome(platformEnterprise.getIncome()+ price*(0.003));
            Person seller = bitcoinlist.get(i).getExchangeHistoryList().get((bitcoinlist.get(i).getExchangeHistoryList().size())-1).getSeller();
            Person buyer = bitcoinlist.get(i).getExchangeHistoryList().get((bitcoinlist.get(i).getExchangeHistoryList().size())-1).getBuyer();
            seller.getWallet().setFlat(seller.getWallet().getFlat() + price*(1-0.002-0.001));
            buyer.getWallet().setFlat(buyer.getWallet().getFlat()-price*(1 + 0.002));
        }
        jTextField1.setText(String.valueOf(price));
        ecoSystem.setBitcoinPrice(price);
        bitcoinlist.clear();
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable authenticateJTable1;
    private javax.swing.JTable buyerRequestJTable;
    private javax.swing.JTable buyerRequestJTable2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel processedRequestJPanel;
    private javax.swing.JTabbedPane requestJTabbedPane;
    private javax.swing.JTable sellRequestJTable;
    private javax.swing.JTable sellerRequestJTable1;
    private javax.swing.JPanel unprocessedRequestJPanel;
    // End of variables declaration//GEN-END:variables
}
