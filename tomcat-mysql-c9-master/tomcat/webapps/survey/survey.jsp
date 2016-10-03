<html>
<head>
<title>Survey</title>
<script type="text/javascript" src="survey.js"></script>
</head>
<body>
<h2 align="center"><em>Student Survey</em></h2>

<form name="survey_form" method="post" action="surveyProcessing" onsubmit="return dataCheck(this)">
  <table align="center" style="width: 600px">
    <colgroup><col align="right"></col><col align="left"></col></colgroup>
    <tr><th align="right">Last Name</th><td><input type="text" name="tf_lastName"/></td></tr>
    <tr><th align="right">First Name</th><td><input type="text" name="tf_firstName"/></td></tr>
    <tr>
      <th align="right">Undergraduate Discipline</th>
      <td><select name="cbo_discipline">
            <option>Please select a discipline</option>
        		<option>Art</option>
        		<option>Business</option>
        		<option>Education</option>
        		<option>Engineering</option>
        		<option>Health Care</option>
        		<option>Science</option>
        		<option>Other</option>
          </select>
      </td>
    </tr>
    <tr><th align="right">Message</th>
        <td><textarea name="ta_message" cols="40" rows="5"></textarea></td>
    </tr>
    <tr>
      <td/>
      <td>
        <input type="submit" value="Submit"/>&nbsp;&nbsp;
        <input type="reset" value="Cancel"/>     
	    </td>
    </tr>
  </table>
  <br/>
</form>
<p/>

<%
   // Find absolute URL for the Web application root 
   // so this Web application will work even if it may be deployed at different URLs/ports
   String absoluteURL = javax.servlet.http.HttpUtils.getRequestURL(request).toString();
   absoluteURL = absoluteURL.substring(0, absoluteURL.lastIndexOf('/'));
%>
<div align="center"><a href="<%=absoluteURL%>/query">Search Survey Records</a></div>

<table border="0" align="center" width="80%">
<tr>
<td>
<%  String message = request.getParameter("message");
    String color = request.getParameter("color");
    if (color == null) color = "red";
    if (message != null) {
%>
       <p align="center"><font color="<%=color%>"><%=message%></font></p>
<%
    }
%>
</td>
</tr>
</table>
</body>
</html>
