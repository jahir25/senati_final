package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ModelMesas {
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
   
    
        public ResultSet GetMesas(DefaultTableModel model){
        Connection con = Conectar();
        ResultSet rs;
        rs = null;
        try {
            String sql = "SELECT IdMesa,NumMesa,NumAsientos,EstaMesa FROM mesas";
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
        public String GuardarMesas(String NumMesa, String NumAsientos, String EstaMesa){
        Integer res = 0;
        Connection con = Conectar();
        Config cof = new Config();
        String llave = cof.pass();
        try {
            String sql = "INSERT INTO mesas (NumMesa, NumAsientos, EstaMesa) VALUES (?, ?, ?)";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, NumMesa);
            smt.setString(2, NumAsientos);
            smt.setString(3, EstaMesa);
            res = smt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error insert mesas" + e);
        }
        return res.toString();
            }
            
        public String UpdateMesas( String id,String NumMesa, String NumAsientos, String EstaMesa){
        Integer res = 0;
        Connection con = Conectar();
        
        try {
            String sql = "UPDATE mesas SET NumMesa = ?, NumAsientos = ?, EstaMesa = ? WHERE IdMesa = ?";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, NumMesa);
            smt.setString(2, NumAsientos);
            smt.setString(3, EstaMesa);
            
            System.out.println(smt);
            res = smt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error update user" + e);
        }
        
        return res.toString();
    }
 }
