/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancandes.dao;

import com.bancandes.mb.Cuenta;
import com.bancandes.mb.Operacion;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;

/**
 *
 * @author Juan
 */
public class OperacionDao {
    
    public static String registrarRetiro(Operacion op)
    {
        
        Cuenta cuenta=CuentaDao.findCuentaById(op.getOrigen());
        double nuevoSaldo=cuenta.getSaldo()-op.getMonto();
        if( nuevoSaldo<0) 
        return  "Saldo insuficiente, faltan "+nuevoSaldo;
        else{
            
            HashMap<String,Object> info=new HashMap<>();
            int id=Consultas.darNumFilas("OPERACIONES");
            if (id==-1) return "Error";
            info.put("ID", ""+id+1 );
            DateTime fecha=Consultas.getCurrentDate();
            info.put("FECHA", fecha);
            info.put("ORIGEN", ""+op.getOrigen());
            info.put("DESTINO", ""+op.getDestino());
            info.put("TIPO", "RETIRO");
            info.put("MONTO", ""+op.getMonto());
            info.put("ID_AUTOR", ""+op.getAutor().getId());
            info.put("METODO", op.getMetodo());
            String msg="";
            Conexion conn;
            try {
                conn = new Conexion();
                if(Consultas.insertar(conn,info, Operacion.infoColumnas, "OPERACIONES")!=null)
                 return "Error"    ;
                
                String update="UPDATE CUENTAS SET SALDO="+nuevoSaldo+" WHERE ID="+op.getOrigen();
                int rs = conn.getConexion().prepareStatement(update).executeUpdate();                
                msg="Se ha registrado el retiro";
            
            } catch (SQLException ex) {
                Logger.getLogger(OperacionDao.class.getName()).log(Level.SEVERE, null, ex);
                msg="NO Se ha registrado el retiro";
            }
            
            return msg;
        
        }       
        
        
    }
        
    
    public static String registrarConsignacion(Operacion op)
    {
        
        Cuenta cuenta=CuentaDao.findCuentaById(op.getOrigen());
        
            double nuevoSaldo=cuenta.getSaldo()+op.getMonto();
            HashMap<String,Object> info=new HashMap<>();
            int id=Consultas.darNumFilas("OPERACIONES");
            if (id==-1) return "Error";
            info.put("ID", ""+id+1 );
            DateTime fecha=Consultas.getCurrentDate();
            info.put("FECHA", fecha);
            info.put("ORIGEN", ""+op.getOrigen());
            info.put("DESTINO", ""+op.getDestino());
            info.put("TIPO", "CONSIGNACION");
            info.put("MONTO", ""+op.getMonto());
            info.put("ID_AUTOR", ""+op.getAutor().getId());
            info.put("METODO", op.getMetodo());
            String msg="";
            Conexion conn;
            try {
                conn = new Conexion();
                
                if(Consultas.insertar(conn,info, Operacion.infoColumnas, "OPERACIONES")!=null)
                    return "Error ";
                 
                
                String update="UPDATE CUENTAS SET SALDO="+nuevoSaldo+" WHERE ID="+op.getOrigen();
                int rs = conn.getConexion().prepareStatement(update).executeUpdate();                
                msg="Se ha registrado la consignacion";
            
            } catch (SQLException ex) {
                Logger.getLogger(OperacionDao.class.getName()).log(Level.SEVERE, null, ex);
                msg="NO Se ha registrado la consignacion";
            }
            
            return msg;
           
        
        
    }
}
