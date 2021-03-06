/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancandes.dao;

import com.bancandes.mb.Operacion;
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
    public static Prestamo findPrestamoById(long idPrestamo,Conexion con )
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
                prestamo.setCuotasTotales(rs.getInt("CUOTAS_TOTALES"));
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
                prestamo.setCuotasTotales(rs.getInt("CUOTAS_TOTALES"));
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
      
   public static void registrarPagoCuota(Prestamo prestamo,Conexion con) throws DaoException
   {
       double nuevosaldo = prestamo.getCantidadRestante() - prestamo.getValorCuota();
       double cuotasRestantes = prestamo.getCuotasRestantes() - 1;
       DateTime siguientePago = prestamo.getFechaSiguientePago().plusMonths(1);
       String sentencia = "UPDATE PRESTAMOS SET CANTIDAD_RESTANTE=" + nuevosaldo + ",CUOTAS_RESTANTES=" + cuotasRestantes + ",FECHA_SIGUIENTE_PAGO=" + Consultas.toDate(siguientePago) + " WHERE ID=" + prestamo.getId();
        
            try {
                
               if(con==null)  con = new Conexion();
                
                PreparedStatement ps = con.getConexion().prepareStatement(sentencia);                
                int i = ps.executeUpdate();
                if(i==0)
                {
                    throw new DaoException();
                }
       
            } catch (SQLException ex) {
                Logger.getLogger(CuentaDao.class.getName()).log(Level.SEVERE, null, ex);
                throw new DaoException();
            }
   }
            
   
      public static void registrarPagoCuotaExtraordinaria(Prestamo prestamo,Operacion op,Conexion con) throws DaoException
   {
       double saldo=prestamo.getCantidadRestante();
       double sobrante;
       if(saldo<op.getMonto())
       {
           
           sobrante=op.getMonto()-saldo;
           
           
           
           
       }
       else{
           saldo=saldo-op.getMonto();
           sobrante=0;
       }
       
       String cadenaFecha=null;
       int cuotasRestantes =(int) (saldo/prestamo.getValorCuota());       
       int cuotasAdelantadas= (int) ((op.getMonto()-sobrante)/prestamo.getValorCuota());
       if(cuotasRestantes==0)
           cadenaFecha="''";
       else if (cuotasAdelantadas>=1)
       {
           DateTime siguientePago = prestamo.getFechaSiguientePago().plusMonths(cuotasAdelantadas);
           cadenaFecha=Consultas.toDate(siguientePago);
           
       }
       
       String sentencia = "UPDATE PRESTAMOS SET CANTIDAD_RESTANTE=" + saldo + ",CUOTAS_RESTANTES=" + cuotasRestantes + ",FECHA_SIGUIENTE_PAGO="+cadenaFecha + " WHERE ID=" + prestamo.getId();  
        
            try {
                
               if(con==null)  con = new Conexion();
                
                PreparedStatement ps = con.getConexion().prepareStatement(sentencia);                
                int i = ps.executeUpdate();
                if(i==0)
                {
                    throw new DaoException();
                }
       
            } catch (SQLException ex) {
                Logger.getLogger(CuentaDao.class.getName()).log(Level.SEVERE, null, ex);
                throw new DaoException();
            }
   }

    
}
