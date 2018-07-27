/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controlador.ControladorServicio;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Servicio;

/**
 *
 * @author Tulio
 */
public class ServletServicio extends HttpServlet {

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
            out.println("<title>Servlet ServletServicio</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletServicio at " + request.getContextPath() + "</h1>");
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
        Data:1 Obtener servicio por categoria
        */
                
        try {
          if(!request.getParameter("data").isEmpty()){
                String valor=request.getParameter("data");
                if(valor.equals("1")){
                    ArrayList<Servicio> servicios=new ControladorServicio().obtenerPorTipo(Integer.parseInt(request.getParameter("id")));
                    if(!servicios.isEmpty()){
                        JsonArrayBuilder array=Json.createArrayBuilder();
                        for(Servicio ser:servicios){
                            array.add(Json.createObjectBuilder().add("servicio", 
                                       Json.createObjectBuilder().add("idServicio", ser.getIdServicio())
                                            .add("idCategoria",ser.getIdCategoria())
                                            .add("nombre",ser.getNombre())
                                            .add("duracion",ser.getDuracion())
                                            .add("precio",ser.getPrecio())
                                            .add("detalle",ser.getDetalle())
                                    ));
                        }
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        Json.createWriter(response.getWriter()).writeArray(array.build());
                    }
                }
                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
                request.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                String valor=request.getParameter("btnAgregar");
                JsonObjectBuilder ob=Json.createObjectBuilder();
                if(valor.toLowerCase().equals("agregar")){
                    Servicio ser=new Servicio(0, Integer.parseInt(request.getParameter("addCategoria")), request.getParameter("nombre"), Integer.parseInt(request.getParameter("duracion")), Integer.parseInt(request.getParameter("precio")), request.getParameter("detalle"));
                    if(new ControladorServicio().agregarServicio(ser)){
                        ob.add("estado", true).add("msj","Agregado con exito");
                        Json.createWriter(response.getWriter()).writeObject(ob.build());
                    }
                    else{
                        ob.add("estado", false).add("msj","no se pudo agregar");
                        Json.createWriter(response.getWriter()).writeObject(ob.build());
                    }
                }
                if(valor.toLowerCase().equals("eliminar")){
                    if(new ControladorServicio().eliminarServicio(Integer.parseInt(request.getParameter("id")))){
                        ob.add("estado", true).add("msj","Eliminado con exito");
                        Json.createWriter(response.getWriter()).writeObject(ob.build());
                    }
                    else{
                        ob.add("estado", false).add("msj","No se pudo eliminar");
                        Json.createWriter(response.getWriter()).writeObject(ob.build());
                    }
                }
                if(valor.toLowerCase().equals("modificar")){
                    Servicio ser=new Servicio(Integer.parseInt(request.getParameter("id")), Integer.parseInt(request.getParameter("addCategoria")), request.getParameter("nombre"), Integer.parseInt(request.getParameter("duracion")), Integer.parseInt(request.getParameter("precio")), request.getParameter("detalle"));
                    if(new ControladorServicio().modificar(ser)){
                        ob.add("estado", true).add("msj","Modificado  con exito");
                        Json.createWriter(response.getWriter()).writeObject(ob.build());
                    }
                    else{
                        ob.add("estado", false).add("msj","no se pudo modificar");
                        Json.createWriter(response.getWriter()).writeObject(ob.build());
                    }
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
                                                    