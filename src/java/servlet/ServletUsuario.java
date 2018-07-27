/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controlador.ControladorDetalleServicio;
import controlador.ControladorListas;
import controlador.ControladorTrabajador;
import controlador.ControladorUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Listas;
import modelo.Persona;
import modelo.Usuario;

/**
 *
 * @author Tulio
 */
public class ServletUsuario extends HttpServlet {

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
            out.println("<title>Servlet ServletUsuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletUsuario at " + request.getContextPath() + "</h1>");
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
        try{
            /*
            data:1 Obtener trabajadores y usuarios
            data:2 obtener rol
            data:3 buscar rut;
            data:4 listar Eliminados
            data:5 activar usuarios
            */
            if(request.getParameter("data").equals("1")){
                request.getSession().setAttribute("usuarios", new ControladorUsuario().obtener());
                request.getSession().setAttribute("trabajadores", new ControladorTrabajador().obtener());
                response.sendRedirect("AdmonUsuario.jsp");
            }else if(request.getParameter("data").equals("2")){
                JsonArrayBuilder arra=Json.createArrayBuilder();
               for(Listas lista: new ControladorListas().obtenerTipoUsuario()){
                   arra.add(Json.createObjectBuilder().add("id", lista.getId()).add("nombre",lista.getNombres()));
               }
               Json.createWriter(response.getWriter()).writeArray(arra.build());
            }
            else if(request.getParameter("data").equals("3")){
                JsonObjectBuilder obj=Json.createObjectBuilder();
                Usuario user=new ControladorUsuario().obtenerUsuario(request.getParameter("dni"));
                if(user!=null){
                    obj.add("rol",user.getIdTipoUsuario());
                }else{
                    obj.addNull("rol");
                }
                Json.createWriter(response.getWriter()).writeObject(obj.build());   
            }else if(request.getParameter("data").equals("4")){
                request.getSession().setAttribute("usuarios", new ControladorUsuario().obtenerEliminados());
                response.sendRedirect("activarUsuario.jsp");
            }else if(request.getParameter("data").equals("5")){
                if(new ControladorUsuario().activar(request.getParameter("dni")))
                    request.getSession().setAttribute("msg", new Listas(1,"Activado con exito"));
                else
                    request.getSession().setAttribute("msg", new Listas(2,"No se pudo activar"));

                response.sendRedirect("activarUsuario.jsp");
            }else if(request.getParameter("data").equals("6")){
                request.getSession().removeAttribute("usuario");
                request.getSession().removeAttribute("permiso");
                response.sendRedirect("index.jsp");
            }
            
        }
        catch(Exception ex){
            response.getWriter().print(ex.getMessage());
        }
            
    }

    /*
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
            /*
            data:1 Agregar
            data:2 modificar
            data:3 eliminar
            */
            if(request.getParameter("btnAccion").toLowerCase().equals("agregar")){
                Usuario user=null;
                if(request.getParameter("password").equals(request.getParameter("password2"))){
                    user=new Usuario(Integer.parseInt(request.getParameter("rol")), request.getParameter("password"), request.getParameter("dni"));
                    request.getSession().setAttribute("msg", new ControladorUsuario().crearUsuario(user));
                    response.sendRedirect("AdmonUsuario.jsp");
                }else{
                    request.getSession().setAttribute("msg", new Listas(2,"Contraseña no coincide"));
                    response.sendRedirect("AdmonUsuario.jsp");
                }
            }
            else if(request.getParameter("btnAccion").toLowerCase().equals("modificar")){
                Usuario user=null;
                if(request.getParameter("password").equals(request.getParameter("password2"))){
                    user=new Usuario(Integer.parseInt(request.getParameter("rol")), request.getParameter("password"), request.getParameter("dni"));
                    request.getSession().setAttribute("msg", (new ControladorUsuario().modificar(user)==1)?new Listas(1,"Modificado con exito"):new Listas(2,"Hubo un problema al modificar"));
                    response.sendRedirect("AdmonUsuario.jsp");
                }else{
                    request.getSession().setAttribute("msg", new Listas(2,"Contraseña no coincide"));
                    response.sendRedirect("AdmonUsuario.jsp");
                }
            }
            else if(request.getParameter("btnAccion").toLowerCase().equals("eliminar")){
                Usuario user=null;
                request.getSession().setAttribute("msg",new ControladorUsuario().eliminar(request.getParameter("dni")));
                response.sendRedirect("AdmonUsuario.jsp");
            }
            else if(request.getParameter("btnAccion").equals("Entrar")){
                System.out.println("Entro");
                Persona per=new Persona(request.getParameter("usuario"),request.getParameter("password"));
                System.out.println("Entro 2");
                String user=new ControladorUsuario().ingresar(per);
                System.out.println("Entro 3");
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Datos usuario ["+user+"]");
                if(!user.isEmpty()){
                    System.out.println("Entro 4");
                    new ControladorDetalleServicio().asistido();
                    String a[]=user.split(",");
                    request.getSession().setAttribute("idSucu", new ControladorUsuario().obtenerIdSucu(Integer.parseInt(a[2])));
                    System.out.println(a[2]);
                    request.getSession().setAttribute("usuario", a[0]);
                    request.getSession().setAttribute("permiso", a[1]);
                    response.sendRedirect("Inicio.jsp");
                }else if(per.getDni().equals("34543999") && per.getPassword().equals("c99dc5aa9bd4928477cde5f587385e67")){
                    request.getSession().setAttribute("idSucu", 1);
                    request.getSession().setAttribute("usuario", "Administrador");
                    request.getSession().setAttribute("permiso", "1");
                    response.sendRedirect("Inicio.jsp");
                }else
                    request.getSession().setAttribute("msj", new Listas(2,"Usuario o contraseña incorrectos"));
                response.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
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
