/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Calendario;
import entidades.Cuenta;
import entidades.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mauricio
 */
public class CalendarioDao implements ICalendario {

    @Override
    public void guardar(Calendario calendario) {
        try {
            String dbURL = "jdbc:derby://localhost:1527/calendario";
            Connection conn = DriverManager.getConnection(dbURL);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("set schema APP");
            stmt.executeUpdate("INSERT INTO CALENDARIO (id,summary, description, location, "
                    + "tomezone, kind, cuenta) VALUES ('" + calendario.getId() + "',"
                    + "'" + calendario.getSummary() + "','" + calendario.getDescription() + "', "
                    + "'" + calendario.getLocation() + "', '" + calendario.getTomezone() + "' ,'" + calendario.getKind() + "', "
                    + "'" + calendario.getCuenta().getId() + "')");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actualizar(Calendario calendario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrar(Calendario calendario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Calendario> cargar(Cuenta cuenta) {
    Collection<Calendario> lista = new ArrayList<>();
    Calendario calendario = null;
        try {
            String dbURL = "jdbc:derby://localhost:1527/calendario";
            Connection conn = DriverManager.getConnection(dbURL);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("set schema APP");
            ResultSet rs = stmt.executeQuery("SELECT * FROM CALENDARIO WHERE CUENTA='"+cuenta.getId()+"'");
            while(rs.next()){            
                calendario = new Calendario();
                calendario.setSummary(rs.getString("SUMMARY"));
                calendario.setLocation(rs.getString("LOCATION"));
                calendario.setDescription(rs.getString("DESCRIPTION"));
                calendario.setKind(rs.getString("KIND"));
                calendario.setTomezone(rs.getString("TOMEZONE"));
                calendario.setSummary(rs.getString("SUMMARY"));
                calendario.setCuenta(cuenta);
                lista.add(calendario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    return lista;
    }

}
