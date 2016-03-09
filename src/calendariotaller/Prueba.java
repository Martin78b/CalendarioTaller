/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendariotaller;



import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Lists;
import com.google.api.client.util.StringUtils;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Calendar;
import com.google.api.services.calendar.model.CalendarList;
import dao.CalendarioDao;
import dao.CuentaDAO;
import dao.UsuarioDAO;
import entidades.Calendario;
import entidades.Cuenta;
import entidades.Usuario;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
/**
 *
 * @author Martin
 */
public class Prueba {

    
    
    public static void main(String[] args) throws SQLException {
      /*  CuentaDAO cuenta = new CuentaDAO();
        UsuarioDAO userdao = new UsuarioDAO();
        
        CalendarioDao cal = new CalendarioDao();
        
        Usuario user = userdao.cargar("yo");
        
        Collection<Cuenta> listac = cuenta.cargar(user);
        Iterator<Cuenta> iterador = listac.iterator();
        Cuenta acc= iterador.next();
        Collection<Calendario> lista = cal.cargar(acc);
        Iterator<Calendario> iterator = lista.iterator();
        Calendario calendario = iterator.next();
        
        System.out.println(calendario.getCuenta());
      System.out.println(calendario.getDescription());
      System.out.println(calendario.getId());
      System.out.println(calendario.getKind());
      System.out.println(calendario.getLocation());
      System.out.println(calendario.getSummary());
      System.out.println(calendario.getTomezone());
      
      */
        Date dt = new Date();
        //java.sql.Date dtsql = new java.sql.Date(2016, 2, 18);
        java.sql.Date dtsql = new java.sql.Date(new Date().getTime());
        String st =new Timestamp(dt.getTime()).toString();
        System.out.println(st.substring(11,19));
        //System.out.println(dtsql.toString());
        
    }
}
