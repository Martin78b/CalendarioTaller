/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendariotaller;

import dao.UsuarioDAO;


import entidades.Usuario;

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

        UsuarioDAO userdao = new UsuarioDAO();
        Usuario user = new Usuario("murai", "9999");
        userdao.actualizar(user);

    
    }}
