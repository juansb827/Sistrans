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
public class Operacion {
    
    
    
    public final static Metadata[] infoColumnas={
     new Metadata("ID",  Metadata.NUMERO) ,
     new Metadata("FECHA",  Metadata.FECHA) ,
     new Metadata("ORIGEN",  Metadata.NUMERO) ,
     new Metadata("DESTINO",  Metadata.NUMERO) ,
     new Metadata("TIPO",  Metadata.CADENA) ,
     new Metadata("MONTO",  Metadata.NUMERO) ,
     new Metadata("ID_AUTOR",  Metadata.NUMERO) ,
     new Metadata("METODO",  Metadata.CADENA) ,
 
     
        
        
    };
    
    //tipos de operacion para cuentas
    public final static String ABRIR="ABRIR";
    public final static String CERRAR="CERRAR";
    public final static String CONSIGNAR="CONSIGNAR";
    public final static String RETIRAR="RETIRAR";
    
    //tipos de operaciones para los prestamos
    public final static String SOLICITAR="SOLICITAR";
    public final static String APROBAR="APROBAR";
    public final static String RECHAZAR="RECHAZAR";
    public final static String PAGAR_CUOTA="PAGAR CUOTA";
    public final static String PAGAR_CUOTA_EXTRAORDINARIA="PAGAR CUOTA EXTRAORDINARIA";
    ;
    
            
    
    long id;
    DateTime fecha;
    long origen;
    long destino;
    String tipo;
     Double monto;
     Usuario autor;
     String metodo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DateTime getFecha() {
        return fecha;
    }

    public void setFecha(DateTime fecha) {
        this.fecha = fecha;
    }

    public long getOrigen() {
        return origen;
    }

    public void setOrigen(long origen) {
        this.origen = origen;
    }

    public long getDestino() {
        return destino;
    }

    public void setDestino(long destino) {
        this.destino = destino;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

 

    

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }
     
     
}

