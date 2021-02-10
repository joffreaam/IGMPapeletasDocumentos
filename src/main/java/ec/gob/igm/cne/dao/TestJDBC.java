/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose
 * Clase para probar la conexion con la Base de Datos
 */
public class TestJDBC {
    private final static String URL = "jdbc:oracle:thin:@192.168.1.80:1521:IGM1";
    private final static String USER = "CNEELECCIONES";
    private final static String PASSWORD= "ELECCIONES2020!";

   

    /**
     * Prueba la conexion con la Base de Datos
     * @return True si la conexion es exitosa, False si no es
     */
    public static boolean TestConnection() {
        Connection c = null;  
        try {    
            Class.forName("oracle.jdbc.OracleDriver");    
            System.out.println("Driver encontrado");    
            System.out.println("Conectando..."); 
            c = DriverManager.getConnection(URL, USER, PASSWORD); 
            System.out.println("Conexion satisfactoria");
            return true;
        } catch (Exception e) {    
            System.out.println("Cannot connect the database");             
            return false;
        } finally {    
            if (c != null) {       
                try {        
                    c.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TestJDBC.class.getName()).log(Level.SEVERE, null, ex);
                }
            }    
        }    
    }
}
