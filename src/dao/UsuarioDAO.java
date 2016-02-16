/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin
 */
public class UsuarioDAO implements IUsuario{

    @Override
    public void guardar(Usuario usuario) {
        try {
            String dbURL = "jdbc:derby://localhost:1527/calendario";
            Connection conn = DriverManager.getConnection(dbURL);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("set schema APP");
            stmt.executeUpdate("INSERT INTO USUARIO (nombre, password) values ('"+ usuario.getNombre()+"', '"+usuario.getPassword()+"')");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actualizar(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrar(Usuario usuario) {
        try {
            String dbURL = "jdbc:derby://localhost:1527/calendario";
            Connection conn = DriverManager.getConnection(dbURL);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("set schema APP");
            stmt.executeUpdate("INSERT INTO USUARIO (nombre, password) values ('"+ usuario.getNombre()+"', '"+usuario.getPassword()+"')");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Usuario cargar(String nombre){
        Usuario user = new Usuario();
        try {
            String dbURL = "jdbc:derby://localhost:1527/calendario";
            Connection conn = DriverManager.getConnection(dbURL);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("set schema APP");
            ResultSet rs = stmt.executeQuery("SELECT nombre, password FROM USUARIO WHERE nombre='"+nombre+"'");
            while(rs.next()){            
                user.setPassword(rs.getString("password"));
                user.setNombre(nombre);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
}
