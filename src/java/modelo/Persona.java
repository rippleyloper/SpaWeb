/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.codec.digest.DigestUtils;



/**
 *
 * @author Prueba
 */
public class Persona {
    private int idBarrio;
    private int idGenero;
    private String dni;
    private String nombre;
    private String apellido;
    private String direccion;
    private String fechaNacimiento;
    private String numero;
    private String correo;
    private String password;
    
    public Persona(int idBarrio, int idGenero, String dni, String nombre, String apellido, String direccion, Date fechaNacimiento, String numero, String correo) throws Exception {
        setIdBarrio(idBarrio);
        setIdGenero(idGenero);
        setDni(dni);
        setNombre(nombre);
        setApellido(apellido);
        setDireccion(direccion);
        setFechaNacimiento(fechaNacimiento);
        setNumero(numero);
        setCorreo(correo);
    }
    
    public Persona(){}

    public Persona(String dni, String nombre, String apellido) throws Exception {
        setDni(dni);
        setNombre(nombre);
        setApellido(apellido);
    }
    
    public Persona(String dni,String password) throws Exception{
        this.setDni(dni);
        this.setPassword(password);
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password)throws Exception {
        if(!password.trim().equals("")){
            if(password.length()>4){
                this.password=DigestUtils.md5Hex(password);
            }else{
                throw new Exception("El largo de la contraseña debe ser mayor a 5");
            }
        }else{
            throw new Exception("El password no puede estar vacio");
        }
    }

    
    
    public int getIdBarrio() {
        return idBarrio;
    }

    public void setIdBarrio(int idBarrio) throws Exception {
        if(idBarrio>0){
            this.idBarrio = idBarrio;
        }else{
            throw  new Exception("Debe selecionar el barrio");
        }
        
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) throws Exception {
       if(idGenero>0){
            this.idGenero=idGenero;
        }else{
            throw  new Exception("Debe selecionar el genero");
        }
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) throws Exception {
       if(!dni.trim().equals("")){
           if(dni.length()>4){
               this.dni=dni.replaceAll("\\<.*?\\>", "");;
           }
       }else{
           throw new Exception("El campo dni no puede estar vacio");
       }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws Exception {
        if(!nombre.trim().equals("")){
           if(nombre.length()>2){
               this.nombre = nombre.toUpperCase().charAt(0)+nombre.substring(1, nombre.length()).replaceAll("\\<.*?\\>", "");;
           }
       }else{
           throw new Exception("El campo nombre no puede estar vacio");
       }
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) throws Exception {
        if(!apellido.trim().equals("")){
           if(apellido.length()>2){
               this.apellido = apellido.toUpperCase().charAt(0)+apellido.substring(1, apellido.length()).replaceAll("\\<.*?\\>", "");;
           }
       }else{
           throw new Exception("El campo apellido no puede estar vacio");
       }
        
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) throws Exception {
        this.direccion = direccion;
        if(!direccion.trim().equals("")){
           if(direccion.length()>10){
               this.direccion = direccion.replaceAll("\\<.*?\\>", "");;
           }
       }else{
           throw new Exception("El campo direccion no puede estar vacio");
       }
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) throws Exception{
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date hoy=new Date();
        if(!fechaNacimiento.after(hoy)){
            this.fechaNacimiento=formato.format(fechaNacimiento);
        }else{
            throw new Exception("Fecha nacimiento no puede ser mayor a hoy");
        }
        
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) throws Exception {
        this.numero = numero;
        if(!numero.trim().equals("")){
            if(numero.length()>4){
                this.numero=numero.replaceAll("\\<.*?\\>", "");
            }else{
                throw new Exception("El numero debe ser entre 4 y 10 caracteres");
            }
        }else{
            throw new Exception("Campo numero no puede estar vacio");
        }
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) throws Exception {
        if(!correo.trim().equals("")){
            if(correo.length()>10){
                this.correo = correo.replaceAll("\\<.*?\\>", "");
            }else{
                throw new Exception("El largo debe ser entre 11 y 45 carracteres");
            }
        }else{
            throw new Exception("El campo correo no puede estar vacio");
        }
    }
    @Override
    public String toString(){
        //int idBarrio, int idGenero, String dni, String nombre, String apellido,
        //String direccion, Date fechaNacimiento, String numero, String correo
        return "{'idBarrio':'"+getIdBarrio()+
                "','idGenero':'"+getIdGenero()+
                "','dni':'"+getDni()+
                "','nombre':'"+getNombre()+
                "','apellido':'"+getApellido()+
                "','direccion':'"+getDireccion()+
                "','fechaNacimiento':'"+getFechaNacimiento()+
                "','numero':'"+getNumero()+
                "','correo':'"+getCorreo()+
                "'},";
    }
    
}
