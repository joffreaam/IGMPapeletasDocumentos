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
import com.mot.rfid.api3.LOCK_PRIVILEGE;
import com.mot.rfid.api3.LOCK_DATA_FIELD;
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
public class LockAccessDlg extends javax.swing.JDialog
                           implements DocumentListener {

    String tagID;
    /** Creates new form ReadAccessDlg */
    public LockAccessDlg(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        jTextFieldTagID.getDocument().addDocumentListener(this);

        tagID = RFIDMainDlg.rfidBase.getSelectedTagID();

        if (tagID == null)
        {
           jTextFieldTagID.setText("");
           jButtonAccessFilter.setEnabled(true);
        }
        else
        {
           jTextFieldTagID.setText(tagID);
           jButtonAccessFilter.setEnabled(false);

        }

        // select the EPC as default
        jComboBoxMemBank.setSelectedIndex(1);

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

        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButtonLock = new javax.swing.JButton();
        jButtonAccessFilter = new javax.swing.JButton();
        jComboBoxMemBank = new javax.swing.JComboBox();
        jTextFieldPwd = new javax.swing.JTextField();
        jTextFieldTagID = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxLockPrevilege = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lock Tag");
        setResizable(false);

        jLabel3.setText("Memory Bank");

        jLabel2.setText("Password (Hex)");

        jLabel1.setText("Tag ID (Hex)");

        jButtonLock.setMnemonic('L');
        jButtonLock.setText("Lock");
        jButtonLock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLockActionPerformed(evt);
            }
        });

        jButtonAccessFilter.setText("Access Filter");
        jButtonAccessFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAccessFilterActionPerformed(evt);
            }
        });

        jComboBoxMemBank.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kill Password", "Access Password", "EPC Memory", "TID Memory", "User Memory" }));

        jLabel4.setText("Lock Previlege");

        jComboBoxLockPrevilege.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Read-Write", "Permanent Lock", "Permanent unlock", "Unlock" }));

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
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldTagID, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                            .addComponent(jTextFieldPwd, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jComboBoxLockPrevilege, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBoxMemBank, javax.swing.GroupLayout.Alignment.LEADING, 0, 146, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(jButtonAccessFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonLock, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)))
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
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxMemBank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxLockPrevilege, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAccessFilter)
                    .addComponent(jButtonLock))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLockActionPerformed
        try {
            TagAccess tagAccess = new TagAccess();
            TagAccess.LockAccessParams lockAccessParams = tagAccess.new LockAccessParams();

           // LOCK_PRIVILEGE[] lockPrivilege = new LOCK_PRIVILEGE[5];
            //lockPrivilege[jComboBoxMemBank.getSelectedIndex()] = getPrivilege();

            LOCK_PRIVILEGE lockPrivilege=getPrivilege();

            LOCK_DATA_FIELD lockDataField=getLockDataField();
            lockAccessParams.setLockPrivilege(lockDataField,lockPrivilege);
            lockAccessParams.setAccessPassword(Long.parseLong(jTextFieldPwd.getText(), 16));

            if (jTextFieldTagID.getText().length() > 0) {
                RFIDMainDlg.rfidBase.getMyReader().Actions.TagAccess.lockWait(tagID, lockAccessParams,
                        RFIDMainDlg.rfidBase.antennaInfo.getAntennaID() != null ? RFIDMainDlg.rfidBase.antennaInfo : null);
            }
            else
            {
                RFIDMainDlg.rfidBase.getMyReader().Actions.TagAccess.lockEvent(lockAccessParams, RFIDMainDlg.rfidBase.isAccessFilterSet ? RFIDMainDlg.rfidBase.accessFilter : null,
                        RFIDMainDlg.rfidBase.antennaInfo.getAntennaID() != null ? RFIDMainDlg.rfidBase.antennaInfo : null);

            }

            RFIDMainDlg.rfidBase.postStatusNotification(RFIDBase.API_SUCCESS, null);

        } catch (InvalidUsageException ex) {
            RFIDMainDlg.rfidBase.postStatusNotification(RFIDBase.PARAM_ERROR, ex.getVendorMessage());
        } catch (OperationFailureException ex) {
            RFIDMainDlg.rfidBase.postStatusNotification(ex.getStatusDescription(), ex.getVendorMessage());
        }
}//GEN-LAST:event_jButtonLockActionPerformed

    private LOCK_PRIVILEGE getPrivilege()
    {
        LOCK_PRIVILEGE lockPrivilege = LOCK_PRIVILEGE.LOCK_PRIVILEGE_NONE;
        switch (jComboBoxLockPrevilege.getSelectedIndex())
        {
            //Read-Write
            case 0:
                lockPrivilege = LOCK_PRIVILEGE.LOCK_PRIVILEGE_READ_WRITE;
                break;
            //Permanent Lock
            case 1:
                lockPrivilege = LOCK_PRIVILEGE.LOCK_PRIVILEGE_PERMA_LOCK;
                break;
            //Permanent unlock
            case 2:
                lockPrivilege = LOCK_PRIVILEGE.LOCK_PRIVILEGE_PERMA_UNLOCK;
                break;
            //Unlock
            case 3:
                lockPrivilege = LOCK_PRIVILEGE.LOCK_PRIVILEGE_UNLOCK;
                break;
        }

        return lockPrivilege;
    }
    private LOCK_DATA_FIELD getLockDataField()
    {
        LOCK_DATA_FIELD lockDataField =LOCK_DATA_FIELD.LOCK_EPC_MEMORY;
        switch (jComboBoxMemBank.getSelectedIndex())
        {
            //Kill Password
            case 0:
            	lockDataField = LOCK_DATA_FIELD.LOCK_KILL_PASSWORD;
                break;
            //Access Password
            case 1:
            	lockDataField = LOCK_DATA_FIELD.LOCK_ACCESS_PASSWORD;
                break;
            //EPC Memory
            case 2:
            	lockDataField = LOCK_DATA_FIELD.LOCK_EPC_MEMORY;
                break;
            //TID Memory
            case 3:
            	lockDataField = LOCK_DATA_FIELD.LOCK_TID_MEMORY;
                break;
            //User Memory
            case 4:
            	lockDataField = LOCK_DATA_FIELD.LOCK_USER_MEMORY;
                break;
        }

        return lockDataField;
    }
    private void jButtonAccessFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAccessFilterActionPerformed
        AccessFilterDlg accessFilterDlg = new AccessFilterDlg(null, rootPaneCheckingEnabled);
        accessFilterDlg.setLocationRelativeTo(null);
        accessFilterDlg.show();
    }//GEN-LAST:event_jButtonAccessFilterActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LockAccessDlg dialog = new LockAccessDlg(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAccessFilter;
    private javax.swing.JButton jButtonLock;
    private javax.swing.JComboBox jComboBoxLockPrevilege;
    private javax.swing.JComboBox jComboBoxMemBank;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextFieldPwd;
    private javax.swing.JTextField jTextFieldTagID;
    // End of variables declaration//GEN-END:variables

}
