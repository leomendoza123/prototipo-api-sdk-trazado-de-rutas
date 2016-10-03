package survey;

import java.sql.*;
import java.util.Date;
import java.util.Vector;

public class Survey {
  
  public String lastName;
  public String firstName;
  public String discipline;
  public String message;
  public Date surveyDate;
  public DatabaseAccess database;  
  private Statement stmt;
  static private String sqlString;	
    
  public Survey() {
    database = new DatabaseAccess();
  }  
 
  public String nullFilter(String s) {
    if (s == null || s.trim().equals("null"))
      return "";
    return s.trim();
  }
  
  // protect ' and " in SQL values
  public String p(String s) {
    s = s.replace("'", "\\'");
    s = s.replace("\"", "\\\"");
    return s;
  }
        
  public synchronized void saveData() {   
    String surveyDateStr = "";
	  try {
	    surveyDateStr = (new java.sql.Date(new java.util.Date().getTime())).toString();
	  }
	  catch(Exception e){}
    Statement stmt = database.getStatement();
    String sqlString = "INSERT INTO survey (lastName, " +
      "firstName, discipline, message, surveyDate) VALUES ('" + 
      p(lastName) + "', '" + p(firstName) + "', '" + 
      p(discipline) + "', '" + p(message) + "', '" + p(surveyDateStr) + "')";
          
    try {
      stmt.executeUpdate(sqlString);
    } 
    catch (Exception e) {
      System.out.println("Failed in saveData(): " + sqlString);
      System.out.println(e);
    }
    finally {
      database.releaseStatement();
    } 
  }

  public synchronized boolean retrieveData(String pLastName, String pFirstName) {
    boolean returnCode = true;
    stmt = database.getStatement();
    sqlString = "SELECT lastName, " +
      "firstName, message, discipline, surveyDate " +
  		"FROM survey WHERE lastName = '" + pLastName + "' and firstName = '" + 
  		pFirstName + "'";
    try {
      ResultSet rs = stmt.executeQuery(sqlString);
      rs.next();
      lastName = nullFilter(rs.getString(1));
      firstName = nullFilter(rs.getString(2));
      message = nullFilter(rs.getString(3));
      discipline = nullFilter(rs.getString(4));
      surveyDate = rs.getDate(5); 
    }
    catch (Exception e) {
      returnCode = false;
      System.out.println("Failed in retrieveData(): " + sqlString);
      System.out.println(e);
    }
    finally {
      database.releaseStatement();      
    }
    return returnCode;  
  }
	  
  public synchronized boolean isRecordAvailable(String pLastName, String pFirstName) {
    stmt = database.getStatement();
    boolean isAvailable = false;
    sqlString = "SELECT lastName FROM survey WHERE lastName = '" + pLastName + 
                "' and firstName = '" + pFirstName + "'";
    try {
    	ResultSet rs = stmt.executeQuery(sqlString);
    	isAvailable = rs.next();
    } 
    catch (Exception e) {
    	System.out.println("Failed in isRecordAvailable(): " + sqlString);
    	System.out.println(e);
    }
    finally {
      database.releaseStatement();
    }
    return isAvailable;
  }

  public synchronized void delete(String pLastName, String pFirstName) {
    stmt = database.getStatement();
    sqlString = "DELETE FROM survey WHERE lastName = '" + pLastName + 
                "' and firstName = '" + pFirstName + "'";
    try {
    	stmt.executeUpdate(sqlString);
    } 
    catch (Exception e) {
    	System.out.println("Failed in delete(): " + sqlString);
    	System.out.println(e);
    }
    finally {
      database.releaseStatement();
    }
  }
  
  // For debugging
  private void print() {
    System.out.println("Last Name: \t" + lastName);
    System.out.println("First Name: \t" + firstName);
    System.out.println("Discipline: \t" + discipline);
    System.out.println("Date: \t" + surveyDate);
    System.out.println("Message: \t" + message);
  }
  
  // For debugging class Survey; not used by the Web application
  public static void main(String[] args) {
    Survey s = new Survey();
    if (s.isRecordAvailable("LastName", "FirstName"))
      s.delete("LastName", "FirstName");
    s.lastName = "LastName";
    s.firstName = "FirstName";
    s.discipline = "Science";
    s.message = "Test message";
    s.saveData();
    s.retrieveData("LastName", "FirstName");
    s.print();
  }
}
