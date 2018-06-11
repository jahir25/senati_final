package Model;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class ModelChef {

    Model ob = new Model();
    
    public void CargarTabla(DefaultTableModel model){
        Connection con = ob.Conectar();
        ResultSet res;
        
        try {
            String sql = "SELECT PD.IdPedido, PD.IdDetalle, NumMesa, NomPlato, PD.Cantidad, PD.Observación, PD.FechaPedido, EstPedido\n" +
            "FROM pedidoDetalle PD\n" +
            "INNER JOIN pedidos PE ON PE.IdPedidos = PD.IdPedido\n" +
            "INNER JOIN platos PL ON PL.IdPlato = PD.IdPlato\n" +
            "INNER JOIN estadopedido ESP ON ESP.IdEstado = PD.IdEstado\n" +
            "INNER JOIN mesas MS ON MS.IdMesa = PE.IdMesa";
            //System.out.println(sql);
            PreparedStatement smt = con.prepareStatement(sql);
            res = smt.executeQuery();
            while (res.next()) {
                Object[] obj = new Object[7];
                for (int i = 0; i < 7; i++) {
                    obj[i] = res.getObject(i + 1).toString();
                }
                model.addRow(obj);
            }
        } catch (Exception e) {
            System.out.println("Error pedido carga" + e);
        }
        
    }
    
    public String TomarPedido(String id){
        Integer res = null;
        Connection con = ob.Conectar();
            
        try {
            String sql = "UPDATE pedidoDetalle SET IdEstado = 2 WHERE IdDetalle = ?";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, id);
            res = smt.executeUpdate();
        } catch (Exception e) {
        }
        return res.toString();
        
    }
    
    public String PedidoRealizado(String id){
        Integer res = null;
        Connection con = ob.Conectar();
            
        try {
            String sql = "UPDATE pedidoDetalle SET IdEstado = 3 WHERE IdDetalle = ?";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, id);
            res = smt.executeUpdate();
        } catch (Exception e) {
        }
        return res.toString();
        
    }
    
}
