/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.vista;


import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author jkari
 */
public class FrmRazonReimpresionDocs extends JPanel implements ActionListener {

    private static String seleccion;
    private ButtonGroup group;
    private static JDialog dialog;

    public FrmRazonReimpresionDocs(List<String> razones) { 
        super(new GridLayout(razones.size() + 2, 1));
        JLabel label = new JLabel("Razones de Reimpresión:");
        add(label);
        group = new ButtonGroup();
        for (String razon : razones) {
            JRadioButton rButton = new JRadioButton(razon);
            rButton.setActionCommand(razon);
            group.add(rButton);
            add(rButton);
        }        
        JButton button = new JButton("Aceptar");
        button.addActionListener(this);
        add(button);
        seleccion=null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(group.getSelection()!=null){
            seleccion = group.getSelection().getActionCommand();
            setVisible(false);
            dialog.dispose();
        }
    }

    /**
     * Crea y muestra el Dialogo y retorna el valor seleccionado cuando se cierra
     * el dialogo
     * @param parent Ventana padre
     * @param modal Tipo modal del Dialogo a mostrar
     * @param razones Listado de razones que va a mostrar el dialogo
     * @return La razon seleccionada por el usuario
     */
    public static String createAndShowGUI(Dialog parent, boolean modal, List<String> razones) {
        dialog = new JDialog(parent, modal);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(parent);
        JComponent newContentPane = new FrmRazonReimpresionDocs(razones);
        Border border = newContentPane.getBorder();
        Border margin = new EmptyBorder(10, 10, 10, 10);
        newContentPane.setBorder(new CompoundBorder(border, margin));
        newContentPane.setOpaque(true); //content panes must be opaque
        dialog.setContentPane(newContentPane);
        //Display the window.
        dialog.pack();
        dialog.setVisible(true);
        return seleccion;
    }

}
