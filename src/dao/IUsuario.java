/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Usuario;

/**
 *
 * @author Martin
 */
public interface IUsuario {
    
    public void guardar(Usuario usuario);
    
    public void actualizar (Usuario usuario);
    
    public void borrar (Usuario usuario);
    
    public Usuario cargar(String nombre);
}
