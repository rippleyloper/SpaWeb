/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controlador.ControladorCliente;
import controlador.ControladorDetalleServicio;
import controlador.ControladorListas;
import controlador.ControladorServicio;
import controlador.ControladorTrabajador;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.DetalleServicio;
import modelo.Listas;
import modelo.Persona;
import modelo.Servicio;
import modelo.Trabajador;

/**
 *
 * @author Tulio
 */
public class ServletReservas extends HttpServlet {

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
            out.println("<title>Servlet ServletReservas</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletReservas at " + request.getContextPath() + "</h1>");
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
        try {
            /*
            Data:1 obtener reservas
            Data:2 obtener tipo servicio y especiliadad
            Data:3 obtener Categoria
            Data:4 obtenerTrabajadores
            data:5 obtener datos de servicio
            data:6 obtener Servicio de categoria;
            data:7 obtener Persona dni
            */
            if(request.getParameter("data").equals("1")){
                JsonArrayBuilder array=Json.createArrayBuilder();
                for(DetalleServicio ds:new ControladorDetalleServicio().obtenerServicios()){
                    JsonArrayBuilder arrayTra=Json.createArrayBuilder();
                    for(Trabajador tra:new ControladorTrabajador().buscarDetalle(ds.getIdDetalle()))
                    {
                        arrayTra.add(Json.createObjectBuilder().
                                        add("dni", tra.getDni())
                                        .add("nombre", tra.getNombre())
                                        .add("apellido",tra.getApellido())
                                        .add("especialidad", tra.getNombreEs())
                        );
                    }
                    array.add(Json.createObjectBuilder()
                            .add("curso", Json.createObjectBuilder().
                                    add("id", ds.getIdDetalle())
                                    .add("DNI", ds.getPer().getDni())
                                    .add("nombre",ds.getPer().getNombre())
                                    .add("apellido",ds.getPer().getApellido())
                                    .add("tipo",ds.getTipo())
                                    .add("servicio",ds.getServicio())
                                    .add("categoria", ds.getCategoria())
                                    .add("inicio", ds.getInicio())
                                    .add("termino",ds.getTermino())
                                    .add("precio",ds.getPrecio())
                                    .add("descuento",ds.getDescuento())
                                    .add("pagado",ds.getPagado())
                                    .add("asis",ds.getIdAsistencia())
                                    .add("detalle",ds.getDetalle())
                                    .add("Trabajadores",arrayTra)
                            )
                    );
                    
                }
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                Json.createWriter(response.getWriter()).writeArray(array.build());
            }
            else if(request.getParameter("data").equals("2")){
                JsonArrayBuilder array=Json.createArrayBuilder();
                for(Listas lista:new ControladorListas().obtenerTipoServicio()){
                    array.add(Json.createObjectBuilder().add("servicio", 
                                Json.createObjectBuilder().add("id", lista.getId())
                                .add("nombre",lista.getNombres())
                            ));
                }
                for(Listas lista:new ControladorListas().obtenerEspecialidad()){
                    array.add(Json.createObjectBuilder().add("especialidad", 
                                Json.createObjectBuilder().add("id", lista.getId())
                                .add("nombre",lista.getNombres())
                            ));
                }
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                Json.createWriter(response.getWriter()).writeArray(array.build());
            }
            else if(request.getParameter("data").equals("3")){
                JsonArrayBuilder array=Json.createArrayBuilder();
                for(Listas lista:new ControladorListas().obtenerCategoriaServicio(Integer.parseInt(request.getParameter("id")))){
                    array.add(Json.createObjectBuilder().add("producto", 
                                Json.createObjectBuilder().add("id", lista.getId())
                                .add("nombre",lista.getNombres())
                            ));
                }
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                Json.createWriter(response.getWriter()).writeArray(array.build());
            }
            else if(request.getParameter("data").equals("4")){
                JsonArrayBuilder array=Json.createArrayBuilder();
                for(Trabajador tra:new ControladorTrabajador().obtener(Integer.parseInt(request.getParameter("id")))){
                    array.add(Json.createObjectBuilder().add("dni", tra.getDni())
                            .add("nombre", tra.getNombre())
                            .add("apellido",tra.getApellido())
                    );
                }
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                Json.createWriter(response.getWriter()).writeArray(array.build());
            }
            else if(request.getParameter("data").equals("5")){
                JsonObjectBuilder obj=Json.createObjectBuilder();
                Servicio ser=new ControladorServicio().buscarServicio(Integer.parseInt(request.getParameter("id")));
                ser.calcularTermino(new SimpleDateFormat("yyyy/MM/dd hh:mm").parse(request.getParameter("fecha")));
                obj.add("precio",ser.getPrecio())
                        .add("inicio", ser.getInicio())
                        .add("termino", ser.getTermino())
                        .add("duracion", ser.getDuracion());
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                Json.createWriter(response.getWriter()).writeObject(obj.build());
            }
            else if(request.getParameter("data").equals("6")){
                JsonArrayBuilder array=Json.createArrayBuilder();
                for(Listas ls:new ControladorListas().obtenerServicio(Integer.parseInt(request.getParameter("id"))))
                       array.add(Json.createObjectBuilder().add("id",ls.getId()).add("nombre",ls.getNombres()));
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                Json.createWriter(response.getWriter()).writeArray(array.build());
            }
            else if(request.getParameter("data").equals("7")){
                JsonObjectBuilder obj=Json.createObjectBuilder();
                if(new ControladorCliente().buscar(request.getParameter("dni"))>0){
                    Persona per=new ControladorCliente().obtenerCliente(request.getParameter("dni"));
                    obj.add("persona",Json.createObjectBuilder()
                            .add("dni", per.getDni())
                            .add("nombre",per.getNombre())
                            .add("apellido",per.getApellido())
                    );
                }else
                    obj.addNull("persona");
                response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    Json.createWriter(response.getWriter()).writeObject(obj.build());
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
        /*
        Dato:1 Registro de evento
        */
        JsonObjectBuilder obj=Json.createObjectBuilder();
        try {
            request.setCharacterEncoding("UTF-8");
            if(request.getParameter("btnAgendar").toLowerCase().equals("agendar")){
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                //(int idDetalle, int idServicio, int idPersona, String idAsistencia, Time hora,Date fecha,String detalle,int precio) {
                int nu=new ControladorCliente().obtenerId(request.getParameter("dni"));
                if(nu>0){
                    Servicio ser=new ControladorServicio().buscarServicio(Integer.parseInt(request.getParameter("servicio")));
                  //  String fecha=request.getParameter("inicio");
                    String fecha=request.getParameter("fecha");
                    String detalle=request.getParameter("detalle");
                    java.util.Date da=new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(fecha);
                    Date dia=new Date(new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(da)).getTime());
                    DetalleServicio ds=new DetalleServicio(0, ser.getIdServicio(), nu, "3", new Time(da.getTime()),dia, detalle, ser.getPrecio(),Integer.parseInt(request.getParameter("descuento")),Integer.parseInt(request.getParameter("pagado")));
                    ControladorDetalleServicio cds=new ControladorDetalleServicio();
                    System.out.println(cds.insertarDetalle(ds));
                    cds.agregarDetalleTrabajador(new ControladorTrabajador().buscar(request.getParameter("trabajador")));
                    
                    obj.add("msj", "Ingresado con exito");
                }else{
                    obj.add("msj", "Dni no esta registrado como cliente");
                }
            }
            else if(request.getParameter("btnAgendar").toLowerCase().equals("asistir")){
                if(new ControladorDetalleServicio().marcarComo(Integer.parseInt(request.getParameter("id")), 1))
                   if(new ControladorDetalleServicio().pagar(Integer.parseInt(request.getParameter("id")),Integer.parseInt(request.getParameter("total"))))
                   obj.add("msj", "Marcado como asistido"); 
            }
            else if(request.getParameter("btnAgendar").toLowerCase().equals("cancelar")){
                if(new ControladorDetalleServicio().marcarComo(Integer.parseInt(request.getParameter("id")), 4))
                   obj.add("msj", "Marcado como cancelado"); 
            }
        } catch (Exception e) {
            obj.add("msj", e.getMessage()+e.getCause().getMessage());
            response.getWriter().print(e.getMessage());
        }
        Json.createWriter(response.getWriter()).writeObject(obj.build());
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
