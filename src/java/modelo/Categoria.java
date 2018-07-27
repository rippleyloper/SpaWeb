/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Tulio
 */
public class Categoria {
    private int idCate;
    private String nombre;
    private int id;

    public Categoria(String nombre, int id) {
        setNombre(nombre);
        setId(id);
    }

    public Categoria(int idCate, String nombre, int id) {
        setIdCate(idCate);
        setNombre(nombre);
        setId(id);
    }
    
    public int getIdCate() {
        return idCate;
    }

    public void setIdCate(int idCate) {
        this.idCate = idCate;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.replaceAll("\\<.*?\\>", "");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
