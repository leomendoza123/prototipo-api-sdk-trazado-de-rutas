package survey;

import java.sql.*;

public class DatabaseAccess {

  Connection con;     // A connection to the database
  Statement stmt;     // All purpose statement
  String jdbcDriver = "com.mysql.jdbc.Driver";
  String dbURL = "jdbc:mysql://localhost/test";
  String user = "root";
  String password = "123456";
  boolean isStatementFree = true;

  public DatabaseAccess() {
    try {
      Class.forName(jdbcDriver);
      con = DriverManager.getConnection(dbURL, user, password);
      stmt = con.createStatement();
    } catch (Exception e) {
      System.err.println("Error in DatabaseAccess()");
      System.err.println(e);
    }
  }

  public synchronized Statement getStatement() {
    while (isStatementFree == false) {
      try {
        wait();
      } catch(InterruptedException e) {}
    }
    isStatementFree = false;
    notify();
    
    return stmt;
  }
  
  public synchronized void releaseStatement() {
    while (isStatementFree) {
      try {
        wait();
      } catch(InterruptedException e) {}
    }
    isStatementFree = true;
    notify();
  }
}
