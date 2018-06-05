package Controller;

import Model.ModelChef;
import javax.swing.JTable;

public class ControllerChef {
    ModelChef chef;
    public void CargarTabla(JTable table){
        
        chef = new ModelChef();
        chef.CargarTabla();
    }
    
}
