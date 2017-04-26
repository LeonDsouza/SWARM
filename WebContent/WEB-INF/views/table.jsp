<%-- 
    Document   : table
    Created on : 9 Apr, 2017, 1:16:25 PM
    Author     : Leon
--%>

<%@page import="java.io.DataInputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0//EN" "http://www.w3.org/TR/REC-html40/strict.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body>
        
        <c:set var="test" value= "${output}"/>
        
        <jsp:directive.include file="Header.jsp" />
        <table cellspacing='0' border="1" >
            <tr>
                <td height = "75"> <b>Antecedent </b></td>
                <td> <b>Consequent </b></td>
                <td> <b>Confidence </b></td>
            </tr>
<% 
    String resp = (String)pageContext.getAttribute("test");
 //String fName = "C:/Users/leons/Desktop/PMS_FINAL_REPO-master/PMS_FINAL_REPO-master/target/PMS_v1-0.0.1-SNAPSHOT/sample_data_1.csv1.csv";
 String fName = resp;
 String thisLine; 
 int count=0; 
 FileInputStream fis = new FileInputStream(fName);
 DataInputStream myInput = new DataInputStream(fis);
 int i=0; 

while ((thisLine = myInput.readLine()) != null)
{
String strar[] = thisLine.split(",");
%><tr><%
for(int j=0;j<strar.length;j++)
 {
if(i!=0) 
 {
out.print("<td> " +strar[j]+ "</td> ");
 }
else
{
out.print(" <td> " +strar[j]+ " </td> ");
}
i++;
} 
%></tr><%
} 
 %>
</table>

<jsp:directive.include file="Footer.jsp" />
    </body>
</html>
