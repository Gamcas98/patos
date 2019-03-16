/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.de.repuestos;

import javax.swing.JFrame;

/**
 *
 * @author Kevin
 */
public class ControlDeRepuestos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FrmPrincipal frmPrincipal = new FrmPrincipal();
        frmPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frmPrincipal.setVisible(true);
    }
    
}
