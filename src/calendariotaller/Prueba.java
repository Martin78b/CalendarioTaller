/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendariotaller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;
/**
 *
 * @author Martin
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
         String dbURL = "jdbc:derby://localhost:1527/calendario";
        Connection conn = DriverManager.getConnection(dbURL);
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("set schema APP");
        stmt.executeUpdate("INSERT INTO USUARIO (nombre, password) values ('murai', '123456')");
    }
    
}
