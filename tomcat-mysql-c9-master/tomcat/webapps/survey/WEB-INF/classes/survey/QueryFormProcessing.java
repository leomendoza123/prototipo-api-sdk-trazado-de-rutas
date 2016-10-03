package survey;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class QueryFormProcessing extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response) {
    Survey survey = new Survey();
    try {
      response.setContentType("text/html");
      PrintWriter writer = response.getWriter();
      writer.println("<html><head><title>Search Processing Result</title></head><body>");
      writer.println("<h2 align=\"center\"><em>Data Query Result</em></h2><br/>");
         
      String lastName = request.getParameter("tf_lastName");
      String firstName = request.getParameter("tf_firstName");
      String discipline = request.getParameter("cbo_discipline");
      String keyPhrase = request.getParameter("tf_keyPhrase");

      String query_parameters = "tf_lastName=" + lastName + "&tf_firstName=" + 
        firstName + "&cbo_discipline=" + discipline + "&tf_keyPhrase=" + keyPhrase;
   
      String selectClause = "select distinct lastName, firstName, discipline, message, surveyDate";
      String fromClause = " from survey";
      String orderClause = " order by lastName ";
      String whereClause = " where 100 = 100 ";  // true, identity for logical AND

      if ((lastName != null) && !lastName.trim().equals(""))
        whereClause += " and lastName =  '" + survey.p(lastName) + "'";
      if ((firstName != null) && !firstName.trim().equals(""))
        whereClause += " and firstName =  '" + survey.p(firstName) + "'";
      if (discipline.compareTo("Select All") != 0 ) 
        whereClause += " and discipline =  '" + discipline + "'";
      if ((keyPhrase != null) && !keyPhrase.trim().equals(""))
        whereClause += " and message like '%" + survey.p(keyPhrase) + "%'";

      String sqlString = selectClause + fromClause + whereClause + orderClause;

      // Find absolute URL for the Web application root 
      // so this Web application will work even if it may be deployed at different URLs/ports
      String absoluteURL = javax.servlet.http.HttpUtils.getRequestURL(request).toString();
      absoluteURL = absoluteURL.substring(0, absoluteURL.lastIndexOf('/'));

      ResultSet rs = null;
      Statement stmt = survey.database.getStatement();
      try {       
        rs = stmt.executeQuery(sqlString);
        while (rs.next()) {       
          lastName = rs.getString(1);
          firstName = rs.getString(2);    
          discipline = rs.getString(3);
          String message = rs.getString(4);
          java.util.Date date = rs.getDate(5);
          writer.println("<hr/>");
          writer.println("<b>" + lastName + ",  " + firstName + "</b> <br/>");
          writer.println("<b>Undergraduate Discipline:</b> " + discipline + " <br/>");
          writer.println("<b>Survey Date:</b> " + date + " <br/>");
          writer.println("<b>Message:</b> " + message + " <br/>");
          // d_lastName and d_firstName identify which record to be deleted
          // query_parameters holds the current query criteria
          writer.println("<a href=\"" + absoluteURL + "/delete?" + query_parameters + 
                         "&d_lastName=" + lastName + "&d_firstName=" + firstName + "\">");
          writer.println("<font  color=\"red\"><em>Delete this record</em></font>");
          writer.println("</a><br/>");
        }
      } 
      catch (Exception e) {
        System.out.println("Failed in data retrieving: " + sqlString);
        System.out.println(e);
      }
      finally {
        survey.database.releaseStatement();
      }
   
      writer.println("<hr/><p/>");
      writer.println("<p align=\"CENTER\"><a href=\"" + absoluteURL + 
        "/query\"><font color=\"blue\"><em>Go Back for Further Query</em></font></a></p>");
      writer.println("</body></html>");
    }
    catch (Exception e){}   
  }
  
  public void doGet(HttpServletRequest request, HttpServletResponse response) {
    doPost(request, response);
  }
}
