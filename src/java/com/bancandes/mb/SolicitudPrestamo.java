/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancandes.mb;

import org.joda.time.DateTime;


/**
 *
 * @author Juan
 */

public class SolicitudPrestamo {
    
    
 
    
    private int id;    
    private DateTime fechaCreacion;
    private String estadoPeticion;
    
    private Usuario solicitante;
    private Prestamo prestamo;
    private Oficina oficina;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(DateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstadoPeticion() {
        return estadoPeticion;
    }

    public void setEstadoPeticion(String estadoPeticion) {
        this.estadoPeticion = estadoPeticion;
    }

    public Usuario getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Usuario solicitante) {
        this.solicitante = solicitante;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }
    
    public int getIdSolicitante()
    {
        return solicitante.getId();
    }
    
    public int getIdPrestamo()
    {
        return prestamo.getId();
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }
    
    
    
}
