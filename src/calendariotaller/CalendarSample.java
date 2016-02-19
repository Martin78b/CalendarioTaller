/*
 * Copyright (c) 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
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
import com.google.api.client.util.DateTime;
import com.google.api.client.util.Lists;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Calendar;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import dao.CalendarioDao;
import dao.CuentaDAO;
import dao.EventoDAO;
import entidades.Calendario;
import entidades.Cuenta;
import entidades.Evento;
import entidades.Usuario;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author Yaniv Inbar
 */
public class CalendarSample {

  /**
   * Be sure to specify the name of your application. If the application name is {@code null} or
   * blank, the application will log a warning. Suggested format is "MyCompany-ProductName/1.0".
   */
  private static final String APPLICATION_NAME = "Kalendaro";

  /** Directory to store user credentials. */
  private static final java.io.File DATA_STORE_DIR =
      //new java.io.File(System.getProperty("user.home"), "Documents\\.store\\calendar_sample");
      new java.io.File("\\.store\\kalendaro");

  /**
   * Global instance of the {@link DataStoreFactory}. The best practice is to make it a single
   * globally shared instance across your application.
   */
  private static FileDataStoreFactory dataStoreFactory;
  
  /** Global instance of the HTTP transport. */
  private static HttpTransport httpTransport;

  /** Global instance of the JSON factory. */
  private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

  private static com.google.api.services.calendar.Calendar client;

  static final java.util.List<Calendar> addedCalendarsUsingBatch = Lists.newArrayList();

  /** Authorizes the installed application to access user's protected data. */
  private static Credential authorize() throws Exception {
    // load client secrets
    Reader lector = new InputStreamReader(CalendarSample.class.getResourceAsStream("/calendariotaller/client_secrets.json"));
    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, lector);
        
