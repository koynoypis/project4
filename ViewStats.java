/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.entity.*;
import org.jfree.chart.labels.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.*;
import org.jfree.chart.urls.*;
import org.jfree.data.category.*;
import org.jfree.data.general.*;

/**
 *
 * @author koynoypis
 */
public class ViewStats extends HttpServlet {

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
            out.println("<title>Servlet ViewStats</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewStats at " + request.getContextPath() + "</h1>");
            out.println("Number of active sessions : " + SessionCounter.getSessionNum() );
            out.println("<p></p>");
            out.println("Number of total daily sessions : " + SessionCounter.getTotalSessionNum(7) );
            out.println("<p></p>");
             
            final double[][] data = new double[][]{
  
  {SessionCounter.getTotalSessionNum(1),SessionCounter.getTotalSessionNum(2),SessionCounter.getTotalSessionNum(3),
  SessionCounter.getTotalSessionNum(4),SessionCounter.getTotalSessionNum(5),SessionCounter.getTotalSessionNum(6),
  SessionCounter.getTotalSessionNum(7),}
  
 };
 

  final CategoryDataset dataset = 
   DatasetUtilities.createCategoryDataset(
  "Web App ","",data);

  JFreeChart chart = null;
  BarRenderer renderer = null;
  CategoryPlot plot = null;


  final CategoryAxis categoryAxis = new CategoryAxis("Day");
  final ValueAxis valueAxis = new NumberAxis("Number of Sessions");
  renderer = new BarRenderer();

  plot = new CategoryPlot(dataset, categoryAxis, valueAxis, 
  renderer);
  plot.setOrientation(PlotOrientation.VERTICAL);
  chart = new JFreeChart("Sessions", JFreeChart.DEFAULT_TITLE_FONT, 
  plot, true);

  chart.setBackgroundPaint(new Color(249, 231, 236));

  Paint p1 = new GradientPaint(
 0.0f, 0.0f, new Color(16, 89, 172), 0.0f, 0.0f, new Color
   (201, 201, 244));

  renderer.setSeriesPaint(1, p1);

  

  plot.setRenderer(renderer);

  try {
  final ChartRenderingInfo info = new ChartRenderingInfo
   (new StandardEntityCollection());
 final File file1 = new File("/usr/share/tomcat6/webapps/project4/barchart.png");
  ChartUtilities.saveChartAsPNG(file1, chart, 600, 400, info);
  } catch (Exception e) {
 out.println(e);
  }

    
            out.println("<IMG SRC=\"barchart.png\" WIDTH=\"600\" HEIGHT=\"400\" BORDER=\"0\" USEMAP=\"#chart\">");
            out.println("</br><a href=\"http://83.212.96.35:8080/project4/index.jsp\">Back To Gallery</a></br>");
            out.println("</body>");
            out.println("</html>");
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
