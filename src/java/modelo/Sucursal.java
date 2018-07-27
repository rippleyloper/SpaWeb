/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Prueba
 */
public class Sucursal {
    private int idBarrio;
    private String nombre;
    private String numero;
    private String direccio;

    public Sucursal(int idBarrio, String nombre, String numero, String direccio) {
        setIdBarrio(idBarrio);
        setNombre(nombre);
        setNumero(numero);
        setDireccio(direccio);
    }

    public int getIdBarrio() {
        return idBarrio;
    }

    public void setIdBarrio(int idBarrio) {
        this.idBarrio = idBarrio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.replaceAll("\\<.*?\\>", "");
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDireccio() {
        return direccio;
    }

    public void setDireccio(String direccio) {
        this.direccio = direccio.replaceAll("\\<.*?\\>", "");;
    }
    @Override
    public String toString(){
        return "Sucursal :"+getNombre();
    }
}
