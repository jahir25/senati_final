/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ModelPlatos;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ALUMNO
 */
public class ControllerPlatos {
        ModelPlatos ob;
    
      public void LlenarTabla(JTable table){
        DefaultTableModel ModelPlatos = new DefaultTableModel();
        ob = new ModelPlatos();
        ModelPlatos.addColumn("ID");
        ModelPlatos.addColumn("Nombre de Plato");
        ModelPlatos.addColumn("Descripción");
        ModelPlatos.addColumn("Precio");
        ob.GetPlatos(ModelPlatos);
        table.setModel(ModelPlatos);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        table.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

    }
      
      public void EditarPlatos(String id, String NomPlato, String DescPlato, String PrecPlato,JTable table){
        
         ob = new ModelPlatos();
        
        String res;
        
        res = ob.UpdatePlatos(id,NomPlato,DescPlato,PrecPlato);
        System.out.println(res);
        if (res.equals("1")) {
            LlenarTabla(table);
        }
      
}
      
         public String GuardarPlatos(String NumMesas, String NumAsientos, String EstaMesa){
        String res;
        
        ob = new ModelPlatos();
        res = ob.GuardarPlatos( NumMesas, NumAsientos, EstaMesa);
        //if (res.equals("1")) {
         // Registrar(NumMesas,  NumAsientos,EstaMesa);
        //}else{
        //  System.out.println("Error al Registrar Mesa");
       // }
        return res;
    }
}
