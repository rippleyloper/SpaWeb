/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controlador.ControladorEspecialidad;
import controlador.ControladorListas;
import controlador.ControladorTipoServicio;
import java.io.IOException;
import java.io.PrintWriter;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Listas;

/**
 *
 * @author Tulio
 */
public class ServletEspecialidad extends HttpServlet {

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
            out.println("<title>Servlet ServletEspecialidad</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletEspecialidad at " + request.getContextPath() + "</h1>");
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
        Data:1 Obtener todos las especialidades
        */
        try {
            if(!request.getParameter("data").isEmpty()){
                String valor=request.getParameter("data");
                if(valor.equals("1")){
                    JsonArrayBuilder array=Json.createArrayBuilder();
                    for(Listas ls:new ControladorListas().obtenerEspecialidad()){
                        array.add(Json.createObjectBuilder().add("id", ls.getId()).add("nombre",ls.getNombres()));
                    }
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    Json.createWriter(response.getWriter()).writeArray(array.build());
                }
            }
        } catch (Exception e) {
            response.getWriter().print(e.getMessage());
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
        //processRequest(request, response);
        try {
            if(!request.getParameter("btnAgregar").isEmpty()){
                String valor=request.getParameter("btnAgregar");
                JsonObjectBuilder obj=Json.createObjectBuilder();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                request.setCharacterEncoding("UTF-8");
                if(valor.toLowerCase().equals("agregar")){
                    Listas ls=new Listas(request.getParameter("nombre"));
                    if(new ControladorEspecialidad().agregarTipo(ls)){
                        obj.add("estado", true).add("msj","Agregado con exito");
                    }else
                        obj.add("estado", false).add("msj","No se pudo agregar");
                    Json.createWriter(response.getWriter()).writeObject(obj.build());
                }
                else if(valor.toLowerCase().equals("modificar")){
                    Listas ls=new Listas(Integer.parseInt(request.getParameter("id")),request.getParameter("nombre"));
                    if(new ControladorEspecialidad().moficiarTipo(ls)){
                        obj.add("estado", true).add("msj","Modificado con exito");
                    }else
                        obj.add("estado", false).add("msj","No se pudo modificar");
                    Json.createWriter(response.getWriter()).writeObject(obj.build());
                }
                else if(valor.toLowerCase().equals("eliminar")){
                    int id =Integer.parseInt(request.getParameter("id"));
                    if(new ControladorEspecialidad().eliminarTipo(id)){
                        obj.add("estado", true).add("msj","Eliminado con exito");
                    }else
                        obj.add("estado", false).add("msj","No se pudo eliminar");
                    Json.createWriter(response.getWriter()).writeObject(obj.build());
                }
            }
        } catch (Exception e) {
            response.getWriter().print(e.getMessage());
        }
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
