package app.alarms;

import java.awt.KeyboardFocusManager;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlarmsEditPanel extends javax.swing.JPanel {

    private int entryNumber = 0;
    String alarmsFile = System.getProperty("user.home") + "\\AppData\\Roaming\\SuperToolkit\\Alarms.txt";
    
    private void loadAlarms() {

        try {

            if (!Files.exists(Paths.get(alarmsFile))) {

                System.out.println("Alarms file not found attemping to create default");
                Files.createFile(Paths.get(alarmsFile));
            } else {

                List<String> fileLines = Files.readAllLines(Paths.get(alarmsFile));

                for (int i = 0; i < fileLines.size() - 1; i += 4) {

                    int hour = Integer.parseInt(fileLines.get(i));
                    int minute = Integer.parseInt(fileLines.get(i + 1));
                    int period = Integer.parseInt(fileLines.get(i + 2));
                    String name = fileLines.get(i + 3);

                    System.out.println("Adding new alarm from file. " + name);
                    addEntry(hour, minute, period, name);
                }
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(AlarmsEditPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AlarmsEditPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AlarmsEditPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void writeAlarm(int hour, int minute, int period, String name) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(alarmsFile, true))) {

            writer.println(Integer.toString(hour));
            writer.println(Integer.toString(minute));
            writer.println(Integer.toString(period));
            writer.println(name);

        } catch (IOException ex) {

            Logger.getLogger(AlarmsEditPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addEntry(int hour, int minute, int period, String name) {
        
        GregorianCalendar alarmTime = new GregorianCalendar();
        
        alarmTime.set(Calendar.HOUR, hour);
        alarmTime.set(Calendar.MINUTE, minute);
        alarmTime.set(Calendar.SECOND, 0);
        alarmTime.set(Calendar.AM_PM, period);
        
        if(alarmTime.before(GregorianCalendar.getInstance())){
            alarmTime.add(Calendar.DATE, 1);
        }
        
        AlarmTask alarmTask = new AlarmTask(name, entryNumber);
        AlarmTask.schedule(alarmTask, alarmTime.getTime());
        
        AlarmsEntryPanel alarmEntry = new AlarmsEntryPanel(hour, minute, period, name, entryNumber);
        System.out.println("Adding new alarm entry: " + name);

        entryContainerPanel.add(alarmEntry);
        entryContainerPanel.validate();
        entryContainerScrollPane.validate();

        entryNumber++;
    }

    public void removeEntry(int number) {
        System.out.println("Removing alarm entry: " + number);

        AlarmTask.unschedule(number);
        
        try {

            List<String> fileLines = Files.readAllLines(Paths.get(alarmsFile));

            try (PrintWriter writer = new PrintWriter(new FileWriter(alarmsFile))) {

                for (int i = 0; i < (fileLines.size() - 1) / 4; i++) {

                    if (i != number) {

                        writer.println(fileLines.get(i * 4));
                        writer.println(fileLines.get(i * 4 + 1));
                        writer.println(fileLines.get(i * 4 + 2));
                        writer.println(fileLines.get(i * 4 + 3));
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AlarmsEntryPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(AlarmsEntryPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AlarmsEntryPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        entryContainerPanel.remove(number);
        
        for (int i = number; i < entryContainerPanel.getComponentCount(); i++) {
            System.out.println("Setting entry " + (i + 1) + " to be " + i);
            ((AlarmsEntryPanel) entryContainerPanel.getComponent(i)).setEntryNumber(i);
        }
        
        entryNumber--;
    }

    public AlarmsEditPanel() {
        initComponents();
        loadAlarms();
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
        hourSelect = new javax.swing.JComboBox();
        minuteSelect = new javax.swing.JComboBox();
        periodSelect = new javax.swing.JComboBox();
        addButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        entryContainerScrollPane = new javax.swing.JScrollPane();
        entryContainerPanel = new javax.swing.JPanel();
        nameSelect = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Alarms");

        hourSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        minuteSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60" }));

        periodSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AM", "PM" }));

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        jLabel2.setText(":");

        entryContainerScrollPane.setBorder(null);

        entryContainerPanel.setLayout(new javax.swing.BoxLayout(entryContainerPanel, javax.swing.BoxLayout.PAGE_AXIS));
        entryContainerScrollPane.setViewportView(entryContainerPanel);

        nameSelect.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nameSelect.setText("Alarm Name");
        nameSelect.setToolTipText("");
        nameSelect.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nameSelectFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nameSelectFocusLost(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(entryContainerScrollPane)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(nameSelect)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hourSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minuteSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(periodSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(addButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hourSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minuteSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(periodSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton)
                    .addComponent(jLabel2)
                    .addComponent(nameSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(entryContainerScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        System.out.println("Manually adding an alarm entry");

        addEntry(hourSelect.getSelectedIndex() + 1, minuteSelect.getSelectedIndex(), periodSelect.getSelectedIndex(), nameSelect.getText());
        writeAlarm(hourSelect.getSelectedIndex() + 1, minuteSelect.getSelectedIndex(), periodSelect.getSelectedIndex(), nameSelect.getText());

        nameSelect.setText("Alarm Name");
    }//GEN-LAST:event_addButtonActionPerformed

    private void nameSelectFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameSelectFocusGained
        if (nameSelect.getText().equals("Alarm Name")) {
            nameSelect.setText("");
        }
    }//GEN-LAST:event_nameSelectFocusGained

    private void nameSelectFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameSelectFocusLost
        if (nameSelect.getText().trim().equals("")) {

            nameSelect.setText("Alarm Name");
            KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
        }
    }//GEN-LAST:event_nameSelectFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JPanel entryContainerPanel;
    private javax.swing.JScrollPane entryContainerScrollPane;
    private javax.swing.JComboBox hourSelect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JComboBox minuteSelect;
    private javax.swing.JTextField nameSelect;
    private javax.swing.JComboBox periodSelect;
    // End of variables declaration//GEN-END:variables
}
