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
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;

/**
 *
 * @author haywoosd
 */
public class StatusMonitorThread extends Thread {

    ExtendedWebBrowser webBrowser;
    boolean enabled = true;
    int ACWTime;
    int AUXTime;
    int WrapupTime;
    ArrayList<String> supervisorList = new ArrayList<String>();
    ArrayList<String> alertedUsers = new ArrayList<String>();
    ArrayList<String> alertedModes = new ArrayList<String>();
    boolean errorNoticeGiven = false;

    public StatusMonitorThread(ExtendedWebBrowser webBrowser1) {
        webBrowser = webBrowser1;
        loadOptions();
    }

    public void terminate() {
        enabled = false;
    }

    public final void loadOptions() {
        File file = new File(System.getProperty("user.home") + "\\AppData\\Roaming\\SuperToolkit\\Monitor_Settings.txt");
        if (file.exists()) {
            try {
                Scanner read = new Scanner(file);
                String temp = read.nextLine();
                String tempArray[] = temp.split(":");
                ACWTime = Integer.parseInt(tempArray[0]) * 60 + Integer.parseInt(tempArray[1]);
                temp = read.nextLine();
                tempArray = temp.split(":");
                AUXTime = Integer.parseInt(tempArray[0]) * 60 + Integer.parseInt(tempArray[1]);
                temp = read.nextLine();
                tempArray = temp.split(":");
                WrapupTime = Integer.parseInt(tempArray[0]) * 60 + Integer.parseInt(tempArray[1]);
                supervisorList.clear();
                while (read.hasNextLine()) {
                    supervisorList.add(read.nextLine());
                }
                read.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(StatusMonitorOptionsPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                PrintWriter write = new PrintWriter(file);
                write.println("01:00");
                write.println("15:00");
                write.println("01:00");
                write.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(StatusMonitorThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            loadOptions();
        }
    }

    private String getUserMode(int row) {
        if (((String) webBrowser.executeJavascriptWithResult("return frames[0].document.getElementById(\"tagents\").rows[" + row + "].children[7].innerHTML")).equals("AUX")) {
            return "AUX";
        } else {
            return (String) webBrowser.executeJavascriptWithResult("return frames[0].document.getElementById(\"tagents\").rows[" + row + "].children[3].innerHTML");
        }
    }

    private int getUserTime(int row) {
        String tempTime = (String) webBrowser.executeJavascriptWithResult("return frames[0].document.getElementById(\"tagents\").rows[" + row + "].children[4].innerHTML");
        String[] parsedTime = tempTime.split(":");
        int time = Integer.parseInt(parsedTime[0]) * 3600 + Integer.parseInt(parsedTime[1]) * 60 + Integer.parseInt(parsedTime[2]);
        return time;
    }

    private void giveAlert(String name, String mode) {
        JDialog diag = new JDialog(main.frame, "Status Monitor");
        diag.add(new StatusMonitorAlertPanel(name, mode, webBrowser));
        diag.pack();
        diag.setLocationRelativeTo(main.frame);
        diag.setResizable(false);
        diag.setVisible(true);
        alertedUsers.add(name);
        alertedModes.add(mode);
    }

    @Override
    public void run() {
        while (enabled) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        int tutorCount = ((Double) webBrowser.executeJavascriptWithResult("return frames[0].document.getElementById(\"tagents\").rows.length")).intValue();
                        for (int i = 0; i < alertedUsers.size(); i++) {
                            for (int i2 = 1; i2 < tutorCount; i2++) {
                                String tempName = (String) webBrowser.executeJavascriptWithResult("return frames[0].document.getElementById(\"tagents\").rows[" + i2 + "].children[0].innerHTML");
                                String listedName = tempName.substring(tempName.lastIndexOf("&nbsp;") + 6, tempName.length());
                                if (listedName.equals(alertedUsers.get(i))) {
                                    if (!alertedModes.get(i).equals(getUserMode(i2))) {
                                        alertedModes.remove(i);
                                        alertedUsers.remove(i);
                                    }
                                }
                            }
                        }
                        for (int i = 1; i < tutorCount; i++) {
                            String tempMode = getUserMode(i);
                            String tempName = (String) webBrowser.executeJavascriptWithResult("return frames[0].document.getElementById(\"tagents\").rows[" + i + "].children[0].innerHTML");
                            String listedName = tempName.substring(tempName.lastIndexOf("&nbsp;") + 6, tempName.length());
                            if (!supervisorList.contains(listedName)) {
                                if (!alertedUsers.contains(listedName)) {
                                    if (tempMode.equals("AUX")) {
                                        if (getUserTime(i) > AUXTime) {
                                            giveAlert(listedName, tempMode);
                                        }
                                    } else if (tempMode.equals("ACW")) {
                                        if (getUserTime(i) > ACWTime) {
                                            giveAlert(listedName, tempMode);
                                        }
                                    } else if (tempMode.equals("Wrapup")) {
                                        if (getUserTime(i) > WrapupTime) {
                                            giveAlert(listedName, tempMode);
                                        }
                                    }
                                }
                            }
                        }
                        errorNoticeGiven = false;
                    } catch (NullPointerException e) {
                        if (!errorNoticeGiven) {
                            System.out.println("Failed to get number of tutors for status monitor. Perhaps not on the right webpage?");
                            errorNoticeGiven = true;
                        }
                    }
                }
            });
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(StatusMonitorThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}