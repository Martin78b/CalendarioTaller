/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Calendario;
import entidades.Evento;
import entidades.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
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
            String st = "INSERT INTO EVENTO (ID, STATUS, CREATED, UPDATED, SUMMARY, DESCRIPTION, LOCATION, COLORID, STARTDATE,"
                    + "STARTDATETIME, STARTTIMEZONE, ENDDATE, ENDDATETIME, ENDTIMEZONE, RECURRENCE, RECURRENCEEVENTID,"
                    + "ORIGINALSTARTDATE, ORIGINALSTARTDATETIME, ORIGINALTIMEZONE, TRANSPARENCY, VISIBILITY, ICALUID, SEQUENCIA"
                    + ", REMINDERSDEFAULT, REMINDERMETHOD, REMINDERMINUTES, CALENDARIO) values ('"+ evento.getId()+"', '"+evento.getStatus()
                    +"', '"+new java.sql.Date(evento.getCreated().getTime())+"', '"+new java.sql.Date(evento.getUpdated().getTime())+"', '"+evento.getSummary()+"', '"+evento.getDescription()
                    +"', '"+evento.getLocation()+"', '"+evento.getColorid()+"', '"+new java.sql.Date(evento.getStartdate().getTime())+"', '"+new Timestamp(evento.getStartdatetime().getTime()).toString().substring(11,19)
                    +"', '"+evento.getStarttimezone()+"', '"+new java.sql.Date(evento.getEnddate().getTime())+"', '"+new Timestamp(evento.getEnddatetime().getTime()).toString().substring(11,19)+"', '"+evento.getEndtimezone()
                    +"', '"+evento.getRecurrence()+"', '"+evento.getRecurrenceeventid()+"', '"+new java.sql.Date(evento.getOriginalstartdate().getTime())
                    +"', '"+new Timestamp(evento.getOriginalstartdatetime().getTime()).toString().substring(11, 19)+"', '"+evento.getOriginaltimezone()+"', '"+evento.getTransparency()
                    +"', '"+evento.getVisibility()+"', '"+evento.getIcaluid()+"', '"+evento.getSequencia()+"', '"+evento.getRemindersdefault()
                    +"', '"+evento.getRemindermethod()+"', "+evento.getReminderminutes()+", '"+evento.getCalendario().getId()+"')";
            
            stmt.executeUpdate(st);
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
    public Collection<Evento> cargar(Calendario  calendario) {
        Collection<Evento> lista = new ArrayList<>();
        Evento evento = null;
        try {
            String dbURL = "jdbc:derby://localhost:1527/calendario";
            Connection conn = DriverManager.getConnection(dbURL);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("set schema APP");
            String st = "SELECT * WHERE CALENDARIO="+calendario.getId();
            ResultSet rs = stmt.executeQuery(st);
            while (rs.next()) {
                
                /*Elementos del evento
                ID, STATUS, CREATED, UPDATED, SUMMARY, DESCRIPTION, LOCATION, COLORID, STARTDATE,"
                    + "STARTDATETIME, STARTTIMEZONE, ENDDATE, ENDDATETIME, ENDTIMEZONE, RECURRENCE, RECURRENCEEVENTID,"
                    + "ORIGINALSTARTDATE, ORIGINALSTARTDATETIME, ORIGINALTIMEZONE, TRANSPARENCY, VISIBILITY, ICALUID, 
                SEQUENCIA"
                    + ", REMINDERSDEFAULT, REMINDERMETHOD, REMINDERMINUTES, CALENDARIO
                
                */
                evento= new Evento();
                evento.setId(rs.getString("ID"));
                evento.setStatus(rs.getString("STATUS"));
                evento.setCreated(rs.getDate("CREATED"));
                evento.setUpdated(rs.getDate("UPDATED"));
                evento.setSummary(rs.getString("SUMMARY"));
                evento.setDescription(rs.getString("DESCRIPTION"));
                evento.setLocation(rs.getString("LOCATION"));
                evento.setColorid(rs.getString("COLORID"));
                evento.setStartdate(rs.getDate("STARTERDATE"));
                evento.setStartdatetime(rs.getDate("STARTDATETIME"));
                evento.setStarttimezone(rs.getString("STARTTIMEZONE"));
                evento.setEnddate(rs.getDate("ENDDATE"));
                evento.setEnddatetime(rs.getDate("ENDDATETIME"));
                evento.setEndtimezone(rs.getString("ENDTIMEZONE"));
                evento.setRecurrence(rs.getString("RECURRENCE"));
                evento.setRecurrenceeventid(rs.getString("RECURRENCEEVENTID"));
                evento.setOriginalstartdate(rs.getDate("ORIGINALSTARTDATE"));
                evento.setOriginalstartdatetime(rs.getDate("ORIGINALSTARTDATETIME"));
                evento.setOriginaltimezone(rs.getString("ORIGINALTIMEZONE"));
                evento.setTransparency(rs.getString("TRANSPARENCY"));
                evento.setVisibility(rs.getString("VISIBILITY"));
                evento.setIcaluid(rs.getString("ICALUID"));
                evento.setSequencia(rs.getString("SEQUENCIA"));
                evento.setRemindersdefault(rs.getBoolean("REMINDERSDEFAULT"));
                evento.setRemindermethod(rs.getString("REMINDERMETHOD"));
                evento.setReminderminutes(rs.getInt("REMINDERMINUTES"));
                evento.setCalendario(calendario);
                //FALTA CARGAR LA LISTA DE EVENTOS
                lista.add(evento);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
}
