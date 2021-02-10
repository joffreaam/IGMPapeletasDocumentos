/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author desarrollo
 */
public class ReportGenarator {

    public static String ACTAS_PAPELETAS = "PapeletaElectoral.jrxml";
    public static String ACTAS_DOCUMENTOS = "DocumentoElectoral.jrxml";

    public JasperPrint genarateReport(String reportName, Map<String, Object> map) throws JRException, SQLException {

            InputStream isReport = getClass().getClassLoader().getResourceAsStream(reportName);
            JasperReport jasperReport = JasperCompileManager.compileReport(isReport);
         //   JasperPrint jpPrint = JasperFillManager.fillReport(jasperReport, map, new JREmptyDataSource());
           Connection  connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.80:1521:IGM1","CNEELECCIONES","ELECCIONES2020!");
            JasperPrint jpPrint = JasperFillManager.fillReport(jasperReport, map, connection);
            
            
            
           // JasperViewer.viewReport(jpPrint, false);
            return jpPrint;

    }
}
