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
public class DetalleTrabajador {
    private int idServicio;
    private int idTrabajador;

    public DetalleTrabajador(int idServicio, int idTrabajador) {
        setIdServicio(idServicio);
        setIdTrabajador(idTrabajador);
    }
    
    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }
    
}
