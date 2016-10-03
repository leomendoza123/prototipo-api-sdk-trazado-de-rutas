package survey;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class Delete extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response) {
    Survey survey = new Survey();
    try {
      String lastName = request.getParameter("d_lastName");
      String firstName = request.getParameter("d_firstName");
  
      String sqlString = "delete from survey where lastName='" + lastName +
                         "' and firstName='" + firstName + "'";

      Statement stmt = survey.database.getStatement();
      try {       
        stmt.executeUpdate(sqlString);
      } 
      catch (Exception e) {
        System.out.println("Failed in deleting data: " + sqlString);
        System.out.println(e);
      }
      survey.database.releaseStatement();
      RequestDispatcher dispatcher = request.getRequestDispatcher("/queryFormProcessing");
      dispatcher.forward(request, response);
    }
    catch (Exception e){}
  }
	 
  public void doGet(HttpServletRequest request, HttpServletResponse response) {
    doPost(request, response);
  }
}