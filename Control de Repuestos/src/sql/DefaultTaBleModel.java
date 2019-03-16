/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1.sql;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joses
 */
public class DefaultTaBleModel extends DefaultTableModel{
    
    
    //este metodo sirve para bloquar las celdas para que no sean editables
    //el metodo fue sobrecargado por que las tablas las manejo desde otra clase
    
       @Override
    public boolean isCellEditable(int row, int column){
    if(column==-1000){
        return true;
    }
    return false;
    }
}
     
    
