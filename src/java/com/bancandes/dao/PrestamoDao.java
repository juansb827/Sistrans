/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancandes.dao;

import com.bancandes.mb.Prestamo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan
 */
public class PrestamoDao {
    
    private static String PRESTAMO_POR_ID="SELECT * FROM PRESTAMOS WHERE ID=";
    
    
    //RF7
    public static Prestamo findPrestamoById(int idPrestamo,Conexion con )
    {
        Prestamo prestamo=null;
        String sentencia=PRESTAMO_POR_ID+idPrestamo;
        try {
           if(con==null)  con=new Conexion();
            prestamo=new Prestamo();
            ResultSet rs = con.getConexion().prepareStatement(sentencia).executeQuery();
            if(rs.next())
            {
                prestamo.setId(rs.getInt("ID"));                
                prestamo.setCantidadTotal(rs.getDouble("CANTIDAD_TOTAL"));
                prestamo.setCuotasRestantes(rs.getInt("CUOTAS_RESTANTES"));
                prestamo.setTipoPrestamo(rs.getString("TIPO_PRESTAMO"));
                
                
                        
                
                        
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(PrestamoDao.class.getName()).log(Level.SEVERE, null, ex);
        }       
        
        return prestamo;
    }
    
}
