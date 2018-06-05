/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ModelMesas;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ALUMNO
 */
public class ControllerMesas {
    ModelMesas ob;
    
      public void LlenarTabla(JTable table){
        DefaultTableModel ModelMesas = new DefaultTableModel();
        ob = new ModelMesas();
        ModelMesas.addColumn("ID");
        ModelMesas.addColumn("N° de Mesas");
        ModelMesas.addColumn("N° de Asientos");
        ModelMesas.addColumn("Estado");
        ob.GetMesas(ModelMesas);
        table.setModel(ModelMesas);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        table.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

    }
      
      public void EditarMesas(String id, String NumMesas, String NumAsientos, String EstaMesa,JTable table){
        
         ob = new ModelMesas();
        
        String res;
        
        res = ob.UpdateMesas(id,NumMesas,NumAsientos,EstaMesa);
        System.out.println(res);
        if (res.equals("1")) {
            LlenarTabla(table);
        }
      
}
      
         public String GuardarMesas(String NumMesas, String NumAsientos, String EstaMesa){
        String res;
        
        ob = new ModelMesas();
        res = ob.GuardarMesas( NumMesas, NumAsientos, EstaMesa);
        //if (res.equals("1")) {
         // Registrar(NumMesas,  NumAsientos,EstaMesa);
        //}else{
        //  System.out.println("Error al Registrar Mesa");
       // }
        return res;
    }
}
