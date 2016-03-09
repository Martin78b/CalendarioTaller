/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Calendario;
import entidades.Evento;
import entidades.Usuario;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;

/**
 *
 * @author Martin
 */
public interface IEvento {
    
    public void guardar(Evento evento)throws SQLIntegrityConstraintViolationException;
    
    public void actualizar (Evento evento);
    
    public void borrar (Evento evento);
    
    public Collection<Evento> cargar(Calendario calendario);
}
