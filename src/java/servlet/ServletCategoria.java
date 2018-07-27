/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controlador.ControladorCategoria;
import controlador.ControladorListas;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Categoria;
import modelo.Listas;

/**
 *
 * @author Tulio
 */
public class ServletCategoria extends HttpServlet {

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
            out.println("<title>Servlet ServletCategoria</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletCategoria at " + request.getContextPath() + "</h1>");
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
        try {
            /*
            data:1  Obtener todos las categorias por id
            data:2  Obtener todos los tipo servicios
            */
            if(request.getParameter("data")!=null){
                String data=request.getParameter("data");
                if(data.equals("1")){
                    JsonArrayBuilder array=Json.createArrayBuilder();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    for(Listas ls:new ControladorListas().obtenerCategoriaServicio(Integer.parseInt(request.getParameter("id"))))
                        array.add(Json.createObjectBuilder().add("id",ls.getId()).add("nombre", ls.getNombres()));
                    Json.createWriter(response.getWriter()).writeArray(array.build());
                }
                else if(data.equals("2")){
                    JsonArrayBuilder array=Json.createArrayBuilder();
                    for(Listas ls:new ControladorListas().obtenerTipoServicio())
                        array.add(Json.createObjectBuilder().add("id",ls.getId()).add("nombre", ls.getNombres()));
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
        //processRequest(request, response);
        try {
            if(!request.getParameter("btnAgregar").isEmpty()){
                String valor=request.getParameter("btnAgregar");
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                request.setCharacterEncoding("UTF-8");
                if(valor.toLowerCase().equals("agregar")){
                    System.out.println(request.getParameter("nombre")+" "+ Integer.parseInt(request.getParameter("tipo")));
                    System.out.println(request.getParameter("nombre")+" "+ Integer.parseInt(request.getParameter("tipo")));
                    Categoria ca=new Categoria(request.getParameter("nombre"), Integer.parseInt(request.getParameter("tipo")));
                    JsonObjectBuilder obj=Json.createObjectBuilder();
                    if(new ControladorCategoria().agregarCategoria(ca)){ 
                        obj.add("estado", true).add("msj","Agregado con exito");
                    }else{
                        obj.add("estado", false).add("msj","Problemas al agregar");
                    }
                    Json.createWriter(response.getWriter()).writeObject(obj.build());
                }else if(valor.toLowerCase().equals("modificar")){
                    Categoria ca=new Categoria(Integer.parseInt(request.getParameter("id")),request.getParameter("nombre"), Integer.parseInt(request.getParameter("tipo")));
                    System.out.println(request.getParameter("id")+" "+request.getParameter("nombre")+" "+ Integer.parseInt(request.getParameter("tipo")));
                    JsonObjectBuilder obj=Json.createObjectBuilder();
                    if(new ControladorCategoria().modificarCategoria(ca)){ 
                        obj.add("estado", true).add("msj","Modificado con exito");
                    }else{
                        obj.add("estado", false).add("msj","No se puede modificar");
                    }
                    Json.createWriter(response.getWriter()).writeObject(obj.build());
                }
                else if(valor.toLowerCase().equals("eliminar")){
                    JsonObjectBuilder obj=Json.createObjectBuilder();
                    if(new ControladorCategoria().eliminarCategoria(Integer.parseInt(request.getParameter("id")))){ 
                        obj.add("estado", true).add("msj","Eliminado con exito");
                    }else{
                        obj.add("estado", false).add("msj","No se puede eliminar");
                    }
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
