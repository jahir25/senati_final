/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Model;
import View.Admin;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import View.Login;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class Controller {
    
    Model ob;
    
    public String LoginController(String usuario, String pass){
        String res;
        ob = new Model();
        ArrayList rs = ob.Login(usuario, pass);
        String val =  rs.get(0).toString();
        System.out.println(val);
        if (val.equals("0")){
            res = "false";
            JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectos", "Error", JOptionPane.INFORMATION_MESSAGE);
        }else{
            String tipo = rs.get(1).toString();
            if (tipo.equals("1")) {
                Admin adm = new Admin();
                adm.show();
            }
            res = "true";
        }
        return res;
    }
    
    public String CrearCuenta(String nom, String ape, String correo, String user, String pass, String tipo){
        String res;
        
        ob = new Model();
        res = ob.CrearUsuario(nom, ape, user, correo, pass, tipo);
        if (res.equals("1")) {
            EnviarEmail(nom, user, pass, correo);
        }else{
            System.out.println("Error al crear el usuario");
        }
        return res;
    }
    
    public void EnviarEmail(String nombre, String user, String pass, String correo){
        
        
        String texto = "Hola " + nombre + ", \nTe damos la bienvenida a nuestra familia \ntus datos de ingreso son: \n\nuser: "+ user +"\npass: " + pass + 
                "\n\nRecuerda que tambien puedes ingresar con tu correo electronico. \n\nSaludos Area Administrativa.";
        
        final String username = "senati.final@gmail.com";
        final String password = "Senati01";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("senati.final@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(correo));
            message.setSubject("BIENVENIDO A NUESTRA FAMILIA.");
            message.setText(texto);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
                throw new RuntimeException(e);
        }
    }
    
    public void LlenarTabla(JTable table){
        DefaultTableModel model = new DefaultTableModel();
        ob = new Model();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Usuario Logout");
        model.addColumn("Correo Electronico");
        model.addColumn("Tipo de Usuario");
        model.addColumn("ID tipo");
        ob.GetUsuarios(model);
        table.setModel(model);   
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(6).setMaxWidth(0);
    }
    
    public void EditarUsuario(String id, String nom, String ape, String correo, String user,String tipo, JTable table){
        
        ob = new Model();
        
        String res;
        
        res = ob.UpdateUsuario(id, nom, ape, user, correo, tipo);
        System.out.println(res);
        if (res.equals("1")) {
            LlenarTabla(table);
        }
        
    }
    
}
