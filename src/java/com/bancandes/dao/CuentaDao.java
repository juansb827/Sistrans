/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancandes.dao;

import com.bancandes.mb.Cuenta;
import com.sun.javafx.geom.CubicApproximator;
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
public class CuentaDao {

    private static String CONSULTA_INFORMACION_CUENTAS="SELECT * FROM \n" +
"((CUENTAS C INNER JOIN (SELECT NOMBRE AS NOMBRE_PROPIETARIO,CORREO AS CORREO_PROPIETARIO,ID AS ID_USUARIO FROM USUARIOS ) ON C.ID_PROPIETARIO= ID_USUARIO)\n" +
"INNER JOIN\n" +
"(SELECT NOMBRE AS NOMBRE_CREADOR,CORREO AS CORREO_CREADOR,ID AS ID_CREADOR2 FROM USUARIOS)\n" +
"ON ID_CREADOR=ID_CREADOR2) ";
    
    public static ArrayList<Cuenta> getCuentas(String sentencia) {
        ArrayList<Cuenta> lista=null;
        
        Conexion con;
        try {
            con = new Conexion();
            PreparedStatement ps = con.getConexion().prepareStatement(sentencia);
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                //Todo por no usar jpa :V
                Cuenta cuenta=new Cuenta();
                cuenta.setId(rs.getInt("ID"));
                cuenta.setIdPropietario(rs.getInt("ID_PROPIETARIO"));
                cuenta.setIdCreador(rs.getInt("ID_CREADOR"));                
                cuenta.setFechaCreacion(new DateTime(rs.getDate("FECHA_CREACION")));
                cuenta.setTipo(rs.getString("TIPO"));
                cuenta.setSaldo(rs.getDouble("SALDO"));                               
                cuenta.setCorreoPropietario(rs.getString("CORREO_PROPIETARIO"));
                cuenta.setNombrePropietario(rs.getString("NOMBRE_PROPIETARIO"));
                cuenta.setEstado(rs.getString("ESTADO"));
                lista.add(cuenta);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CuentaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;

    }
    
    public static ArrayList<Cuenta> getCuentasbyCreador(int idCreador)
    {
        String sentencia = CONSULTA_INFORMACION_CUENTAS+" WHERE ID_CREADOR="+idCreador;
        return getCuentas(sentencia);
    }
    
    public static ArrayList<Cuenta> getCuentasByPropietario(int idPropietario)
    {
        String sentencia = CONSULTA_INFORMACION_CUENTAS+ "WHERE ID_PROPIETARIO="+ idPropietario;
        return getCuentas(sentencia);
    }
    
    public static ArrayList<Cuenta> getAllCuentas(int resultsPerPage,int page)
    {
        
        return getCuentas(CONSULTA_INFORMACION_CUENTAS);
    }
    
    public static String cerrarCuenta(int idCuenta)
    {
        String horror="UPDATE CUENTAS SET ESTADO='CERRADA',SALDO=0 WHERE ID="+idCuenta;
         String mensaje="";
        Conexion con;
            try {
                
                con = new Conexion();
                
                PreparedStatement ps = con.getConexion().prepareStatement(horror);                
                int i = ps.executeUpdate();
                if(i>0)
                {
                    mensaje="Se cerro la cuenta";
                }
       
            } catch (SQLException ex) {
                Logger.getLogger(CuentaDao.class.getName()).log(Level.SEVERE, null, ex);
                mensaje="Error, no se pudo completar la operacion";
            }
            
       return mensaje;           
                
        
    }
    
    
       public static String abrirCuenta(int idCuenta)
    {
        String horror="UPDATE CUENTAS SET ESTADO='ABIERTA',SALDO=0 WHERE ID="+idCuenta;
         String mensaje="";
        Conexion con;
            try {
                
                con = new Conexion();
                
                PreparedStatement ps = con.getConexion().prepareStatement(horror);                
                int i = ps.executeUpdate();
                if(i>0)
                {
                    mensaje="Se abrio la cuenta";
                }
       
            } catch (SQLException ex) {
                Logger.getLogger(CuentaDao.class.getName()).log(Level.SEVERE, null, ex);
                mensaje="Error, no se pudo completar la operacion";
            }
            
       return mensaje;           
                
        
    }
       
       
       public static Cuenta getCuenta(String sentencia) {
        Cuenta cuenta=null;
        
        Conexion con;
        try {
            con = new Conexion();
            PreparedStatement ps = con.getConexion().prepareStatement(sentencia);
            ResultSet rs = ps.executeQuery();
            cuenta=new Cuenta();
            if (rs.next()) {
                //Todo por no usar jpa :V
                
                cuenta.setId(rs.getInt("ID"));                
                cuenta.setSaldo(rs.getDouble("SALDO"));                                              
                cuenta.setEstado(rs.getString("ESTADO"));
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CuentaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cuenta;

    }
       
       
       
       public static Cuenta findCuentaById(long idCuenta)
       {
           return getCuenta("SELECT * FROM CUENTAS WHERE ID="+idCuenta);
       }

}