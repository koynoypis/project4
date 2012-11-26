/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Iterator;
import java.io.File;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.*;

/**
 *
 * @author koynoypis
 */
public class UploadImage extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UploadImage</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UploadImage at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
 if (!isMultipart) {
 } else {
	   FileItemFactory factory = new DiskFileItemFactory();
	   ServletFileUpload upload = new ServletFileUpload(factory);
	   List items = null;
	   try {
		   items = upload.parseRequest(request);
	   } catch (FileUploadException e) {
		   e.printStackTrace();
	   }
	   Iterator itr = items.iterator();
	   while (itr.hasNext()) {
	   FileItem item = (FileItem) itr.next();
	   if (item.isFormField()) {
	   } else {
		   try {
			   String itemName = item.getName();
                           if(itemName.equals("")){
                              response.sendRedirect("index.jsp");
                           }
			   File savedFile = new File(getServletContext().getRealPath("/")+"uploadedImages/"+itemName);
			   item.write(savedFile);  
		
			   out.println("<tr><td><b>Your file has been saved at the loaction:</b></td></tr><tr><td><b>"+getServletContext().getRealPath("/")+"uploadedImages"+"\\"+itemName+"</td></tr>");
                           out.println("</br><a href=\"http://83.212.96.35:8080/project4/index.jsp\">View Images!</a></br>");
                           FileWriter xml = new FileWriter(getServletContext().getRealPath("/")+"uploadedImages/"+savedFile.getName() + ".xml");
                           item = (FileItem) itr.next();
                           BufferedWriter writer = new BufferedWriter(xml);
                           
                           String caption = item.getString();
                           if(caption.equals("")){caption = "No Caption";}
                           
                           writer.write("<?xml version=\"1.0\"?>");
                           writer.write("<image>");
                           writer.write("<caption>"+caption+"</caption>");
                           
                           writer.write("<url>"+getServletContext().getRealPath("/")+"uploadedImages/"+savedFile.getName()+"</url>");
                           
                           writer.write("</image>");
                           writer.close();
                           xml.flush();
                           xml.close();
                           
                            
                           
                           
		   }
                   catch (Exception e) {
			   e.printStackTrace();
		   }
                   
	   }
           
	   }
   }
        } finally {            
            out.close();
           
        }
        
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
