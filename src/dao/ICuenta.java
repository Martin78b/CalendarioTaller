/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Cuenta;
import entidades.Usuario;
import java.util.Collection;

/**
 *
 * @author Martin
 */
public interface ICuenta {
    
    public void guardar(String email, String displayname, String servicio, String token, String  nombreUsuario);
    
    public void modificar(Cuenta cuenta);
    
    public void borrar(Cuenta cuenta);
    
    public Collection<Cuenta> cargar(Usuario user);
}
