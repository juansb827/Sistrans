/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancandes.dao;

import com.bancandes.mb.Oficina;
import com.bancandes.mb.Prestamo;
import com.bancandes.mb.SolicitudPrestamo;
import com.bancandes.mb.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;

/**
 *
 * @author Juan
 */
public class SolicitudPrestamoDao {
    
    private final static String SOLICITUDES="SELECT * FROM SOLICITUDES_PRESTAMOS WHERE ID_OFICINA=";
    
    private static ArrayList<SolicitudPrestamo> getSolicitudes(String sentencia){
        ArrayList<SolicitudPrestamo> solicitudes=null;
        Conexion con;
        try {
            con = new Conexion();
            solicitudes=new  ArrayList<>();
            ResultSet rs=con.getConexion().prepareStatement(sentencia).executeQuery();
            while(rs.next())
            {
                SolicitudPrestamo solicitud=new SolicitudPrestamo();
                solicitud.setEstadoPeticion(rs.getString("ESTADO_PETICION"));
                solicitud.setFechaCreacion(new DateTime(rs.getDate("FECHA_CREACION")));
                solicitud.setId(rs.getInt("ID"));
                
                Oficina oficina= new Oficina() ;
                oficina.setId(rs.getInt("ID_OFICINA"));               
                
                solicitud.setOficina(oficina);
                
                Usuario solicitante=UsuarioDao.findUsuarioById(rs.getInt("ID_SOLICITANTE"),con );
                solicitud.setSolicitante( solicitante);
            
                Prestamo prestamo=PrestamoDao.findPrestamoById( rs.getInt("ID_PRESTAMO"),con );
                solicitud.setPrestamo(prestamo);
                
                solicitudes.add(solicitud);
                
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(SolicitudPrestamoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return solicitudes;
    }
    
    public static ArrayList<SolicitudPrestamo> findSolicitudesByOficina(int idOficina)
    {
        return getSolicitudes(SOLICITUDES+idOficina);
    }
    
    
    
} 
    
    
    
    
    
