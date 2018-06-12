package Controller;

import Model.ModeloMoso;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class ControllerMoso extends MouseAdapter{
    ModeloMoso ob = new ModeloMoso();
    int column, filas;
    public String idPedidoSeleccionado = "";
    JLabel pedidos[][];
    public Object[] TraerPedido(String idmesa){
        String res = "";
        Object[] obj = new Object[4];
        obj = ob.TraerPedido(idmesa);
        return obj;
    }
    
    public void LlenarTabla(JTable table){
        System.out.println("table");
        try {
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Plato");
            model.addColumn("Descripción");
            model.addColumn("Precio");
            ob.GetPlato(model);
            table.setModel(model);

            table.getColumnModel().getColumn(0).setMaxWidth(0);
            table.getColumnModel().getColumn(0).setMinWidth(0);
            table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
            table.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

            table.getColumnModel().getColumn(2).setMaxWidth(0);
            table.getColumnModel().getColumn(2).setMinWidth(0);
            table.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
            table.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void ObtenerDetalle(String idPedido, JPanel panel){
        //panel.updateUI();
        System.out.println("ID PEDIDO" + idPedido);
        panel.removeAll();
        panel.revalidate();
        Object [][] res;
        res = ob.GetDetallePedido(idPedido);
        double precio_total = 0;
        filas = res.length;
        System.out.println("Cantidad de Pedidos Filas "+filas);
        if (filas > 0) {
            column = res[0].length;
            precio_total = 0;
            panel.setLayout(new GridLayout(filas + 2, 3, 5, 5));
            pedidos = new JLabel[filas][4];

            Font font = new Font("Tahona", Font.BOLD, 12);

            JLabel header1 = new JLabel("PLATO");
            header1.setFont(font);
            panel.add(header1);

            JLabel header3 = new JLabel("CANTIDAD");
            header3.setHorizontalAlignment(SwingConstants.CENTER);
            header3.setFont(font);
            panel.add(header3);

            JLabel header2 = new JLabel("PRECIO");
            //header2.setHorizontalAlignment(SwingConstants.RIGHT);
            header2.setFont(font);
            panel.add(header2);

            JLabel header4 = new JLabel("SUB TOTAL");
            //header4.setHorizontalAlignment(SwingConstants.RIGHT);
            header4.setFont(font);
            panel.add(header4);

            for (int i = 0; i < filas; i++) {
                double cantidad = Double.parseDouble(res[i][4].toString());
                double precio = Double.parseDouble(res[i][10].toString());
                double sub = precio * cantidad;
                precio_total += sub;
                pedidos[i][0] = new JLabel(res[i][8].toString());
                pedidos[i][0].setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/checkoff.png")));
                pedidos[i][0].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                pedidos[i][0].setName(res[i][0].toString());
                pedidos[i][0].addMouseListener(this);
                pedidos[i][1] = new JLabel(res[i][4].toString());
                pedidos[i][1].setHorizontalAlignment(SwingConstants.CENTER);
                pedidos[i][2] = new JLabel(res[i][10].toString());
                //pedidos[i][2].setHorizontalAlignment(SwingConstants.RIGHT);
                pedidos[i][3] = new JLabel(sub + "");
                //pedidos[i][3].setHorizontalAlignment(SwingConstants.RIGHT);
                panel.add(pedidos[i][0]);
                panel.add(pedidos[i][1]);
                panel.add(pedidos[i][2]);
                panel.add(pedidos[i][3]);
            }

            JLabel vacio1 = new JLabel("");
            panel.add(vacio1);

            JLabel vacio2 = new JLabel("");
            panel.add(vacio2);

            JLabel total = new JLabel("TOTAL");
            total.setFont(font);
            panel.add(total);

            JLabel precio = new JLabel(precio_total + "");
            precio.setForeground(Color.red);
            //precio.setHorizontalAlignment(SwingConstants.RIGHT);
            panel.add(precio);
        }
    }

    public void MandarPedido(String idPlato, int cantidad, String idPedido, String observacion, JPanel panel) {
        int res;
        res = ob.MandarPedido(idPlato, cantidad, idPedido, observacion);
        System.out.println("controller " +res);
        if (res == 1) {
            System.out.println("si entra");
            ObtenerDetalle(idPedido, panel);
        }
    }

    public int CrearPedido(String idmesa) {
        int res;
        res = ob.CrearPedido(idmesa);
        return res;
    }
    
    public int CancelarPedido(String idDetallePedido){
        int res;
        res = ob.CancelarPedido(idDetallePedido);
        return res;
    }
    
    
    public void mouseClicked(MouseEvent e)
    {
        for (int i = 0; i < filas; i++) {
            pedidos[i][0].setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/checkoff.png")));
            if (e.getSource() == pedidos[i][0]) {
                pedidos[i][0].setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/checkon.png")));
                idPedidoSeleccionado = pedidos[i][0].getName().toString();
            }
        }
    }

    public int CerrarPedido(String idPedido, String idMesa) {
        int res, res2;
        res = ob.CerrarPedido(idPedido);
        if (res == 1) {ob.UpdateEstadoMesa(idMesa, 1);
        }
        return res;
    }

    public Object[][] ObtenerMesas() {
        Object res[][];
        res = ob.ObtenerMesas();
        return res;
    }
    
    public void UpdateEstadoMesa(String IdMesa, int Estado){
        ob.UpdateEstadoMesa(IdMesa, Estado);
    }
    
}

