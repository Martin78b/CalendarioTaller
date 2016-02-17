/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Cuenta;
import entidades.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin
 */
public class CuentaDAO implements ICuenta{

    @Override
    public void guardar(String email, String displayname, String servicio, String token, String  nombreUsuario) {
        try {
            String dbURL = "jdbc:derby://localhost:1527/calendario";
            Connection conn = DriverManager.getConnection(dbURL);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("set schema APP");
            ResultSet rs = stmt.executeQuery("SELECT max(ID) FROM CUENTA");
            rs.next();
            String resultado = rs.getString(1);
            System.out.println(resultado);
            int identificador = Integer.parseInt(resultado);
            identificador=identificador+1;
            stmt.executeUpdate("INSERT INTO CUENTA (id, email, displayname, servicio, token, usuario) values ('"+identificador+"','"+ email+"', '"+displayname+"', '"+servicio+"', '"+token+"', '"+nombreUsuario+"')");
        } catch (SQLException ex) {
            Logger.getLogger(CuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modificar(Cuenta cuenta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrar(Cuenta cuenta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Cuenta> cargar(Usuario user) {
        Collection<Cuenta> lista = new ArrayList<>();
        Cuenta cuenta =null;
        try{
            String dbURL = "jdbc:derby://localhost:1527/calendario";
            Connection conn = DriverManager.getConnection(dbURL);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("set schema APP");
            ResultSet rs = stmt.executeQuery("SELECT * FROM CUENTA WHERE usuario='"+user.getNombre()+"'");
            while(rs.next()){
                cuenta= new Cuenta();
                cuenta.setId(rs.getString("ID"));
                cuenta.setDisplayname(rs.getString("displayname"));
                cuenta.setEmail(rs.getString("email"));
                cuenta.setServicio(rs.getString("servicio"));
                cuenta.setToken(rs.getString("token"));
                //cuenta.setUsuario(user);
                lista.add(cuenta);
            }
        } catch (Exception ex){
            
        }
        return lista;
    }
    
}
