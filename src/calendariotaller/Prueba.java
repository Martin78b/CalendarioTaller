/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendariotaller;



import dao.CuentaDAO;
import dao.UsuarioDAO;
import entidades.Cuenta;
import entidades.Usuario;
import java.sql.SQLException;
import java.util.Collection;
/**
 *
 * @author Martin
 */
public class Prueba {

    public static void main(String[] args) throws SQLException {
        CuentaDAO cuenta = new CuentaDAO();
        UsuarioDAO userdao = new UsuarioDAO();
        
        Usuario user = userdao.cargar("yo");
        Cuenta acc= null;
        Collection<Cuenta> lista = cuenta.cargar(user);
        int i=1;
        while(!lista.isEmpty()){
            lista.
        }
    }
}
