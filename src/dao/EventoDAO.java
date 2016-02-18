/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Evento;
import entidades.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin
 */
public class EventoDAO implements IEvento{

    @Override
    public void guardar(Evento evento) throws SQLIntegrityConstraintViolationException {
        try {
            String dbURL = "jdbc:derby://localhost:1527/calendario";
            Connection conn = DriverManager.getConnection(dbURL);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("set schema APP");
            stmt.executeUpdate("INSERT INTO EVENTO (ID, STATUS, CREATED, UPDATED, SUMMARY, DESCRIPTION, LOCATION, COLORID, STARTDATE,"
                    + "STARTDATETIME, STARTDATETIMEZONE, ENDDATE, ENDDATETIME, ENDTIMEZONE, RECURRENCE, RECURRENCEEVENTID,"
                    + "ORIGINALSTARTDATE, ORIGINALSTARTDATETIME, ORIGINALTIMEZONE, TRANSPARENCY, VISIBILITY, ICALUID, SEQUENCIA"
                    + ", REIMNDERSDEFAULT, REMINDERMETHOD, REMINDERMINUTES, CALENDARIO) values ('"+ evento.getId()+"', '"+evento.getStatus()
                    +"', '"+evento.getCreated()+"', '"+evento.getUpdated()+"', '"+evento.getSummary()+"', '"+evento.getDescription()
                    +"', '"+evento.getLocation()+"', '"+evento.getColorid()+"', '"+evento.getStartdate()+"', '"+evento.getStartdatetime()
                    +"', '"+evento.getStarttimezone()+"', '"+evento.getEnddate()+"', '"+evento.getEnddatetime()+"', '"+evento.getEndtimezone()
                    +"', '"+evento.getRecurrence()+"', '"+evento.getRecurrenceeventid()+"', '"+evento.getOriginalstartdate()
                    +"', '"+evento.getOriginalstartdatetime()+"', '"+evento.getOriginaltimezone()+"', '"+evento.getTransparency()
                    +"', '"+evento.getVisibility()+"', '"+evento.getIcaluid()+"', '"+evento.getSequencia()+"', '"+evento.getRemindersdefault()
                    +"', '"+evento.getRemindermethod()+"', '"+evento.getReminderminutes()+"', '"+evento.getCalendario().getId()+"')");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actualizar(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrar(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario cargar(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
