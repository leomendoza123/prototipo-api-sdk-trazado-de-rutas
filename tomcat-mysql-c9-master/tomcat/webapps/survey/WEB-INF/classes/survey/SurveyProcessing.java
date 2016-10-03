package survey;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class SurveyProcessing extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response) {
    Survey survey = new Survey();
    try {
      String message = "";
      try {
        survey.lastName = request.getParameter("tf_lastName");
        survey.firstName = request.getParameter("tf_firstName");
        survey.discipline = request.getParameter("cbo_discipline");
        survey.message = request.getParameter("ta_message");
        survey.surveyDate = new java.util.Date();
      }
      catch (Exception e) {
        message = "Input error: " + e;
        RequestDispatcher dispatcher = request.getRequestDispatcher("/survey?color=red&message=" + message);
        dispatcher.forward(request, response);
      }
      if (survey.isRecordAvailable(survey.lastName, survey.firstName)) {
        survey.delete(survey.lastName, survey.firstName);
        message += "Your previous record has been deleted.<br/>";
      }
      survey.saveData();
      message += "Thank you for submitting your survey data. Your data has been securely saved in our survey database.";
      message += "\n\n<div align=\"center\"> <h4><a href=\"javascript:window.close()\">Close Window</a></h4> </div>";
      RequestDispatcher dispatcher = request.getRequestDispatcher("/survey?color=blue&message=" + message);
      dispatcher.forward(request, response);
    }
    catch (Exception e) {}
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) {
    doPost(request, response);
  }
}