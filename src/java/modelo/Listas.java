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
public class Listas {
    private int id;
    private String nombres;

    public Listas(int id, String nombres) {
        setId(id);
        setNombres(nombres);
    }

    public Listas(String nombres) {
        this.nombres = nombres.replaceAll("\\<.*?\\>", "");
    }

    public Listas(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres.replaceAll("\\<.*?\\>", "");
    }
    @Override
    public String toString(){
        return "{'id':'"+getId()+
                "','nombre':'"+getNombres()+
                "'},";
    }
}
