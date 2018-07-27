/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;
import org.apache.commons.codec.digest.DigestUtils;



/**
 *
 * @author Prueba
 */
public class Usuario extends Persona{
    private int idTipoUsuario;
    private String contrasena;
    
    public Usuario(int idTipoUsuario, String contrasena, int idBarrio, int idGenero, String dni, String nombre, String apellido, String direccion, Date fechaNacimiento, String numero, String correo) throws Exception {
        super(idBarrio, idGenero, dni, nombre, apellido, direccion, fechaNacimiento, numero, correo);
        setIdTipoUsuario(idTipoUsuario);
        setContrasena(contrasena);
    }
    public Usuario(int idTipoUsuari, int idBarrio, int idGenero, String dni, String nombre, String apellido, String direccion, Date fechaNacimiento, String numero, String correo) throws Exception {
        super(idBarrio, idGenero, dni, nombre, apellido, direccion, fechaNacimiento, numero, correo);
        setIdTipoUsuario(idTipoUsuari);
    }
    public Usuario(int idTipoUsuario, String contrasena, String dni) throws Exception {
        setIdTipoUsuario(idTipoUsuario);
        setContrasena(contrasena);
        setDni(dni);
    }
    
    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) throws Exception {
        if(!(idTipoUsuario==0)){
             this.idTipoUsuario = idTipoUsuario;
        }else{
            throw new Exception("Debe selcionar un rol");
        }
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) throws Exception {
        if(!contrasena.trim().equals("")){
            if(contrasena.length()>4){
                this.contrasena=DigestUtils.md5Hex(contrasena);
            }else{
                throw new Exception("El largo debe ser mayor a 4");
            }
        }else{
            throw new Exception("La contraseña no puede estar vacia");
        }
    }
    
    
}
