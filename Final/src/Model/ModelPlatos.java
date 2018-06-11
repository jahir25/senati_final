/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ALUMNO
 */
public class ModelPlatos {
       public Connection Conectar(){
        Connection con = null;
        try {
            String url = "jdbc:mysql://localhost:3306/senati_final";
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, "root", "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return con;
    }
   
    
        public ResultSet GetPlatos(DefaultTableModel model){
        Connection con = Conectar();
        ResultSet rs;
        rs = null;
        try {
            String sql = "SELECT IdPlato,NomPlato,DescPlato,PrecPlato FROM platos";
            PreparedStatement smt = con.prepareStatement(sql);
            rs = smt.executeQuery();
            System.out.println(rs);
            while (rs.next()) {
                Object [] fila = new Object[4];
                for (int i = 0; i < 4; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                model.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }
        public String GuardarPlatos(String NomPlato, String DescPlato, String PrecPlato){
        Integer res = 0;
        Connection con = Conectar();
        Config cof = new Config();
        String llave = cof.pass();
        try {
            String sql = "INSERT INTO platos (NomPlato, DescPlato, PrecPlato) VALUES (?, ?, ?)";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, NomPlato);
            smt.setString(2, DescPlato);
            smt.setString(3, PrecPlato);
            res = smt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error insert mesas" + e);
        }
        return res.toString();
            }
            
        public String UpdatePlatos( String IdPlato,String NomPlato, String DescPlato, String PrecPlato){
        Integer res = 0;
        Connection con = Conectar();
        
        try {
            String sql = "UPDATE platos SET NomPlato = ?, DescPlato = ?, PrecPlato = ? WHERE IdPlato = ?";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, NomPlato);
            smt.setString(2, DescPlato);
            smt.setString(3, PrecPlato);
            smt.setString(4, IdPlato);
            
            System.out.println(smt);
            res = smt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error update user" + e);
        }
        
        return res.toString();
    }
}
