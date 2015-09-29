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
public class Prestamo {
    private int id;
    private int idPropietario;
    private double interes;
    private int cuotasRestantes;

    private DateTime fechaVencimiento;
    private DateTime fechaSiguientePago;
    private DateTime fechaAprobacion;
    private String estado;
    private Double cantidadTotal;
    private Double cantidadRestante;
    private String tipoPrestamo;
    private Double valorCuota;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getIdUsuario() {
        return idPropietario;
    }

    public void setIdPropietario(int idUsuario) {
        this.idPropietario = idUsuario;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public int getCuotasRestantes() {
        return cuotasRestantes;
    }

    public void setCuotasRestantes(int cuotasRestantes) {
        this.cuotasRestantes = cuotasRestantes;
    }

    public DateTime getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(DateTime fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public DateTime getFechaSiguientePago() {
        return fechaSiguientePago;
    }

    public void setFechaSiguientePago(DateTime fechaSiguientePago) {
        this.fechaSiguientePago = fechaSiguientePago;
    }

    public Double getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(Double cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public Double getCantidadRestante() {
        return cantidadRestante;
    }

    public void setCantidadRestante(Double cantidadRestante) {
        this.cantidadRestante = cantidadRestante;
    }

    public String getTipoPrestamo() {
        return tipoPrestamo;
    }

    public void setTipoPrestamo(String tipoPrestamo) {
        this.tipoPrestamo = tipoPrestamo;
    }

    public Double getValorCuota() {
        return valorCuota;
    }

    public void setValorCuota(Double valorCuota) {
        this.valorCuota = valorCuota;
    }

    public DateTime getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(DateTime fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    
    
    
    
}

