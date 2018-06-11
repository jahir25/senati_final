package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.table.DefaultTableModel;

public class ModeloMoso {
    Model ob = new Model();
    public Object[] TraerPedido(String id){
        ResultSet res;
        String rs = "";
        Connection con = ob.Conectar();
        Object[] obj = new Object[4];
        try {
            //System.out.println(id);
            String sql = "SELECT IdPedidos, IdMesa, FechaHora, COUNT(*) FROM pedidos WHERE IdEstadoPedidos = '1' AND IdMEsa = ?";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, id);
            //System.out.println("query "+ smt);
            res = smt.executeQuery();
            while (res.next()) {
                
                rs = res.getObject(4).toString();
                for (int i = 0; i < 4; i++) {
                    obj[i] = res.getObject(i + 1);
                }
            }
        } catch (Exception e) {
            System.out.println("Error "+ e);
        }
        return obj;
    }
    
    public void GetPlato(DefaultTableModel model){
        ResultSet res;
        Connection con = ob.Conectar();
        try {
            String sql = "SELECT IdPlato, NomPlato, DescPlato, PrecPlato FROM platos";
            PreparedStatement smt = con.prepareStatement(sql);
            res = smt.executeQuery();
            while (res.next()) {
                Object[] obj = new Object[4];
                for (int i = 0; i < 4; i++) {
                    obj[i] = res.getObject(i + 1);
                }
                model.addRow(obj);
            }
        } catch (Exception e) {
            //System.out.println(e);
        }
    }
    
    public Object[][] GetDetallePedido(String idPedido){
        ResultSet res;
        Connection con = ob.Conectar();
        Object obj[][] = null;
        try {
            String sql = "SELECT * FROM pedidodetalle PD \n" +
            "INNER JOIN platos PL ON PL.IdPlato = PD.IdPlato\n" +
            "WHERE IdPedido = ? AND IdEstado != 4";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, idPedido);
            int cont = 1;
            int cont2 = 0;
            res = smt.executeQuery();
            ResultSetMetaData rd = res.getMetaData();
            int columnas= rd.getColumnCount();
            int rowcount = 0;
            if (res.last()) {
              rowcount = res.getRow();
              res.beforeFirst();
            }
            obj = new Object[rowcount][columnas];
            //System.out.println(smt);
            while (res.next()) {
                for (int i = 0; i < columnas; i++) {
                    //System.out.println(res.getObject(i+1));
                    obj[cont2][i] = res.getObject(i+1);
                }
                cont2++;
                cont++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return obj;
    }

    public int MandarPedido(String idPlato, int cantidad, String idPedido, String observacion) {
        int res = 0;
        Connection con = ob.Conectar();
        try {
            String sql = "INSERT INTO pedidodetalle (IdPedido, IdPlato, IdEstado, Cantidad, Observación) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, idPedido);
            smt.setString(2, idPlato);
            smt.setInt(3, 1);
            smt.setInt(4, cantidad);
            smt.setString(5, observacion);
            res = smt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return res;
    }

    public int CrearPedido(String idmesa) {
        int res = 0;
        Connection con = ob.Conectar();
        try {
            String sql = "INSERT INTO pedidos (IdMesa, IdEstadoPedidos) VALUES (?, 1)";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, idmesa);
            res = smt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return res;
    }

    public int CancelarPedido(String idDetallePedido) {
        int res = 0;
        int estado;
        Connection con = ob.Conectar();
        estado = ConsultarEstadoPedido(idDetallePedido);
        if (estado != 2) {
            try {
                String sql = "UPDATE pedidodetalle SET IdEstado = 4 WHERE IdDetalle = ?";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setString(1, idDetallePedido);
                res = smt.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return res;
    }
    
    public int ConsultarEstadoPedido(String idDetallePedido){
        ResultSet res = null;
        Connection con = ob.Conectar();
        int Estado = 0;
        try {
            String sql = "SELECT IdEstado FROM pedidodetalle WHERE IdDetalle = ?";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, idDetallePedido);
            res = smt.executeQuery();
            while (res.next()) {
                Estado = (int) res.getObject(1);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return Estado;
    }

    public int CerrarPedido(String idPedido) {
        int res = 0;
        int cont = 0;
        Connection con = ob.Conectar();
        cont = ConsultarPlato(idPedido);
        if (cont == 0) {
            try {
                String sql = "UPDATE pedidos SET IdEstadoPedidos = 2 WHERE IdPedidos = ?";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setString(1, idPedido);
                res = smt.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        
        return res;
    }
    
    public int ConsultarPlato(String idPedido){
        int cont = 0;
        ResultSet res = null;
        Connection con = ob.Conectar();
        try {
            String sql = "SELECT COUNT(*) FROM pedidodetalle WHERE IdPedido = ? AND IdEstado IN(1,2)";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, idPedido);
            res = smt.executeQuery();
            while (res.next()) {
                cont = (int) res.getObject(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Cantidad de pedido "+ cont);
        return cont;
    }
    
    public int UpdateMesaDisponible (String IdMesa){
        int res = 0;
        Connection con = ob.Conectar();
        try {
            String sql = "UPDATE mesas SET IdEstadoMesa =  2 WHERE IdMesa = ?";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, IdMesa);
            res = smt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return res;
    }
    
}
