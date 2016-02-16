/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Calendario;
import entidades.Usuario;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 *
 * @author Martin
 */
public interface ICalendario {
    public void guardar(Calendario calendario);
    
    public void actualizar (Calendario calendario);
    
    public void borrar (Calendario calendario);
    
    public Usuario cargar(String id);
    
}
