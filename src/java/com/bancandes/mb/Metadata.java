/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancandes.mb;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Juan
 */
public class Metadata {

    public final static int NUMERO = 0;
    public final static int CADENA = 1;
    public final static int FECHA = 2;

    String nombreColumna;
    int tipoCampo;

    public Metadata(String nombreColumna, int tipoCampo) {
        this.nombreColumna = nombreColumna;
        this.tipoCampo = tipoCampo;
    }

    public String getNombreColumna() {
        return nombreColumna;
    }

    public int getTipoCampo() {
        return tipoCampo;
    }

    public static HashMap<String, Object> convertRequestToHashMap(Metadata[] metadata, HttpServletRequest request) {
        HashMap<String, Object> respuesta = new HashMap<>();
        for (int i = 0; i < metadata.length; i++) {
            Metadata infoCampo = metadata[i];
            String nombreCampo = infoCampo.getNombreColumna();
            //trae la info del que tenga name='nombreCampo' 
            String delFormulario = request.getParameter(nombreCampo);
            respuesta.put(nombreCampo, delFormulario);
            
        }
        return respuesta;
    }

}

