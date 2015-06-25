/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendariotaller;

/**
 *
 * @author martin
 */

import java.sql.*;

public class CalendarioTaller {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        String dbURL = "jdbc:derby://localhost:1527/calendario;";
        Connection conn = DriverManager.getConnection(dbURL);
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("set schema APP");
        stmt.executeUpdate("INSERT INTO CUENTA (ID, EMAIL, DISPLAYNAME, SERVICIO, TOKEN, USUARIO) "
                + "VALUES ('1', 'a@a.com', 'yo', 'Google', '1', 'yo')");
        stmt.close();
    }
    
}
