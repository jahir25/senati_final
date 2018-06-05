package Model;

import com.mysql.jdbc.Connection;
import java.sql.ResultSet;

public class ModelChef {

    Model ob = new Model();
    
    public void CargarTabla(){
        Connection con = ob.Conectar();
        ResultSet res;
        
        try {
            String sql = "SELECT * FROM pedidos "
        } catch (Exception e) {
        }
        
    }
    
}
