/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.Depository.Custodian;

import Business.EcoSystem;
import Business.Enterprise.DepositoryEnterprise;
import Business.Organization.Depository.AuthenticationOrganization;
import Business.UserAccount.UserAccount;
import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author Lei
 */
public class CustodianWorkAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form CustodianWorkAreaJPanel
     */
   JPanel userProcessContainer;
   UserAccount account;
   AuthenticationOrganization authenticationOrganization;
   DepositoryEnterprise depositoryEnterprise;
   EcoSystem system;

    public CustodianWorkAreaJPanel(JPanel userProcessContainer, UserAccount account, AuthenticationOrganization authenticationOrganization, DepositoryEnterprise depositoryEnterprise, EcoSystem system) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.authenticationOrganization = authenticationOrganization;
        this.depositoryEnterprise = depositoryEnterprise;
        this.system = system;
        lblEnterpriseName.setText(depositoryEnterprise.getName());
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        btnBitcoinList = new javax.swing.JButton();
        lblEnterpriseName = new javax.swing.JLabel();
        jlabel = new javax.swing.JLabel();
        btnAuthenticateTransaction = new javax.swing.JButton();
        btnVerificationNewMinedBitcoin = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 255));
        jLabel4.setText("Depository Custodian WorkArea");

        btnBitcoinList.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnBitcoinList.setText("Bitcoin List");
        btnBitcoinList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBitcoinListActionPerformed(evt);
            }
        });

        lblEnterpriseName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblEnterpriseName.setText("<Enterprise Name>");

        jlabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlabel.setText("Enterprise: ");

        btnAuthenticateTransaction.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnAuthenticateTransaction.setText("Authenticate Transaction");
        btnAuthenticateTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAuthenticateTransactionActionPerformed(evt);
            }
        });

        btnVerificationNewMinedBitcoin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnVerificationNewMinedBitcoin.setText("Verification New Mined Bitcoin");
        btnVerificationNewMinedBitcoin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificationNewMinedBitcoinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(323, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(308, 308, 308))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(172, 172, 172)
                            .addComponent(lblEnterpriseName, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBitcoinList, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVerificationNewMinedBitcoin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlabel)
                                    .addComponent(btnAuthenticateTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel4)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlabel)
                    .addComponent(lblEnterpriseName))
                .addGap(45, 45, 45)
                .addComponent(btnAuthenticateTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btnBitcoinList, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnVerificationNewMinedBitcoin, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(229, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBitcoinListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBitcoinListActionPerformed
        // TODO add your handling code here:
        CustodianBitcoinListJPanel cbljp = new CustodianBitcoinListJPanel(userProcessContainer, depositoryEnterprise,authenticationOrganization,system);
        userProcessContainer.add("cbljp", cbljp);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_btnBitcoinListActionPerformed

    private void btnAuthenticateTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAuthenticateTransactionActionPerformed
        // TODO add your handling code here:
        CustodianAuthenticateTransactionJPanel catjp = new CustodianAuthenticateTransactionJPanel(userProcessContainer, depositoryEnterprise,account, authenticationOrganization.getWorkQueue());
        userProcessContainer.add("catjp", catjp);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_btnAuthenticateTransactionActionPerformed

    private void btnVerificationNewMinedBitcoinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificationNewMinedBitcoinActionPerformed
        // TODO add your handling code here:
        CustodianVerificationRequestJPanel cvrjp = new CustodianVerificationRequestJPanel(userProcessContainer, account, depositoryEnterprise, authenticationOrganization);
        userProcessContainer.add("cvrjp", cvrjp);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_btnVerificationNewMinedBitcoinActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAuthenticateTransaction;
    private javax.swing.JButton btnBitcoinList;
    private javax.swing.JButton btnVerificationNewMinedBitcoin;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jlabel;
    private javax.swing.JLabel lblEnterpriseName;
    // End of variables declaration//GEN-END:variables
}
