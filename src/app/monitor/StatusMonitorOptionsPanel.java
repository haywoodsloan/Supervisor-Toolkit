/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.monitor;

import app.browser.ExtendedWebBrowser;
import app.main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author haywoosd
 */
public class StatusMonitorOptionsPanel extends javax.swing.JPanel {

    /**
     * Creates new form StatusMonitorOptionsPanel
     */
    String disableButtonText;
    String enableButtonText;
    ExtendedWebBrowser webBrowser;

    public StatusMonitorOptionsPanel(ExtendedWebBrowser webBrowser1) {
        webBrowser = webBrowser1;
        if (webBrowser1.isMonitorEnabled()) {
            disableButtonText = "Disable";
            enableButtonText = "Apply";
        } else {
            disableButtonText = "Cancel";
            enableButtonText = "Enable";
        }
        initComponents();
        loadSettings();
    }

    private void saveSettings() {
        File file = new File(System.getProperty("user.home") + "\\AppData\\Roaming\\SuperToolkit\\Monitor_Settings.txt");
        try {
            PrintWriter write = new PrintWriter(file);
            write.println(ACWMinutes.getText() + ":" + ACWSeconds.getText());
            write.println(AUXMinutes.getText() + ":" + AUXSeconds.getText());
            write.println(WrapupMinutes.getText() + ":" + WrapupSeconds.getText());
            for (String name : SupervisorsList.getText().split("\n")) {
                write.println(name);
            }
            write.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StatusMonitorOptionsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadSettings() {
        File file = new File(System.getProperty("user.home") + "\\AppData\\Roaming\\SuperToolkit\\Monitor_Settings.txt");
        if (file.exists()) {
            try {
                Scanner read = new Scanner(file);
                String temp = read.nextLine();
                String tempArray[] = temp.split(":");
                ACWMinutes.setText(tempArray[0]);
                ACWSeconds.setText(tempArray[1]);
                temp = read.nextLine();
                tempArray = temp.split(":");
                AUXMinutes.setText(tempArray[0]);
                AUXSeconds.setText(tempArray[1]);
                temp = read.nextLine();
                tempArray = temp.split(":");
                WrapupMinutes.setText(tempArray[0]);
                WrapupSeconds.setText(tempArray[1]);
                while (read.hasNextLine()) {
                    SupervisorsList.append(read.nextLine() + "\n");
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(StatusMonitorOptionsPanel.class.getName()).log(Level.SEVERE, null, ex);
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

        disableButton = new javax.swing.JButton();
        enableButton = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ACWMinutes = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ACWSeconds = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        AUXSeconds = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        AUXMinutes = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        WrapupSeconds = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        WrapupMinutes = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        SupervisorsList = new javax.swing.JTextArea();

        disableButton.setText(disableButtonText);
        disableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disableButtonActionPerformed(evt);
            }
        });

        enableButton.setText(enableButtonText);
        enableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enableButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Max ACW time:");

        jLabel2.setText("Max AUX time:");

        jLabel3.setText("Max Wrapup time:");

        ACWMinutes.setText("01");
        ACWMinutes.setMinimumSize(new java.awt.Dimension(25, 20));

        jLabel4.setText("minutes,");

        ACWSeconds.setText("00");

        jLabel5.setText("seconds");

        jLabel6.setText("seconds");

        AUXSeconds.setText("00");

        jLabel7.setText("minutes,");

        AUXMinutes.setText("15");
        AUXMinutes.setMinimumSize(new java.awt.Dimension(25, 20));

        jLabel8.setText("seconds");

        WrapupSeconds.setText("00");

        jLabel9.setText("minutes,");

        WrapupMinutes.setText("01");
        WrapupMinutes.setMinimumSize(new java.awt.Dimension(25, 20));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ACWMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ACWSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AUXMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AUXSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(WrapupMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(WrapupSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ACWMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(ACWSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(AUXMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(AUXSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(WrapupMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(WrapupSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Times", jPanel1);

        SupervisorsList.setColumns(20);
        SupervisorsList.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        SupervisorsList.setRows(5);
        jScrollPane1.setViewportView(SupervisorsList);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Supervisors", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(enableButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(disableButton)
                .addContainerGap())
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(disableButton)
                    .addComponent(enableButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void enableButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enableButtonActionPerformed
        if (webBrowser.isMonitorEnabled()) {
            saveSettings();
            webBrowser.updateMonitor();
        } else {
            saveSettings();
            webBrowser.enableMonitor();
            main.ModifyOptions(false, "S", "S", webBrowser);
        }
        SwingUtilities.getRoot(this).setVisible(false);
    }//GEN-LAST:event_enableButtonActionPerformed

    private void disableButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disableButtonActionPerformed
        if (webBrowser.isMonitorEnabled()) {
            webBrowser.disableMonitor();
            main.ModifyOptions(true, "S", null, webBrowser);
        }
        SwingUtilities.getRoot(this).setVisible(false);
    }//GEN-LAST:event_disableButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ACWMinutes;
    private javax.swing.JTextField ACWSeconds;
    private javax.swing.JTextField AUXMinutes;
    private javax.swing.JTextField AUXSeconds;
    private javax.swing.JTextArea SupervisorsList;
    private javax.swing.JTextField WrapupMinutes;
    private javax.swing.JTextField WrapupSeconds;
    private javax.swing.JButton disableButton;
    private javax.swing.JButton enableButton;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}