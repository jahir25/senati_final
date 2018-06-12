package Controller;

import Model.ModelReporte;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ControllerReporte {
    
    ModelReporte reporte;
    String[] cab  = new String[]{"Id Pedido", "Numero de Mesa", "Nombre del Plato", "Estado del plato", "Cantidad", "Precio", "Sub Total"};
    
    public void GenerarExcel(){
        reporte = new ModelReporte();
        try {
            //WorkbookSettings conf = new WorkbookSettings();
            XSSFWorkbook book = new XSSFWorkbook();
            XSSFSheet sheet = book.createSheet("Reporte");
            
            InputStream img = new FileInputStream("src\\assets\\logo.png");
            byte[] bytes = IOUtils.toByteArray(img);
            int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            
            CreationHelper help = book.getCreationHelper();
            Drawing draw = sheet.createDrawingPatriarch();
            
            ClientAnchor anchor = help.createClientAnchor();
            anchor.setCol1(0);
            anchor.setCol1(1);
            Picture pict = draw.createPicture(anchor, imgIndex);
            pict.resize(1, 3);
            
            /*
            =============================================================
                                CREACION DE ESTILOS
            =============================================================
            */
            
            CellStyle estilo = book.createCellStyle();
            estilo.setAlignment(HorizontalAlignment.CENTER);
            estilo.setVerticalAlignment(VerticalAlignment.CENTER);
            XSSFFont font = book.createFont();
            font.setFontName("Calibri");
            font.setColor(IndexedColors.LIGHT_BLUE.getIndex());
            font.setBold(true);
            font.setFontHeightInPoints((short) 20);
            estilo.setFont(font);
            /*
            =============================================================
                            FIN DE CREACIÓN DE ESTILOS
            =============================================================
            */
            
            /*
            =============================================================
                            CREACION DE HEADERS
            =============================================================
            */
            
            Row row = sheet.createRow(1);
            Cell cel = row.createCell(2);
            cel.setCellStyle(estilo);
            cel.setCellValue("Reporte de Ventas");
            
            sheet.addMergedRegion(new CellRangeAddress(1, 3, 2, 8));
            
            
            
            CellStyle estilocab = book.createCellStyle();
            estilocab.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            estilocab.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            XSSFFont fontcab = book.createFont();
            fontcab.setFontName("Calibri");
            fontcab.setBold(true);
            fontcab.setColor(IndexedColors.WHITE.getIndex());
            fontcab.setFontHeightInPoints((short) 12);
            estilocab.setFont(fontcab);
            
            Row FilaCab = sheet.createRow(5);
            
            for (int i = 0; i < cab.length; i++) {
                Cell celdaCab = FilaCab.createCell(i+2);
                celdaCab.setCellStyle(estilocab);
                celdaCab.setCellValue(cab[i].toUpperCase());
            }
            Object[][] res;
            res = reporte.ConsultarPedido();
            int contFilas = 0;
            String numeroPedido = "";
            if (res.length > 0) {
                for (int i = 0; i < res.length; i++) {
                    Row Filadata = sheet.createRow(i+6);
                    for (int j = 0; j < res[0].length; j++) {
                        Cell celldata = Filadata.createCell(j+2);
                        celldata.setCellValue(res[i][j].toString());
                        if (j == 3) {
                            
                            CellStyle estiloData = book.createCellStyle();
                            XSSFFont fontEstado = book.createFont();
                            fontEstado.setFontName("Calibri");
                            
                            if (res[i][j].toString().equals("Cancelado")) {
                                fontEstado.setColor(IndexedColors.RED.getIndex());
                            }else if(res[i][j].toString().equals("Tomado")){
                                fontEstado.setColor(IndexedColors.LIGHT_BLUE.getIndex());
                            }else if(res[i][j].toString().equals("Realizado")){
                                fontEstado.setColor(IndexedColors.ORANGE.getIndex());
                            }else if(res[i][j].toString().equals("Cerrado")){
                                fontEstado.setColor(IndexedColors.GREEN.getIndex());
                            }else{
                                fontEstado.setColor(IndexedColors.AUTOMATIC.getIndex());
                            }
                            estiloData.setFont(fontEstado);
                            celldata.setCellStyle(estiloData);
                        }else if(j == 6){
                            //celldata.setCellType(CellType.);
                        }
                        
                    }
                    sheet.autoSizeColumn(i);
                    if (i == 0) {
                        numeroPedido = res[i][0].toString();
                        contFilas++;
                    }else{
                        if (res[i][0].toString().equals(numeroPedido)) {
                        contFilas++;    
                        }else{
                            //sheet.addMergedRegion(new CellRangeAddress(2, 2, i+6, contFilas + (i+6)));
                            numeroPedido = res[i][0].toString();
                            contFilas = 1;
                        }
                    }
                }
            }
            sheet.setZoom(100);
            /*
            =============================================================
                            FIN DE CREACION DE HEADERS
            =============================================================
            */
            
            FileOutputStream out = new FileOutputStream(new File("c:/Xprueba.xlsx"));
            book.write(out);
            out.close();
            System.out.println("Sale correcto");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Por favor cierre el excel", "Info", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(e);
        }
        
    }

    public void CargarTabla(JTable tablereporte) {
        reporte = new ModelReporte();
        DefaultTableModel model = new DefaultTableModel();
        for (int i = 0; i < cab.length; i++) {
            System.out.println(cab[i]);
            model.addColumn(cab[i]);
        }
        reporte.CargarTable(model);
        tablereporte.setModel(model);
    }
    
}
