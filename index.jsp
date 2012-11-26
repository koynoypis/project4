<%-- 
    Document   : index
    Created on : Nov 7, 2012, 4:02:38 PM
    Author     : koynoypis
--%>
<%@ page language="java" %>
<%@ page session="true"%>
<%@ page import="org.w3c.dom.*" %>
<%@ page import="javax.xml.parsers.DocumentBuilder" %>
<%@ page import="javax.xml.parsers.DocumentBuilderFactory" %>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Image Viewer</title>
    </head>
    <body>
        <h1>Welcome to Image Viewer</h1>
        <form action="UploadImage" method="post" enctype="multipart/form-data" name="form1" id="form1">
   
   <table border="0">
       <tr>
	       <td align="center"><b>Upload An Image</td>
	   </tr>
       <tr>
	       <td>
		       Specify file: <input name="file" type="file" id="file"></br>
                       Specify caption: <input name="text" type="text" id="caption">
                        <input type="submit" name="Submit" value="Submit file"/>
			</td>
		 </tr>
                 
                     
                             
                     
                
    </table>
	
 </form>
        <form action="ViewStats" method="post" enctype="multipart/form-data" name="form6" id="form6">
                         <input type="submit" name="Submit" value="View Stats"/>
                         </form>
        
        <%
       
            File imageDir = new File(config.getServletContext().getRealPath("/")+"uploadedImages");
            for(File imageFile : imageDir.listFiles()) {
                     String imageFileName = "uploadedImages/" + imageFile.getName();
                     String[] splits = imageFileName.split("\\.");
                    if(!splits[splits.length-1].equals("xml")){

                             DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
                             DocumentBuilder db =dbf.newDocumentBuilder();
                             Document doc=db.parse("/usr/share/tomcat6/webapps/project4/uploadedImages/"+imageFile.getName()+".xml");
                             NodeList nl = doc.getElementsByTagName("image");
                             NodeList nameNlc =  doc.getElementsByTagName("caption");
                             Element nameElements=(Element)nameNlc.item(0);
                             String nameTagValue=nameElements.getChildNodes().item(0).getNodeValue();
                
                %>
                <div class="image">
                    <div class="img">
                    <img  src="<%= imageFileName %>" width="200" height="200">
                    <p><%=nameTagValue%></p>
                    </div>
                    <div class="actions">
                    <form action="Delete" method="post" name="form1" id="form4">
                    <input name="filename" type="hidden" value="<%= imageFileName %>" />
                    <span><input type="submit" name="Submit" value="Delete"/></span>
                </form>
                <form action="EditCaption" method="post" name="form1" id="form5">
                    <input name="filename" type="hidden" value="<%= imageFileName %>" />
                    <input name="text" type="text" id="caption"/>  
                    <span><input type="submit" name="Submit" value="EditCaption"/></span>
                 </form>
                                              
                 
                  
                <%
           } %>
               
            <%
        }
    
    
%>
</br>
    </body>
</html>
