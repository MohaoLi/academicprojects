/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.Platform.Analyst;
import Business.EcoSystem;
import Business.Employee.Employee;
import Business.Enterprise.DepositoryEnterprise;
import Business.Enterprise.Enterprise;
import Business.Enterprise.PlatformEnterprise;
import Business.Network.Network;
import Business.Organization.BitcoinPlatform.AnalysisOrganization;
import Business.Organization.BitcoinPlatform.BrokerOrganization;
import Business.Organization.Organization;
import Business.Other.Bitcoin;
import Business.Other.ExchangeHistory;
import Business.UserAccount.UserAccount;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Hashtable;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Lei
 */
public class AnalystWorkAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form AnalystWorkAreaJPanel
     */

    JPanel userProcessContainer;
    UserAccount account;
    AnalysisOrganization analysisOrganization;
    PlatformEnterprise platformEnterprise;
    EcoSystem system;
    
    public AnalystWorkAreaJPanel(JPanel userProcessContainer, UserAccount account, AnalysisOrganization analysisOrganization, PlatformEnterprise platformEnterprise,EcoSystem system) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.analysisOrganization = analysisOrganization;
        this.platformEnterprise = platformEnterprise;
        this.system = system;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton1.setText("Bitcoin Price");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dubai", 0, 24)); // NOI18N
        jLabel1.setText("Analysis WorkArea");

        jButton2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton2.setText("Broker transaction times");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton3.setText("Broker Income");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 428, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(387, 387, 387))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(173, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Hashtable<Date, Double> historyprice = new Hashtable<>();
        for(Network network:system.getNetworkList()){
            for(Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()){
                if(enterprise instanceof DepositoryEnterprise){
                    for(Bitcoin bitcoin : ((DepositoryEnterprise) enterprise).getBitcoinList().keySet()){
                        if(bitcoin.getExchangeHistoryList().size() != 0){
                            for(ExchangeHistory exchangeHistory : bitcoin.getExchangeHistoryList()){
                                historyprice.put(exchangeHistory.getDate(), exchangeHistory.getExchangePrize());
                            }
                        }
                    }
                }
            }
        }
        ArrayList list = new ArrayList<>();
        for(Date date : historyprice.keySet()){
            ArrayList<Object> a = new ArrayList<>();
            a.add(date);
            a.add(historyprice.get(date));
            list.add(a);   
        }
        
        Collections.sort(list, new Comparator<ArrayList>(){
            @Override
            public int compare(ArrayList a, ArrayList b) {
                Integer s = null;
                if(((Date)(a.get(0))).before(((Date)b.get(0)))){
                    s = -1;
                }else{
                    s = 1;
                }
                return s;
            }
        });

        
        DefaultCategoryDataset dataset =  new DefaultCategoryDataset();
        for(int i = 0; i < list.size(); i++){
            String date_1 = String.valueOf(((ArrayList)list.get(i)).get(0));
            dataset.addValue((Double)((ArrayList)list.get(i)).get(1),"Bitcoin Price",date_1);
        }
        
        
        JFreeChart chart = ChartFactory.createLineChart("Bitcoin Price","Date", "Price", dataset);
        chart.setBackgroundPaint(Color.WHITE);
        chart.getTitle().setPaint(Color.BLUE);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.BLACK);
        ChartFrame frame = new ChartFrame("Bar", chart);
        frame.setVisible(true);
        frame.setSize(800,600);
     
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Hashtable<Employee, Integer> brokerlist = new Hashtable<>();
        for(Organization organization : platformEnterprise.getOrganizationDirectory().getOrganizationList()){
            if(organization instanceof BrokerOrganization){
                for(UserAccount userAccount : organization.getUserAccountDirectory().getUserAccountList()){
                    brokerlist.put((Employee)userAccount.getPerson(), 0);
                }
            }
        }
        for(Employee employee : brokerlist.keySet()){
        for(Network network : system.getNetworkList()){
            for(Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()){
                if(enterprise instanceof DepositoryEnterprise){
                    for(Bitcoin bitcoin : ((DepositoryEnterprise) enterprise).getBitcoinList().keySet()){
                        if(bitcoin.getExchangeHistoryList().size() != 0){
                            for(ExchangeHistory exchangeHistory : bitcoin.getExchangeHistoryList()){
                                if(exchangeHistory.getBuy_broker() == employee){
                                    brokerlist.replace(employee, ((Integer)brokerlist.get(employee))+1);
                                }
                                if(exchangeHistory.getSell_broker() == employee){
                                    brokerlist.replace(employee, ((Integer)brokerlist.get(employee))+1);
                                }
                            }
                        }
                    }
                }
            }
        }
        }
        
        DefaultPieDataset dataset =  new DefaultPieDataset();
        for(Employee employee : brokerlist.keySet()){
            dataset.setValue(employee.getName(),(Integer)brokerlist.get(employee));
        }
