package Model;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Model {
    
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
    
    public ArrayList Login(String usuario, String pass){
        ArrayList<String> data = new ArrayList<String>();
        Connection con = Conectar();
        String correo = usuario;
        Config cof = new Config();
        String llave = cof.pass();
        ResultSet rs = null;
        try {
            String sql = "SELECT COUNT(*), IdTipoUsuario FROM usuario WHERE (LoginUsuario = ? OR CorreoUsuario = ?) AND CAST(AES_DECRYPT(Contraseña, ?) AS CHAR) = ?";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, usuario);
            smt.setString(2, correo);
            smt.setString(3, llave);
            smt.setString(4, pass);
            rs = smt.executeQuery();
            while (rs.next()) {
                data.add(rs.getObject(1).toString());
                data.add(rs.getObject(2).toString());
            }
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return data;
    }
    
    public String CrearUsuario(String nom, String ape, String user, String correo, String pass, String tipo){
        Integer res = 0;
        Connection con = Conectar();
        Config cof = new Config();
        String llave = cof.pass();
        try {
            String sql = "INSERT INTO usuario (NomUsuario, ApeUsuario, LoginUsuario, CorreoUsuario, idTipoUsuario, Contraseña) VALUES (?, ?, ?, ?, ?, AES_ENCRYPT(?, ?))";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, nom);
            smt.setString(2, ape);
            smt.setString(3, user);
            smt.setString(4, correo);
            smt.setString(5, tipo);
            smt.setString(6, pass);
            smt.setString(7, llave);
            res = smt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error insert usuario" + e);
        }
        return res.toString();
    }
    
    public ResultSet GetUsuarios(DefaultTableModel model){
        Connection con = Conectar();
        ResultSet rs;
        rs = null;
        try {
            String sql = "SELECT USU.IdUsuario, USU.NomUsuario, USU.ApeUsuario, USU.LoginUsuario,\n" +
            "(CASE\n" +
            "    WHEN USU.CorreoUsuario IS NULL THEN 'no cuenta con correo'\n" +
            "    ELSE USU.CorreoUsuario\n" +
            "END),\n" +
            "TP.TipoUsuario, TP.IdTipoUsuario FROM usuario USU INNER JOIN tipousuario TP ON TP.IdTipoUsuario = USU.IdTipoUsuario";
            PreparedStatement smt = con.prepareStatement(sql);
            rs = smt.executeQuery();
            
            while (rs.next()) {
                Object [] fila = new Object[7];
                for (int i = 0; i < 7; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                model.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }
    
    public String UpdateUsuario(String id, String nom, String ape, String user, String correo, String tipo){
        Integer res = 0;
        Connection con = Conectar();
        
        try {
            String sql = "UPDATE usuario SET NomUsuario = ?, ApeUsuario = ?, LoginUsuario = ?, CorreoUsuario = ?, idTipoUsuario = ? WHERE IdUsuario = ?";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, nom);
            smt.setString(2, ape);
            smt.setString(3, user);
            smt.setString(4, correo);
            smt.setString(5, tipo);
            smt.setString(6, id);
            System.out.println(smt);
            res = smt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error update user" + e);
        }
        
        return res.toString();
    }
    
}
