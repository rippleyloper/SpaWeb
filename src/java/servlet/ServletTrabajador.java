/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controlador.ControladorCliente;
import controlador.ControladorListas;
import controlador.ControladorTrabajador;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Dispatch;
import modelo.Listas;
import modelo.Trabajador;
import sun.rmi.server.Dispatcher;

/**
 *
 * @author Tulio
 */
public class ServletTrabajador extends HttpServlet {

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
            out.println("<title>Servlet ServletTrabajador</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletTrabajador at " + request.getContextPath() + "</h1>");
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
            data:1 Trabajadores
            data:2 Genero
            data:3 Departamento
            data:4 Barrio
            data:5 especialidad
            data:6 sucursal
            data:7 detalle
            data:8 eliminar
            data:9 listar activar
            data:10 activar
            */
            if(request.getParameter("data").equals("1")){
                request.getSession().setAttribute("trabajadores", new ControladorTrabajador().obtener());
                response.sendRedirect("AdmonTrabajador.jsp");
            }
            else if(request.getParameter("data").equals("2")){
                JsonArrayBuilder array=Json.createArrayBuilder();
                for(Listas lista:new ControladorListas().obtenerGenero())
                    array.add(Json.createObjectBuilder().add("id", lista.getId()).add("nombre",lista.getNombres()));
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                Json.createWriter(response.getWriter()).writeArray(array.build());
            }else if(request.getParameter("data").equals("3")){
                JsonArrayBuilder array=Json.createArrayBuilder();
                for(Listas lista:new ControladorListas().obtenerDepartamento())
                    array.add(Json.createObjectBuilder().add("id",lista.getId()).add("nombre", lista.getNombres()));
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                Json.createWriter(response.getWriter()).writeArray(array.build());
            }else if(request.getParameter("data").equals("4")){
                JsonArrayBuilder array=Json.createArrayBuilder();
                for(Listas lita:new ControladorListas().obtenerBario(Integer.parseInt(request.getParameter("idBa"))))
                    array.add(Json.createObjectBuilder().add("id",lita.getId()).add("nombre", lita.getNombres()));
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                Json.createWriter(response.getWriter()).writeArray(array.build());
            }else if(request.getParameter("data").equals("5")){
                JsonArrayBuilder array=Json.createArrayBuilder();
                for(Listas lista:new ControladorListas().obtenerEspecialidad())
                    array.add(Json.createObjectBuilder().add("id",lista.getId()).add("nombre", lista.getNombres()));
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                Json.createWriter(response.getWriter()).writeArray(array.build());
            }
            else if(request.getParameter("data").equals("6")){
                JsonArrayBuilder array=Json.createArrayBuilder();
                for(Listas lista:new ControladorListas().obtenerSucursal())
                    array.add(Json.createObjectBuilder().add("id",lista.getId()).add("nombre", lista.getNombres()));
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                Json.createWriter(response.getWriter()).writeArray(array.build());
            }
            else if(request.getParameter("data").equals("10")){
                JsonArrayBuilder array=Json.createArrayBuilder();
                for(Trabajador trabajador: new ControladorTrabajador().obtener())
                   array.add(Json.createObjectBuilder().add("DNI",trabajador.getDni()).add("nombre", trabajador.getNombre()).add("apellido", trabajador.getApellido()));
                
                response.setContentType("application/json");
                  response.setCharacterEncoding("UTF-8");
                Json.createWriter(response.getWriter()).writeArray(array.build());
                
            }
            else if(request.getParameter("data").equals("7")){
                JsonObjectBuilder obj=Json.createObjectBuilder();
                JsonArrayBuilder arra=Json.createArrayBuilder();
                Trabajador tra=new ControladorTrabajador().obtenerTrabajador(request.getParameter("dni"));
                if(tra!=null){
                    for(Listas ls:tra.getIdEspecialidad())
                        arra.add(Json.createObjectBuilder().add("id", ls.getId()).add("nombre", ls.getNombres()));
                    
                    obj.add("dni",tra.getDni())
                         .add("nombre", tra.getNombre())
                            .add("apellido", tra.getApellido())
                            .add("genero", tra.getIdGenero())
                            .add("telefono",tra.getNumero())
                            .add("correo",tra.getCorreo())
                            .add("fecha",tra.getFechaNacimiento())
                            .add("direccion",tra.getDireccion())
                            .add("departamento",new ControladorCliente().obtenerDepartamento(tra.getIdBarrio()))
                            .add("barrioo",tra.getIdBarrio())
                            .add("sucursal",tra.getIdSucursal().getId())
                            .add("especialidad",arra);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    Json.createWriter(response.getWriter()).writeObject(obj.build());
                }
            }
            else if(request.getParameter("data").equals("8")){
                if(new ControladorTrabajador().eliminar(request.getParameter("dni")))
                    request.getSession().setAttribute("msg", new Listas(1, "Eliminado correctamente"));
                else
                    request.getSession().setAttribute("msg", new Listas(2, "No se pudo eliminar"));
                response.sendRedirect("AdmonTrabajador.jsp");
            }else if(request.getParameter("data").equals("9")){
                request.getSession().setAttribute("trabajadoresB", new ControladorTrabajador().obtenerEliminado());
                response.sendRedirect("activarTrabajador.jsp");
            }else if(request.getParameter("data").equals("10")){
                if(new ControladorTrabajador().activar(request.getParameter("dni")))
                    request.getSession().setAttribute("msg", new Listas(1, "Activado correctamente"));
                else
                    request.getSession().setAttribute("msg", new Listas(2, "No se pudo activar"));
                response.sendRedirect("activarTrabajador.jsp");
            }
            else if(request.getParameter("data").equals("11")){
                ControladorTrabajador controladorTrabajador = new ControladorTrabajador();
                String  dniTrabajador = "";
                dniTrabajador =   controladorTrabajador.obtenerDniTrabajador(Integer.parseInt(request.getParameter("idTrabajador")));
                JsonObjectBuilder obj=Json.createObjectBuilder();
                obj.add("dni", dniTrabajador);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    Json.createWriter(response.getWriter()).writeObject(obj.build());
            }
            
        } catch (Exception e) {
            request.getSession().setAttribute("msg", new Listas(2, "Problemas de conexion, numero de error C025"));
            String referer = request.getHeader("Referer");
            response.sendRedirect(referer);
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
            request.setCharacterEncoding("UTF-8");
            if(request.getParameter("btnSubmit").toLowerCase().equals("agregar")){
                ArrayList<Listas> ls=new ArrayList<Listas>();
                for(String es:request.getParameterValues("especialidad"))
                    ls.add(new Listas(Integer.parseInt(es)));
                Trabajador tra=new Trabajador(ls, new Listas(2),Integer.parseInt(request.getParameter("barrio2")),Integer.parseInt(request.getParameter("genero")), request.getParameter("dni"), request.getParameter("nombre"), request.getParameter("apellido"), request.getParameter("direccion"), new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fecha")), request.getParameter("numero"), request.getParameter("correo"));
                if(new ControladorTrabajador().crearTrabajador(tra,(int)request.getSession().getAttribute("idSucu")))
                    request.getSession().setAttribute("msg", new Listas(1, "Agregado correctamente"));
                else
                    request.getSession().setAttribute("msg", new Listas(2, "Problemas al agregar, intentelo de nuevo."));
                response.sendRedirect("AdmonTrabajador.jsp");
            }else if(request.getParameter("btnSubmit").toLowerCase().equals("modificar")){
                ArrayList<Listas> ls=new ArrayList<Listas>();
                for(String es:request.getParameterValues("especialidad"))
                    ls.add(new Listas(Integer.parseInt(es)));
                Trabajador tra=new Trabajador(ls, new Listas(2),Integer.parseInt(request.getParameter("barrio2")),Integer.parseInt(request.getParameter("genero")), request.getParameter("dni"), request.getParameter("nombre"), request.getParameter("apellido"), request.getParameter("direccion"), new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fecha")), request.getParameter("numero"), request.getParameter("correo"));
                if(new ControladorTrabajador().modificar(tra))
                    request.getSession().setAttribute("msg", new Listas(1, "Modificado correctamente"));
                else
                    request.getSession().setAttribute("msg", new Listas(2, "Problemas al modificar, intentelo de nuevo."));
                
                response.sendRedirect("AdmonTrabajador.jsp");
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            request.getSession().setAttribute("msg", new Listas(2,"Problemas de conexion, numero de error C025"));
            response.sendRedirect("AdmonTrabajador.jsp");
        }
        catch (Exception e) {
            request.getSession().setAttribute("msg", new Listas(2,e.getMessage()));
            response.sendRedirect("AdmonTrabajador.jsp");
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
