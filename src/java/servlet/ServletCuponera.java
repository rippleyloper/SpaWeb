/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controlador.ControladorListas;
import controlador.ControladorServicio;
import controlador.ControladorTrabajador;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Listas;
import modelo.Servicio;
import modelo.Trabajador;

/**
 *
 * @author Tulio
 */
public class ServletCuponera extends HttpServlet {

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
            out.println("<title>Servlet ServletCuponera</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletCuponera at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        /*
        data:1 obtener tipoServicio;
        data:2 obtener categoria servicio;
        data:3 obtener Servicios;
        data:4 obtener detalle servicio
        data:5 obtener Especialidad Trabajador
        data:6 obtener Trabajadores con especialidad
        */
        try {
            if(!request.getParameter("data").isEmpty()){
                int data=Integer.parseInt(request.getParameter("data"));
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                if(data==1){
                    JsonArrayBuilder array=Json.createArrayBuilder();
                    for(Listas ls:new ControladorListas().obtenerTipoServicio())
                        array.add(Json.createObjectBuilder().add("id", ls.getId()).add("nombre",ls.getNombres()));
                    Json.createWriter(response.getWriter()).writeArray(array.build());
                }
                else if(data==2){
                    JsonArrayBuilder array=Json.createArrayBuilder();
                    for(Listas ls:new ControladorListas().obtenerCategoriaServicio(Integer.parseInt(request.getParameter("idSer"))))
                        array.add(Json.createObjectBuilder().add("id", ls.getId()).add("nombre", ls.getNombres()));
                    Json.createWriter(response.getWriter()).writeArray(array.build());
                }else if(data==3){
                    JsonArrayBuilder array=Json.createArrayBuilder();
                    for(Listas ls:new ControladorListas().obtenerServicio(Integer.parseInt(request.getParameter("idCat"))))
                        array.add(Json.createObjectBuilder().add("id",ls.getId()).add("nombre",ls.getNombres()));
                    Json.createWriter(response.getWriter()).writeArray(array.build());
                }else if(data==4){
                    JsonObjectBuilder obj=Json.createObjectBuilder();
                    Servicio ser=new ControladorServicio().buscarServicio(Integer.parseInt(request.getParameter("idCat")));
                    obj.add("id",ser.getIdServicio()).add("nombre",ser.getNombre()).add("precio",ser.getPrecio()).add("duracion", ser.getDuracion());
                    Json.createWriter(response.getWriter()).writeObject(obj.build());
                }
                else if(data==5){
                    JsonArrayBuilder array=Json.createArrayBuilder();
                    for(Listas ls:new ControladorListas().obtenerEspecialidad())
                        array.add(Json.createObjectBuilder().add("id",ls.getId()).add("nombre",ls.getNombres()));
                    Json.createWriter(response.getWriter()).writeArray(array.build());
                }else if(data==6){
                    JsonArrayBuilder array=Json.createArrayBuilder();
                    for(Trabajador tra:new ControladorTrabajador().obtener(Integer.parseInt(request.getParameter("idEs")))){
                        array.add(Json.createObjectBuilder().add("id",tra.getDni()).add("nombre", tra.getNombre()+" "+tra.getApellido()));
                    }
                    Json.createWriter(response.getWriter()).writeArray(array.build());
                }
            
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
