/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Time;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Prueba
 */
public class DetalleServicio {
    private int idDetalle;
    private int idServicio;
    private int idPersona;
    private String servicio;
    private String categoria;
    private String tipo;
    private String idAsistencia;
    private Time hora;
    private String hora2;
    private Date fecha;
    private String detalle;
    private int precio;
    private String termino;
    private String inicio;
    private Persona per;
    private int idTipo;
    private double descuento;
    private double pagado;
    public DetalleServicio(int idDetalle, int idServicio, int tipo, String idAsistencia, String hora, Date fecha, String detalle, int precio, Persona per,int duracion,int descuento,int pagado) {
        setIdDetalle(idDetalle);
        setIdServicio(idServicio);
        setIdTipo(tipo);
        setIdAsistencia(idAsistencia);
        setHora2(hora);
        setFecha(fecha);
        setDetalle(detalle);
        setPrecio(precio);
        setPer(per);
        calcularTermino(duracion);
        setDescuento(descuento);
        setPagado(pagado);
    }
    public DetalleServicio(int idDetalle, String idServicio, String tipo,String categoria, String idAsistencia, String hora, Date fecha, String detalle, int precio, Persona per,int duracion,int descuento,int pagado) {
        setIdDetalle(idDetalle);
        setServicio(idServicio);
        setCategoria(categoria);
        setTipo(tipo);
        setIdAsistencia(idAsistencia);
        setHora2(hora);
        setFecha(fecha);
        setDetalle(detalle);
        setPrecio(precio);
        setPer(per);
        calcularTermino(duracion);
        setDescuento(descuento);
        setPagado(pagado);
    }
    public String getServicio() {
        return servicio;
    }

    public double getPagado() {
        return pagado;
    }

    public void setPagado(int pagado) {
        this.pagado = pagado;
    }
    
    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria.replaceAll("\\<.*?\\>", "");;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo.replaceAll("\\<.*?\\>", "");;
    }
    public int getPrecio() {
        return precio;
    }

    public Persona getPer() {
        return per;
    }

    public void setPer(Persona per) {
        this.per = per;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public DetalleServicio(int idDetalle, int idServicio, int idPersona, String idAsistencia, Time hora,Date fecha,String detalle,int precio,int descuento,int pagado) {
        setIdDetalle(idDetalle);
        setIdServicio(idServicio);
        setIdPersona(idPersona);
        setIdAsistencia(idAsistencia);
        setHora(hora);
        setFecha(fecha);
        setDetalle(detalle);
        setPrecio(precio);
        setDescuento(descuento);
        setPagado(pagado);
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(String idAsistencia) {
        this.idAsistencia = idAsistencia.replaceAll("\\<.*?\\>", "");;
    }

    public String getDetalle() {
        return detalle;
    }

    public String getHora2() {
        return hora2;
    }

    public void setHora2(String hora2) {
        this.hora2 = hora2.replaceAll("\\<.*?\\>", "");;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getTermino() {
        return termino;
    }

    public void setTermino(String termino) {
        this.termino = termino.replaceAll("\\<.*?\\>", "");;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }
    
    
    public void setDetalle(String detalle) {
        this.detalle = detalle.replaceAll("\\<.*?\\>", "");;
    }

    private void calcularTermino(int duracion) {
        try {
            Calendar ca=Calendar.getInstance();
            ca.setTime(getFecha());
            String a[]=getHora2().split(":");
            ca.set(Calendar.HOUR, Integer.parseInt(a[0]));
            ca.set(Calendar.MINUTE, Integer.parseInt(a[1]));
            ca.set(Calendar.SECOND, Integer.parseInt(a[2]));
            setInicio(new SimpleDateFormat("yyyy-MM-dd").format(ca.getTime())+"T"+new SimpleDateFormat("hh:mm:ss").format(ca.getTime()));
            ca.add(Calendar.MINUTE, duracion);
            setTermino(new SimpleDateFormat("yyyy-MM-dd").format(ca.getTime())+"T"+new SimpleDateFormat("hh:mm:ss").format(ca.getTime()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
}
