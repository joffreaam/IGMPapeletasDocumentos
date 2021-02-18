/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ReadAccessDlg.java
 *
 * Created on May 3, 2011, 11:25:55 AM
 */
package zebra;

import com.mot.rfid.api3.InvalidUsageException;
import com.mot.rfid.api3.MEMORY_BANK;
import com.mot.rfid.api3.OperationFailureException;
import com.mot.rfid.api3.TagAccess;
import com.mot.rfid.api3.TagData;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author CFRN78
 */
public class KillAccessDlg extends javax.swing.JDialog
                           implements DocumentListener {

    String tagID;

    /** Creates new form ReadAccessDlg */
    public KillAccessDlg(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        jTextFieldTagID.getDocument().addDocumentListener(this);

        tagID = RFIDMainDlg.rfidBase.getSelectedTagID();

        if (tagID == null) {
            jTextFieldTagID.setText("");
            jButtonAccessFilter.setEnabled(true);
        } else {
            jTextFieldTagID.setText(tagID);
            jButtonAccessFilter.setEnabled(false);

        }

        // initialize the password, offset and length to 0
        jTextFieldPwd.setText("0");

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButtonKill = new javax.swing.JButton();
        jButtonAccessFilter = new javax.swing.JButton();
        jTextFieldPwd = new javax.swing.JTextField();
        jTextFieldTagID = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Kill Tag");
        setResizable(false);

        jLabel2.setText("Kill Password (Hex)");

        jLabel1.setText("Tag ID (Hex)");

        jButtonKill.setMnemonic('K');
        jButtonKill.setText("Kill");
        jButtonKill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKillActionPerformed(evt);
            }
        });

        jButtonAccessFilter.setText("Access Filter");
        jButtonAccessFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAccessFilterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldTagID, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                            .addComponent(jTextFieldPwd, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(jButtonAccessFilter, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonKill, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldTagID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldPwd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonKill)
                    .addComponent(jButtonAccessFilter))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonKillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKillActionPerformed
        try {
            TagAccess tagAccess = new TagAccess();
            TagAccess.KillAccessParams killAccessParams = tagAccess.new KillAccessParams();

            killAccessParams.setKillPassword(Long.parseLong(jTextFieldPwd.getText(), 16));

            if (jTextFieldTagID.getText().length() > 0) {
                RFIDMainDlg.rfidBase.getMyReader().Actions.TagAccess.killWait(tagID, killAccessParams,
                        RFIDMainDlg.rfidBase.antennaInfo.getAntennaID() != null ? RFIDMainDlg.rfidBase.antennaInfo : null);
            } else {
                RFIDMainDlg.rfidBase.getMyReader().Actions.TagAccess.killEvent(killAccessParams, RFIDMainDlg.rfidBase.isAccessFilterSet ? RFIDMainDlg.rfidBase.accessFilter : null,
                        RFIDMainDlg.rfidBase.antennaInfo.getAntennaID() != null ? RFIDMainDlg.rfidBase.antennaInfo : null);
                
            }



            RFIDMainDlg.rfidBase.postStatusNotification(RFIDBase.API_SUCCESS, null);

        } catch (InvalidUsageException ex) {
            RFIDMainDlg.rfidBase.postStatusNotification(RFIDBase.PARAM_ERROR, ex.getVendorMessage());
        } catch (OperationFailureException ex) {
            RFIDMainDlg.rfidBase.postStatusNotification(ex.getStatusDescription(), ex.getVendorMessage());
        }
}//GEN-LAST:event_jButtonKillActionPerformed

    private void jButtonAccessFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAccessFilterActionPerformed
        AccessFilterDlg accessFilterDlg = new AccessFilterDlg(null, rootPaneCheckingEnabled);
        accessFilterDlg.setLocationRelativeTo(null);
        accessFilterDlg.show();
    }//GEN-LAST:event_jButtonAccessFilterActionPerformed

    // DocumentListener methods
    public void insertUpdate(DocumentEvent ev) {
        if (jTextFieldTagID.getText().length() == 0)
            jButtonAccessFilter.setEnabled(true);
        else
            jButtonAccessFilter.setEnabled(false);
    }

    public void removeUpdate(DocumentEvent ev) {
        if (jTextFieldTagID.getText().length() == 0)
            jButtonAccessFilter.setEnabled(true);
        else
            jButtonAccessFilter.setEnabled(false);
    }

    public void changedUpdate(DocumentEvent ev) {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                KillAccessDlg dialog = new KillAccessDlg(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAccessFilter;
    private javax.swing.JButton jButtonKill;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextFieldPwd;
    private javax.swing.JTextField jTextFieldTagID;
    // End of variables declaration//GEN-END:variables
}