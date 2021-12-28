package Controlador;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.util.HashMap;

public class PruebasController {

    private IOBaseDatos BD = new IOBaseDatos();

    public void GeneraFactura()  {

        HashMap parametros = new HashMap();

        parametros.put("ParIDUsuario", 2);
        parametros.put("ParFechaIng","2021-12-11");


        JasperReport reporte;

        String archivo = "cashbill_chome_A4.jasper";

        try {

            reporte = (JasperReport) JRLoader.loadObjectFromFile(archivo);

            JasperPrint print = JasperFillManager.fillReport(reporte, parametros, BD.conexion() );
            JasperExportManager.exportReportToPdfFile(print,"Factura-"+java.time.LocalDate.now()+".pdf");

            JasperViewer.viewReport(print, false);

        } catch (JRException jRException) {

            System.out.println(jRException.getMessage());

        }

    }

    public void GeneraOrdenCompra()  {

        HashMap parametros = new HashMap();

        parametros.put("ParIDPro","01");


        JasperReport reporte;

        String archivo = "Orden de Compra Kamonduck.jasper";

        try {

            reporte = (JasperReport) JRLoader.loadObjectFromFile(archivo);

            JasperPrint print = JasperFillManager.fillReport(reporte, parametros, BD.conexion() );
            JasperExportManager.exportReportToPdfFile(print,"Orden Compra-"+java.time.LocalDate.now()+".pdf");

            JasperViewer.viewReport(print, false);

        } catch (JRException jRException) {

            System.out.println(jRException.getMessage());

        }

    }
}