//            dataset.addValue((Double)((ArrayList)list.get(i)).get(1),"Bitcoin Price",date_1);
        JFreeChart chart = ChartFactory.createPieChart3D("Tranaction Times", dataset, true, true,false);
        chart.setTitle(new TextTitle("Tranaction Times", new Font("Arial",Font.CENTER_BASELINE, 20)));
        chart.setBackgroundPaint(Color.LIGHT_GRAY);
        chart.getTitle().setPaint(Color.red);
        PiePlot3D mPiePlot = (PiePlot3D)chart.getPlot();
        mPiePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})", NumberFormat.getNumberInstance(), new DecimalFormat("0.00%"))); 
        mPiePlot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})"));
        mPiePlot.setLabelFont(new Font("Arial", Font.PLAIN, 15));
        ChartFrame mChartFrame = new ChartFrame("Tranaction Times", chart);  
        mChartFrame.pack();  
        mChartFrame.setVisible(true);  
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Hashtable<Employee, Double> brokerlist = new Hashtable<>();
        for(Organization organization : platformEnterprise.getOrganizationDirectory().getOrganizationList()){
            if(organization instanceof BrokerOrganization){
                for(UserAccount userAccount : organization.getUserAccountDirectory().getUserAccountList()){
                    brokerlist.put((Employee)userAccount.getPerson(), 0.0);
                }
            }
        }
        
        for(Employee employee : brokerlist.keySet()){
        for(Network network : system.getNetworkList()){
            for(Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()){
                if(enterprise instanceof DepositoryEnterprise){
                    for(Bitcoin bitcoin : ((DepositoryEnterprise) enterprise).getBitcoinList().keySet()){
                        if(bitcoin.getExchangeHistoryList().size() != 0){
                            for(ExchangeHistory exchangeHistory : bitcoin.getExchangeHistoryList()){
                                if(exchangeHistory.getBuy_broker() == employee){
                                    brokerlist.replace(employee, ((Double)brokerlist.get(employee)) + exchangeHistory.getExchangePrize()*0.02);
                                }
                                if(exchangeHistory.getSell_broker() == employee){
                                    brokerlist.replace(employee, ((Double)brokerlist.get(employee)) + exchangeHistory.getExchangePrize()*0.02);
                                }
                            }
                        }
                    }
                }
            }
        }
        }
        
        DefaultCategoryDataset dataset =  new DefaultCategoryDataset();
        for(Employee employee : brokerlist.keySet()){
            dataset.addValue((Double)brokerlist.get(employee),"",employee.getName());
        }
        StandardChartTheme mChartTheme = new StandardChartTheme("CN"); 
        mChartTheme.setExtraLargeFont(new Font("Arial", Font.BOLD, 20)); 
        mChartTheme.setLargeFont(new Font("Arial", Font.PLAIN, 15));
        mChartTheme.setRegularFont(new Font("Arial", Font.PLAIN, 15));
        ChartFactory.setChartTheme(mChartTheme);
        JFreeChart mChart = ChartFactory.createBarChart3D("Summary", "Broker", "Income", dataset);
        CategoryPlot mPlot = (CategoryPlot)mChart.getPlot();
        BarRenderer mRenderer = new BarRenderer();  
        mRenderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());  
        mRenderer.setItemLabelFont(new Font("Arial", Font.PLAIN, 15));  
        mRenderer.setItemLabelsVisible(true);  
        mPlot.setRenderer(mRenderer);  
        
        ChartFrame mChartFrame = new ChartFrame("Income", mChart);  
        mChartFrame.pack();  
        mChartFrame.setVisible(true); 
        
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
