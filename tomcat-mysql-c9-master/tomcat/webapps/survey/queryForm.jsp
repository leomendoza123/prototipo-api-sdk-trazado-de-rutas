<html>
<head>
<title>Search Survey Database</title>
</head>
<body>
<h2 align="center"><em>Survey Data Retrieval</em></h2>

<table align="center" width="80%">
<tr><td>
<p>
This form allows administrators to search for student survey data. You may use any combination 
of the following search criteria to get the related student survey records.
</p>
</td></tr>
</table>

<form name="search_form" method="post" action="queryFormProcessing">
<table width="57%"  align="center">
  <colgroup><col align="right"></col><col align="left"></col></colgroup>
  <tr>
    <th align="right">Last Name</th>
    <td><input type="text" name="tf_lastName"/></td>
  </tr>
  <tr>
    <th align="right">First Name</th>
    <td><input type="text" name="tf_firstName"/></td>
  </tr>
  <tr>
    <th align="right">Undergraduate Discipline</th>
    <td><select name="cbo_discipline" size="1">
          <option selected>Select All</option>
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
  <tr>
    <th align="right">Message Key Phrase</th>
    <td><input name="tf_keyPhrase" type="text" size="40"/></td>
  </tr>
  <tr/>
  <tr>
    <td colspan="2" align="center"><input name="submit" type="submit" value="Submit"/>
      &nbsp;&nbsp;
      <input name="reset" type="reset" value="Reset"/></td>
    </td>
  <tr>
</table>
</form>
<%
   // Find absolute URL for the Web application root
   String absoluteURL = javax.servlet.http.HttpUtils.getRequestURL(request).toString();
   absoluteURL = absoluteURL.substring(0, absoluteURL.lastIndexOf('/'));
%>
<div align="center"><a href="<%=absoluteURL%>/">Take Survey Now</a></div>
</body>
</html>
