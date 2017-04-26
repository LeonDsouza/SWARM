<%-- 
    Document   : FetchData
    Created on : 26 Apr, 2017, 5:33:12 PM
    Author     : Leon
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0//EN" "http://www.w3.org/TR/REC-html40/strict.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:directive.include file="Header.jsp" />
        <div class="main-content">
            <div class="main-content-inner">


                <div class="page-content">



                    <center>
                        <div class="row">
                            <div class="col-xs-12">
                                <!-- PAGE CONTENT BEGINS -->
                                <div class="clearfix">

                                    <div class="row col-xs-12" >





                                        <form method="post" action="/hitSparql">
                                            <label>Enter SPARQL Query</label> <br><textarea name="query" placeholder="Enter SPARQL query to be fired" cols="100" rows="5" /> </textarea><br>
                                            <br>
                                            <br>

                                            Enter URL of SPARQL endpoint &nbsp;<input type ="text" name="endpoint" />
                                            <br>
                                            <br>
                                            <br>
                                            Select Format of Output &nbsp;
                                            <select name="format">
                                                <option> CSV </option>
                                                <option> TSV </option>

                                                <option> XML </option>
                                                <option> JSON </option>
                                            </select>
                                            <br>
                                            <br>
                                            <br>
                                            <input class="btn btn-sm btn-info" type="submit" value="Run Query">
                                        </form>


                                    </div>
                                </div>
                            </div>
                        </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>
</div>

</body>
</html>
