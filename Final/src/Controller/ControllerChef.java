package Controller;

import Model.Model;
import Model.ModelChef;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ControllerChef {
    ModelChef chef;
    
    public void CargarTabla(JTable table){
        chef = new ModelChef();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Pedido");
        model.addColumn("Id Detalle");
        model.addColumn("N° Mesa");
        model.addColumn("Plato");
        model.addColumn("Cantidad");
        model.addColumn("Observación");
        model.addColumn("Fecha del Pedido");
        model.addColumn("Estado");
        chef.CargarTabla(model);
        table.setModel(model);
        table.getColumnModel().getColumn(1).setMaxWidth(0);
        table.getColumnModel().getColumn(1).setMinWidth(0);
        table.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
        table.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
//        table.getColumnModel().getColumn(6).setMaxWidth(0);
//        table.getColumnModel().getColumn(6).setMinWidth(0);
//        table.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
//        table.getTableHeader().getColumnModel().getColumn(6).setMinWidth(0);
    }
    
    public void TomarPedido(String idPedido){
        chef = new ModelChef();
        chef.TomarPedido(idPedido);
    }
    
    public void PedidoRealizado(String idPedido){
        chef = new ModelChef();
        chef.PedidoRealizado(idPedido);
    }
    
}
