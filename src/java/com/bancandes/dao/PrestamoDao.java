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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;

/**
 *
 * @author Juan
 */
public class PrestamoDao {
    
    private static String PRESTAMO_POR_ID="SELECT * FROM PRESTAMOS WHERE ID=";
    private static String PRESTAMOS_DE_USUARIO="SELECT * FROM PRESTAMOS WHERE ID_PROPIETARIO=";
    private static String PRESTAMOS_DE_OFICINA="SELECT * FROM PRESTAMOS WHERE ID_PROPIETARIO=";
    
    
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
    
    
    private static ArrayList<Prestamo> getPrestamos(String sentencia){
        ArrayList<Prestamo> prestamos=null;
        Conexion con;
        try {
            con = new Conexion();
                prestamos=new ArrayList<>();
            ResultSet rs = con.getConexion().prepareStatement(sentencia).executeQuery();
        while(rs.next())
            {
                Prestamo prestamo=new  Prestamo();
                prestamo.setId(rs.getInt("ID"));                
                prestamo.setCantidadTotal(rs.getDouble("CANTIDAD_TOTAL"));
                prestamo.setCuotasRestantes(rs.getInt("CUOTAS_RESTANTES"));
                prestamo.setTipoPrestamo(rs.getString("TIPO_PRESTAMO"));
                prestamo.setIdPropietario(rs.getInt("ID_PROPIETARIO") );
                prestamo.setInteres(rs.getDouble("INTERES"));
                prestamo.setCuotasRestantes(rs.getInt("CUOTAS_RESTANTES"));
                prestamo.setFechaVencimiento(new DateTime(rs.getDate("FECHA_VENCIMIENTO")));
                prestamo.setCantidadTotal(rs.getDouble("CANTIDAD_TOTAL"));
                prestamo.setCantidadRestante(rs.getDouble("CANTIDAD_RESTANTE"));
                prestamo.setTipoPrestamo(rs.getString("TIPO_PRESTAMO"));
                prestamo.setFechaSiguientePago(new DateTime(rs.getDate("FECHA_SIGUIENTE_PAGO")));
                prestamo.setValorCuota(rs.getDouble("VALOR_CUOTA"));
                prestamo.setFechaAprobacion(new DateTime(rs.getDate("FECHA_APROBACION")));
                prestamo.setEstado(rs.getString("ESTADO"));
                prestamos.add(prestamo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrestamoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  prestamos;
        
    }
    
    public static ArrayList<Prestamo> findPrestamosByPropietario(int idPropietario,Conexion con)
    {   
        
            String sentencia=PRESTAMOS_DE_USUARIO+idPropietario;                  
            return getPrestamos(sentencia);
    } 
    
      public static ArrayList<Prestamo> findPrestamosByOficina(int idOficina,Conexion con)
    {   
        
            String sentencia=PRESTAMOS_DE_USUARIO+"";                  
            return getPrestamos(sentencia);
    } 
            
    
}
