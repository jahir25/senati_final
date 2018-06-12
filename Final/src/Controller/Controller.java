/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Model;
import Model.Sesiones;
import View.Admin;
import View.Chef;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import View.Login;
import View.Moso;
import View.VerificarLogeo;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class Controller {
    
    Model ob;
    Sesiones data;
    Sesiones data2;
    public String LoginController(String usuario, String pass){
        ob = new Model();
        data = ob.Login(usuario, pass);
        String res;
        String sessionval = data.getValdsession();
        if (sessionval.equals("0")){
            res = "false";
            JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectos", "Error", JOptionPane.INFORMATION_MESSAGE);
        }else{
            String perfil = data.getPerfil();
            String flag = data.getFlagsesion();
            System.out.println(flag);
            if (flag.equals("1")) {
                if (perfil.equals("1")) {
                    System.out.println("es admin");
                    Admin adm = new Admin();
                    adm.obtenerSesion(data);
                    adm.show();
                }else if(perfil.equals("2")){
                    System.out.println("es chef");
                    Chef ch = new Chef();
                    ch.obtenerSesion(data);
                    ch.show();
                }else{
                    System.out.println("es moso");
                    Moso moso = new Moso();
                    moso.obtenerSesion(data);
                    moso.show();
                }
            }else{
                VerificarLogeo log = new VerificarLogeo();
                log.show();
                log.obtenerSesion(data.getValdsession(), data.getPerfil(), data.getFlagsesion(), data.getNombre(), data.getApellido(), data.getId(), usuario);
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
        
        
        String texto = "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "	<title>CORREO</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "		<table style=\"max-width:600px; padding: 10px; margin: 0 auto; border-collapse: collapse;\">\n" +
                        "			<tr>\n" +
                        "				<td style=\"background-color: white; text-align: left; padding: 0\">\n" +
                        "					<a href=\"https://web.facebook.com/jerry.contreras.716\">\n" +
                        "					<img width=\"50%\" style=\"display: block; margin: 1.5% 3%\" src=\"https://s33.postimg.cc/dchktargv/logo.png\">\n" +
                        "					</a>\n" +
                        "				</td>\n" +
                        "			</tr>\n" +
                        "			<tr>\n" +
                        "				<td style=\"background-color:#3374FF\">\n" +
                        "				<div style=\"color : white; margin: 1% 10% 1%; text-align: justify; font-family: sans-serif;font-size: 1em\">\n" +
                        "					<h3 style=\"color: white; text-align: center; margin: 0 0 7px \">! Bienvenidos a nuestra familia !</h3>\n" +
                        "					\n" +
                        "					</div>\n" +
                        "				</td>\n" +
                        "			</tr>\n" +
                        "			<td style=\"background-color:white\">\n" +
                        "				<div style=\"color : #252323; margin: 4% 10% 2%; text-align: justify; font-family: sans-serif;\">\n" +
                        "						<p style=\"margin: 2px; font-size: 1em\">\n" +
                        "						Somos una comunidad de comida rápida y deliciosa, nos encuentras en la ciudad de Lima,Perú.<br><br>\n" +
                        "\n" +
                        "						Tu Usuario es :  "+ user +" <br>\n" +
                        "						Tu Pass es : " + pass + "  \n" +
                        "\n" +
                        "						<br><br>\n" +
                        "						Te agradecemos por decidir formar parte de nuestra familia y acompañarnos en esta aventura de sabores maravillosos a probar.Además realizaremos una serie de actividades que nos preparara para su llegada. !Los Esperamos! <br>\n" +
                        "						Entre las actividades tenemos: </p>\n" +
                        "					<ul style=\"font-size: 1em; margin: 10px 0\">\n" +
                        "						<li>PROMOCIONES</li>\n" +
                        "						<li>DIAS CRIOLLOS</li>\n" +
                        "						<li>EVENTOS CORPOTIVOS</li>\n" +
                        "						<li>MARTES DE 2 X 1</li>\n" +
                        "						<li>MUCHAS SORPRESAS MÁS</li>\n" +
                        "					</ul>\n" +
                        "					<div style=\" width:100%,margin:20px 0; display: inline-block; text-align: center;\">\n" +
                        "					<img style=\"padding-left: 120px;width: 40px;margin: 5px\" src=\"https://s33.postimg.cc/mx17fyjb3/coffee-cup.png\">\n" +
                        "					<img style=\"padding: 0;width: 40px;margin: 5px\" src=\"https://s33.postimg.cc/w4tfwqayn/shop.png\">\n" +
                        "					<img style=\"padding: 0;width: 40px;margin: 5px\" src=\"https://s33.postimg.cc/eerrbpzyn/spaguetti.png\">\n" +
                        "					</div>\n" +
                        "					<br>\n" +
                        "					<br>\n" +
                        "					<p style=\"color: #002980; font-size: 12px; text-align: center;margin: 30px 0 0\"> RESTAURANT \"JAHIR & JERRY\" 2018 </p>\n" +
                        "				</div>\n" +
                        "			</td>\n" +
                        "		</tr>\n" +
                        "		</table>\n" +
                        "\n" +
                        "</body>\n" +
                        "</html>";
        
        
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
            message.setFrom(new InternetAddress("RestaurantSenatito"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(correo));
            message.setSubject("BIENVENIDO A NUESTRA FAMILIA.");
            message.setContent(texto, "text/html");

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
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        table.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        
        table.getColumnModel().getColumn(6).setMaxWidth(0);
        table.getColumnModel().getColumn(6).setMinWidth(0);
        table.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
        table.getTableHeader().getColumnModel().getColumn(6).setMinWidth(0);
        //LlenarTabla(table);
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
    
        
    public void RestablecerContraseña(String id, String nombre, String ape, String correo){
        
        ob = new Model();
        String contraseñaG, res;
        contraseñaG = GenerarContraseña();
        res = ob.RestablecerContraseña(id, contraseñaG);
        System.out.println("contraseña"+contraseñaG);
        if (res.equals("1")) {
            EnviarEmailPass(nombre, ape, correo, contraseñaG);
            JOptionPane.showMessageDialog(null, "Contraseña Restablecida", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
    
    public void  EnviarEmailPass(String nombre, String ape, String correo, String pass){
        String nombreCompleto = nombre + " " + ape;
        String texto = "Hola " + nombreCompleto + ", \nTu administrador reinicio tu contraseña \ntu nueva contraseña es: \npass: " + pass + 
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
            message.setFrom(new InternetAddress("RestaurantSenatitos"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(correo));
            message.setSubject("REINICIO DE CONTRAEÑA.");
            message.setText(texto);

            Transport.send(message);

            System.out.println("Reinicio");

        } catch (MessagingException e) {
                throw new RuntimeException(e);
        }
    
    }
    
    public String GenerarContraseña(){
    
        String chars = "abcxyz1234567890";
        Random rnd = new Random();
        char c = chars.charAt(rnd.nextInt(chars.length()));
        String contraseña = String.valueOf(c);
       
        return contraseña;
    }
    
    public String NuevaContraseña(String contraseña, String id, String usuario){
        ob = new Model();
        String res;
        System.out.println(usuario);
        res = ob.NuevoPass(id, contraseña);
        System.out.println(res);
        if (res.equals("1")) {
            LoginController(usuario, contraseña);
        }
        return res;
    }

    public void EliminarUsuario(String id) {
        ob = new Model();
        System.out.println(id);
        String res;
        res = ob.EliminarUsuario(id);
        System.out.println(res);
    }
    
}
