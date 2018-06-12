package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.table.DefaultTableModel;

public class ModelReporte {
    Model ob;
    public Object[][] ConsultarPedido(){
        ResultSet res;
        Object[][] obj= null;
        ob = new Model();
        Connection con = ob.Conectar();
        try {
            String sql = "SELECT PE.IdPedidos, PE.IdMesa, PL.NomPlato, EPD.EstPedido, PD.Cantidad, PL.PrecPlato, (PD.Cantidad * PL.PrecPlato) AS SUB\n" +
            "FROM pedidos PE\n" +
            "INNER JOIN pedidodetalle PD ON PD.IdPedido = PE.IdPedidos\n" +
            "INNER JOIN platos PL ON PL.IdPlato = PD.IdPlato\n" +
            "INNER JOIN estadopedido EPD ON EPD.IdEstado = PD.IdEstado\n" +
            "ORDER BY IdPedidos";
            PreparedStatement smt = con.prepareStatement(sql);
            res = smt.executeQuery();
            ResultSetMetaData rd = res.getMetaData();
            int columnas= rd.getColumnCount();
            int rowcount = 0;
            int contador = 0;
            if (res.last()) {
              rowcount = res.getRow();
              res.beforeFirst();
            }
            obj = new Object[rowcount][columnas];
            while (res.next()) {
                for (int j = 0; j < columnas; j++) {
                    obj[contador][j] = res.getObject(j + 1);
                }
                contador++;
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return obj;
    }

    public void CargarTable(DefaultTableModel model) {
        ResultSet res;
        ob = new Model();
        Connection con = ob.Conectar();
        try {
            String sql = "SELECT PE.IdPedidos, PE.IdMesa, PL.NomPlato, EPD.EstPedido, PD.Cantidad, PL.PrecPlato, (PD.Cantidad * PL.PrecPlato) AS SUB\n" +
            "FROM pedidos PE\n" +
            "INNER JOIN pedidodetalle PD ON PD.IdPedido = PE.IdPedidos\n" +
            "INNER JOIN platos PL ON PL.IdPlato = PD.IdPlato\n" +
            "INNER JOIN estadopedido EPD ON EPD.IdEstado = PD.IdEstado\n" +
            "ORDER BY IdPedidos";
            PreparedStatement smt = con.prepareStatement(sql);
            res = smt.executeQuery();
            System.out.println(smt);
            ResultSetMetaData rd = res.getMetaData();
            int columnas= rd.getColumnCount();
            int rowcount = 0;
            int contador = 0;
            if (res.last()) {
              rowcount = res.getRow();
              res.beforeFirst();
            }
            while (res.next()) {
                Object obj[] = new Object[columnas];
                for (int j = 0; j < columnas; j++) {
                    obj[j] = res.getObject(j + 1);
                }
                model.addRow(obj);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