    if (clientSecrets.getDetails().getClientId().startsWith("Enter")
        || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
      System.out.println(
          "Enter Client ID and Secret from https://code.google.com/apis/console/?api=calendar "
          + "into calendar-cmdline-sample/src/main/resources/client_secrets.json");
      System.exit(1);
    }
    // set up authorization code flow
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        httpTransport, JSON_FACTORY, clientSecrets,
        Collections.singleton(CalendarScopes.CALENDAR)).setDataStoreFactory(dataStoreFactory)
        .build();
    // authorize
    return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
  }

  public static void main(String[] args) {
    try {
      // initialize the transport
      httpTransport = GoogleNetHttpTransport.newTrustedTransport();

      // initialize the data store factory
      dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);

      // authorization
      Credential credential = authorize();
      

      // set up global Calendar instance
      client = new com.google.api.services.calendar.Calendar.Builder(
          httpTransport, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();

      // run commands
      showCalendars();
      addCalendarsUsingBatch();
      Calendar calendar = addCalendar();
      
      updateCalendar(calendar);
      addEvent(calendar);
      
      showEvents(calendar);
      deleteCalendarsUsingBatch();
      deleteCalendar(calendar);

    } catch (IOException e) {
      System.err.println(e.getMessage());
    } catch (Throwable t) {
      t.printStackTrace();
    }
    System.exit(1);
  }

  private static void showCalendars() throws IOException {
    View.header("Show Calendars");
    CalendarList feed = client.calendarList().list().execute();
    View.display(feed);
  }

  private static void addCalendarsUsingBatch() throws IOException {
    View.header("Add Calendars using Batch");
    BatchRequest batch = client.batch();

    // Create the callback.
    JsonBatchCallback<Calendar> callback = new JsonBatchCallback<Calendar>() {

      @Override
      public void onSuccess(Calendar calendar, HttpHeaders responseHeaders) {
        View.display(calendar);
        addedCalendarsUsingBatch.add(calendar);
      }

      @Override
      public void onFailure(GoogleJsonError e, HttpHeaders responseHeaders) {
        System.out.println("Error Message: " + e.getMessage());
      }
    };

    // Create 2 Calendar Entries to insert.
    Calendar entry1 = new Calendar().setSummary("Calendar for Testing 1");
    client.calendars().insert(entry1).queue(batch, callback);

    Calendar entry2 = new Calendar().setSummary("Calendar for Testing 2");
    client.calendars().insert(entry2).queue(batch, callback);
    
    batch.execute();
  }

  private static Calendar addCalendar() throws IOException {
    View.header("Add Calendar");
    Calendar entry = new Calendar();
    entry.setSummary("Calendario de Prueba");
    Calendar result = client.calendars().insert(entry).execute();
    View.display(result);
    return result;
  }

  private static Calendar updateCalendar(Calendar calendar) throws IOException {
    View.header("Update Calendar");
    Calendar entry = new Calendar();
    entry.setSummary("Updated Calendar for Testing");
    Calendar result = client.calendars().patch(calendar.getId(), entry).execute();
    View.display(result);
    return result;
  }


  private static void addEvent(Calendar calendar) throws IOException {
    View.header("Add Event");
    Event event = newEvent();
    Event result = client.events().insert(calendar.getId(), event).execute();
    EventoDAO eve = new EventoDAO();
    Evento even = new Evento();
    Cuenta cuenta = new Cuenta();
    CuentaDAO acc = new CuentaDAO();
    CalendarioDao cladao = new CalendarioDao();
    Collection<Cuenta> coll = acc.cargar(new Usuario("yo"));
    System.out.println("Cargó la cuenta del usuario yo");
    Iterator<Cuenta> it = coll.iterator();
    cuenta= it.next();
    Calendario cal = new Calendario();
    cal.setId(calendar.getId());
    cal.setKind(calendar.getKind());
    cal.setSummary(calendar.getSummary());
    //cal.setTomezone(calendar.getTimeZone());
    cal.setCuenta(cuenta);
    cladao.guardar(cal);
    System.out.println("Guardó el calendario en DDBB");
    even.setCalendario(cal);
    even.setColorid(result.getColorId());
    even.setCreated(new Date(result.getCreated().getValue()));
    even.setDescription(result.getDescription());
    java.sql.Date startDate = new java.sql.Date(new Date().getTime());
    java.sql.Date endDate = new java.sql.Date(startDate.getTime() + 3600000);
    System.out.println(startDate);
    System.out.println(endDate);
    even.setEnddate(endDate);
    even.setEnddatetime(endDate);
    //even.setEndtimezone(result.getEnd().getTimeZone());
    even.setIcaluid(result.getICalUID());
    even.setId(result.getId());
    even.setLocation(result.getLocation());
    even.setOriginalstartdate(startDate);
    even.setOriginalstartdatetime(startDate);
    System.out.println("creó el evento son las fechas");
    //even.setOriginaltimezone(result.getOriginalStartTime().getTimeZone());
    //even.setRecurrence(result.getRecurrence().iterator().next());
    //even.setRecurrenceeventid(result.getRecurringEventId());
    //even.setRemindermethod(result.getReminders().toString());
    //even.setReminderminutes(20);
    //even.setRemindersdefault(result.getReminders().getUseDefault());
    //even.setSequencia(result.getSequence().toString());
    //even.setStartdate(new Date(result.getStart().getDate().getValue()));
    //even.setStartdatetime(new Date(result.getStart().getDateTime().getValue()));
    even.setStarttimezone("UTC");
    even.setStatus(result.getStatus());
    even.setSummary(result.getSummary());
    even.setTransparency(result.getTransparency());
    even.setUpdated(new Date(result.getUpdated().getValue()));
    even.setVisibility(result.getVisibility());
      try {
          System.out.println("Guardando el evento");
          eve.guardar(even);
          System.out.println("Evento Guardado");
      } catch (SQLIntegrityConstraintViolationException ex) {
          Logger.getLogger(CalendarSample.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    View.display(result);
  }

  private static Event newEvent() {
    Event event = new Event();
    event.setSummary("New Event");
    Date startDate = new Date();
    Date endDate = new Date(startDate.getTime() + 3600000);
    DateTime start = new DateTime(startDate, TimeZone.getTimeZone("UTC"));
    event.setStart(new EventDateTime().setDateTime(start));
    DateTime end = new DateTime(endDate, TimeZone.getTimeZone("UTC"));
    event.setEnd(new EventDateTime().setDateTime(end));
    return event;
  }

  private static void showEvents(Calendar calendar) throws IOException {
    View.header("Show Events");
    Events feed = client.events().list(calendar.getId()).execute();
    View.display(feed);
  }

  private static void deleteCalendarsUsingBatch() throws IOException {
    View.header("Delete Calendars Using Batch");
    BatchRequest batch = client.batch();
    for (Calendar calendar : addedCalendarsUsingBatch) {
      client.calendars().delete(calendar.getId()).queue(batch, new JsonBatchCallback<Void>() {

        @Override
        public void onSuccess(Void content, HttpHeaders responseHeaders) {
          System.out.println("Delete is successful!");
        }

        @Override
        public void onFailure(GoogleJsonError e, HttpHeaders responseHeaders) {
          System.out.println("Error Message: " + e.getMessage());
        }
      });
    }

    batch.execute();
  }

  private static void deleteCalendar(Calendar calendar) throws IOException {
    View.header("Delete Calendar");
    client.calendars().delete(calendar.getId()).execute();
  }
}
