/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controlador.ControladorCliente;
import controlador.ControladorListas;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;
import modelo.Persona;
import modelo.Listas;

/**
 *
 * @author Tulio
 */
public class ServletCliente extends HttpServlet {

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
            out.println("<title>Servlet ServletCliente</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletCliente at " + request.getContextPath() + "</h1>");
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
        //data=1 listar
        //data=2 eliminar
        //data=3 Detalle
        //data=4 Modificar
        //data=5 Genero
        //data=6 departamento
        //data=7 barrio
        //data=8 listar Eliminados
        //data=9 activar Cliete
        try {
            if(request.getParameter("data").equals("1")){
                //Lista todos los clientes
                ControladorCliente controCliente= new ControladorCliente();
                request.getSession().setAttribute("clientes", controCliente.obtenerClientes());
                response.sendRedirect("AdmonCliente.jsp");
            }
            else if(request.getParameter("data").equals("2")){
                ControladorCliente conClie=new ControladorCliente();
                if(conClie.eliminar(request.getParameter("dni"))>0)
                    request.getSession().setAttribute("1", "Eliminado con exito");
                else
                    request.getSession().setAttribute("1", "Eliminado con exito");
                response.sendRedirect("AdmonCliente.jsp");
            }
            else if(request.getParameter("data").equals("3")){
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                JsonObjectBuilder jsonObj=Json.createObjectBuilder();
                Persona per=new ControladorCliente().obtenerCliente(request.getParameter("dni"));
                jsonObj.add("dni", per.getDni())
                        .add("nombre", per.getNombre())
                        .add("apellido", per.getApellido())
                        .add("idGenero", per.getIdGenero())
                        .add("telefono", per.getNumero())
                        .add("correo", per.getCorreo())
                        .add("direccion",per.getDireccion())
                        .add("fecha",per.getFechaNacimiento())
                        .add("departamento",new ControladorCliente().obtenerDepartamento(per.getIdBarrio()))
                        .add("barrio", per.getIdBarrio());
                Json.createWriter(response.getWriter()).writeObject(jsonObj.build());
            }
            else if(request.getParameter("data").equals("5")){
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                JsonArrayBuilder jsonO=Json.createArrayBuilder();
                //String data="{'lista':[";
                for(Listas listas:new ControladorListas().obtenerGenero()){
                    //data+=listas.toString();
                    jsonO.add(Json.createObjectBuilder().add("id", listas.getId()).add("nombre",listas.getNombres()));
                }
                JsonArray obj=jsonO.build();
                //data=data.substring(0, data.length()-1)+"]}";
                //response.getWriter().print(data);
                Json.createWriter(response.getWriter()).writeArray(obj);
            }
            else if(request.getParameter("data").equals("6")){
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                //String data="{'lista':[";
                JsonArrayBuilder arrayBuilder=Json.createArrayBuilder();
                for(Listas listas:new ControladorListas().obtenerDepartamento()){
                    arrayBuilder.add(Json.createObjectBuilder()
                            .add("id", listas.getId())
                            .add("nombre",listas.getNombres())
                    );
                }
                JsonArray array=arrayBuilder.build();
                Json.createWriter(response.getWriter()).writeArray(array);
                //data=data.substring(0, data.length()-1)+"]}";
                //response.getWriter().print(data);
                
            }
            else if(request.getParameter("data").equals("7")){
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                JsonArrayBuilder arrayBuilder=Json.createArrayBuilder();
                for(Listas listas:new ControladorListas().obtenerBario(Integer.parseInt(request.getParameter("idDepa")))){
                    arrayBuilder.add(Json.createObjectBuilder()
                            .add("id",listas.getId())
                            .add("nombre", listas.getNombres())
                    );
                }    
                JsonArray array=arrayBuilder.build();
                Json.createWriter(response.getWriter()).writeArray(array);
            }
            else if(request.getParameter("data").equals("8")){
                ControladorCliente controCliente= new ControladorCliente();
                request.getSession().setAttribute("clientes", controCliente.obtenerClientesEliminados());
                response.sendRedirect("activarCliente.jsp");
            }else if(request.getParameter("data").equals("9")){
                ControladorCliente conClie=new ControladorCliente();
                if(conClie.activar(request.getParameter("dni"))>0){
                    request.getSession().setAttribute("msg", new Listas(1,"Eliminado con exito"));
                }else
                    request.getSession().setAttribute("msg", new Listas(2,"No se pudo eliminar"));
                response.sendRedirect("activarCliente.jsp");
            }
        } catch (Exception e) {
           request.getSession().setAttribute("msg", new Listas(2, "Problemas de conexion, numero de error C025"));
           String referer = request.getHeader("Referer");
           System.out.println("GG");
            Logger logger=Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE,"Problem ClassFormatError connecting. " + e.getMessage() + " " + e.getCause());
            int size = e.getStackTrace().length - 1;
            logger.log(Level.SEVERE,"   Root cause: " + e.getStackTrace()[size].getMethodName() + " " + e.getStackTrace()[size].getClassName());
            for(StackTraceElement ele:e.getStackTrace()){
                logger.log(Level.SEVERE,"Metodo con problemas ["
                        +ele.getMethodName()+"]\n Class con problema ["
                        +ele.getClassName()+"]\n Linea ["+ele.getLineNumber()+"]");
            }
            response.getWriter().print(e.getMessage());
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
            //    public Persona(int idBarrio, int idGenero, String dni,
            //String nombre, String apellido, String direccion, Date fechaNacimiento, String numero, String correo) throws Exception {
            if(request.getParameter("btnSubmit").equals("Agregar")){
                Cliente per=new Cliente(Integer.parseInt(request.getParameter("barrio")),Integer.parseInt(request.getParameter("genero")), request.getParameter("dni"), request.getParameter("nombre"), request.getParameter("apellido"), request.getParameter("direccion"), new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fecha")), request.getParameter("numero"), request.getParameter("correo"));
                ControladorCliente cli=new ControladorCliente();
                if(cli.agregarCliente(per,0)==1)
                    request.getSession().setAttribute("msg", new Listas(1,"Agregado con exito"));
                else
                    request.getSession().setAttribute("msg", new Listas(2,"Problemas al agregar"));
                response.sendRedirect("AdmonCliente.jsp");
            }else if(request.getParameter("btnSubmit").equals("Modificar")){
                Cliente per=new Cliente(Integer.parseInt(request.getParameter("barrio")),Integer.parseInt(request.getParameter("genero")), request.getParameter("dni"), request.getParameter("nombre"), request.getParameter("apellido"), request.getParameter("direccion"), new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fecha")), request.getParameter("numero"), request.getParameter("correo"));
                ControladorCliente cli=new ControladorCliente();
                if(cli.modificar(per)>0)
                    request.getSession().setAttribute("msg", new Listas(1,"Modificado con exito"));
                else
                    request.getSession().setAttribute("msg", new Listas(2,"Problemas al modificar"));
                response.sendRedirect("AdmonCliente.jsp");
            }
        }
        catch(SQLException e){
            request.getSession().setAttribute("msg", new Listas(2,"Problemas de conexion, numero de error C025"));
            response.sendRedirect("AdmonCliente.jsp");
        }
        catch (Exception e) {
            request.getSession().setAttribute("msg", new Listas(2,e.getMessage()));
            response.sendRedirect("AdmonCliente.jsp");
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
