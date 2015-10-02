/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancandes.dao;

import com.bancandes.mb.Cuenta;
import com.bancandes.mb.Operacion;
import com.sun.javafx.geom.CubicApproximator;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
    
    public static void cerrarCuenta(long idCuenta) throws DaoException
    {
        String horror="UPDATE CUENTAS SET ESTADO='CERRADA',SALDO=0 WHERE ID="+idCuenta;
         
        Conexion con;
            try {
                
                con = new Conexion();
                
                PreparedStatement ps = con.getConexion().prepareStatement(horror);                
                int i = ps.executeUpdate();
         
            } catch (SQLException ex) {
                Logger.getLogger(CuentaDao.class.getName()).log(Level.SEVERE, null, ex);
                 throw new DaoException();
            }
            
       
                
        
    }
    
    
       public static void abrirCuenta(long idCuenta) throws DaoException
    {
        String horror="UPDATE CUENTAS SET ESTADO='ACTIVA',SALDO=0 WHERE ID="+idCuenta;
         
        Conexion con;
            try {
                
                con = new Conexion();
                
                PreparedStatement ps = con.getConexion().prepareStatement(horror);                
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
       
       
       public static Cuenta getCuenta(String sentencia) throws DaoException {
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
            throw new DaoException();
        }
        return cuenta;

    }
       
       
       
       public static Cuenta findCuentaById(long idCuenta) throws DaoException
       {
           return getCuenta("SELECT * FROM CUENTAS WHERE ID="+idCuenta);
       }
       
       
        public static void registrarRetiro(Operacion op) throws DaoException
    {
        
        Cuenta cuenta=CuentaDao.findCuentaById(op.getIdCuenta());
        
        
        double nuevoSaldo=cuenta.getSaldo()-op.getMonto();
        if( nuevoSaldo<0) 
        throw new DaoException( "Saldo insuficiente, faltan "+nuevoSaldo);
        
        Conexion conn;
        try {
            conn = new Conexion();
            String update="UPDATE CUENTAS SET SALDO="+nuevoSaldo+" WHERE ID="+op.getIdCuenta();
                int rs = conn.getConexion().prepareStatement(update).executeUpdate();                
        } catch (SQLException ex) {
            Logger.getLogger(CuentaDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException();
        }
                
        
        
        
        
        
    }
        
        public static void registrarConsignacion(Operacion op) throws DaoException
    {
        
        Cuenta cuenta=CuentaDao.findCuentaById(op.getIdCuenta());
        
            double nuevoSaldo=cuenta.getSaldo()+op.getMonto();
            
            Conexion conn;
            try {
                conn = new Conexion();
                
                
                String update="UPDATE CUENTAS SET SALDO="+nuevoSaldo+" WHERE ID="+op.getIdCuenta();
                int rs = conn.getConexion().prepareStatement(update).executeUpdate();                
                
            
            } catch (SQLException ex) {
                Logger.getLogger(OperacionDao.class.getName()).log(Level.SEVERE, null, ex);
                throw new DaoException();
            }
        
    }
        
        public static ArrayList<FilaEnConsulta> busquedaInfoCuentas(HashMap<String,Object> filtros,ArrayList<String> agrupar) throws DaoException
        {
            
            
            String sentencia="SELECT ID_PROPIETARIO,ID_CREADOR,CORREO_PROPIETARIO,TIPO,ESTADO,SALDO,FECHA_ULTIMA_OPERACION FROM \n" +
"((CUENTAS INNER JOIN (SELECT CORREO AS CORREO_PROPIETARIO,ID AS ID_US  FROM USUARIOS t) ON ID_PROPIETARIO=ID_US)\n" +
"LEFT JOIN \n" +
"(SELECT  MAX (FECHA) AS FECHA_ULTIMA_OPERACION,ID_CUENTA AS IDC2 FROM OPERACIONES GROUP BY ID_CUENTA)\n" +
"ON CUENTAS.ID=IDC2) ";
            return Consultas.hacerConsulta(sentencia, 100);
                    
                    
        }
                
                
    
}
