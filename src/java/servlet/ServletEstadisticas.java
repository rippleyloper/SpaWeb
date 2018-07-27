/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controlador.ControladorEstadisticas;
import java.io.IOException;
import java.io.PrintWriter;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tulio
 */
public class ServletEstadisticas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletEstadisticas</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletEstadisticas at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*
        data 1:obtener estadisticas
        */
        try {
            if(!request.getParameter("data").isEmpty()){
                if(request.getParameter("data").equals("1")){
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    JsonArrayBuilder array=Json.createArrayBuilder();
                    array.add(Json.createObjectBuilder().add("clienteA", new ControladorEstadisticas().obtenerClientesA()));
                    array.add(Json.createObjectBuilder().add("clienteI", new ControladorEstadisticas().obtenerClientesI()));
                    array.add(Json.createObjectBuilder().add("serviciosH", new ControladorEstadisticas().obtenerServiciosHoy()));
                    array.add(Json.createObjectBuilder().add("totalA", new ControladorEstadisticas().obtenerTotalAgendas()));
                    array.add(Json.createObjectBuilder().add("totalAs", new ControladorEstadisticas().obtenerTotalAsistio()));
                    array.add(Json.createObjectBuilder().add("totalC", new ControladorEstadisticas().obtenerTotalCancelado()));
                    array.add(Json.createObjectBuilder().add("totalE", new ControladorEstadisticas().obtenerTotalEspera()));
                    array.add(Json.createObjectBuilder().add("totalNoA", new ControladorEstadisticas().obtenerTotalNoA()));
                    array.add(Json.createObjectBuilder().add("trabajadorA", new ControladorEstadisticas().obtenerTrabajadoresA()));
                    array.add(Json.createObjectBuilder().add("trabajadorI", new ControladorEstadisticas().obtenerTrabajadoresI()));
                    array.add(Json.createObjectBuilder().add("calcularT", new ControladorEstadisticas().calcularTotal()));
                    array.add(Json.createObjectBuilder().add("calcularP", new ControladorEstadisticas().calcularPagado()));
                    array.add(Json.createObjectBuilder().add("calcularTD", new ControladorEstadisticas().calcularTotalDescuento()));
                    array.add(Json.createObjectBuilder().add("cumpleMes",new ControladorEstadisticas().obtenerCumMen()));
                    array.add(Json.createObjectBuilder().add("cumpleDia",new ControladorEstadisticas().obtenerCumDia()));
                    Json.createWriter(response.getWriter()).writeArray(array.build());
                }else if(request.getParameter("data").equals("2")){
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    JsonArrayBuilder array=Json.createArrayBuilder();
                    array.add(Json.createObjectBuilder().add("serviciosH", new ControladorEstadisticas().obtenerServiciosHoy()));
                    Json.createWriter(response.getWriter()).writeArray(array.build());
                }
            }
        } catch (Exception e) {
            
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
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
