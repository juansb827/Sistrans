/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancandes.dao;

import com.bancandes.mb.Cuenta;
import com.bancandes.mb.Operacion;
import com.bancandes.mb.Prestamo;
import com.bancandes.mb.Usuario;
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
    
   
        
    
    
    public static String  registrarOperacionPrestamo(Usuario autor,Prestamo prestamo,Operacion operacion) throws DaoException
    {
                    String msg=null;
        try {
            HashMap<String,String> infoPrestamo;
            HashMap<String,Object> infoOperacion=new HashMap<String, Object>();
            infoOperacion.put("ID", Consultas.darNumFilas(Operacion.NOMBRE_TABLA));
            infoOperacion.put("FECHA", Consultas.getCurrentDate());
            
            infoOperacion.put("TIPO", operacion.getTipo());
            infoOperacion.put("ID_ AUTOR", autor.getId());
            
            
            

            Conexion con=null;
            switch(operacion.getTipo())
            {
                case  Operacion.PAGAR_CUOTA:
                case Operacion.PAGAR_CUOTA_EXTRAORDINARIA:
                    infoOperacion.put("MONTO", prestamo.getValorCuota());
                    //Si es efectivo el origen es 0;
                infoOperacion.put("ORIGEN","0");
            //Destino cero significa que le paga al banco
                infoOperacion.put("DESTINO", "0");           
                
                infoOperacion.put("METODO", operacion.getMetodo());
            infoOperacion.put("ID_PRESTAMO", prestamo.getId());
            con=new Conexion();
            Consultas.insertar(con,infoOperacion, Operacion.infoColumnas, Operacion.NOMBRE_TABLA);
            double nuevosaldo=prestamo.getCantidadRestante()-prestamo.getValorCuota();
            double cuotasRestantes=prestamo.getCuotasRestantes()-1;
            DateTime siguientePago=prestamo.getFechaSiguientePago().plusMonths(1);
            String sentencia="UPDATE PRESTAMOS SET CANTIDAD_RESTANTE="+nuevosaldo+",CUOTAS_RESTANTES="+cuotasRestantes+",FECHA_SIGUIENTE_PAGO='"+Consultas.toDate(siguientePago)+"', WHERE ID="+prestamo.getId();
            
            int res=con.getConexion().prepareStatement(sentencia).executeUpdate();
                msg= "se registro la operacion correctamente";
               break;
             case Operacion.CERRAR:                 
                 if(prestamo.getCantidadRestante()!=0) msg ="El prestamo no se ha pagado completamente";
                 else{
                   con=new Conexion();
                   Consultas.insertar(null,infoOperacion, Operacion.infoColumnas, Operacion.NOMBRE_TABLA);
                   String sentenciaCerrar="UPDATE PRESTAMOS SET ESTADO='CERRADO' WHERE ID="+prestamo.getId();            
                  int res1=con.getConexion().prepareStatement(sentenciaCerrar).executeUpdate();
                   msg= "se cerro el prestamo";
                 }
                    
                    
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(OperacionDao.class.getName()).log(Level.SEVERE, null, ex);
            return "Error";
        }
        
                
                
        
        return msg;
    }
        
    
   public static void registrarOperacionCuenta(Usuario autor,Operacion operacion) throws DaoException 
   {
       
       HashMap<String,Object> infoOperacion=new HashMap<>();
       //atributos que tiene toda operacion 
       infoOperacion.put("ID", Consultas.darNumFilas(Operacion.NOMBRE_TABLA)+1);
            infoOperacion.put("FECHA", Consultas.getCurrentDate());            
            infoOperacion.put("TIPO", operacion.getTipo());
            infoOperacion.put("ID_AUTOR", autor.getId());
            infoOperacion.put("ID_CUENTA", operacion.getIdCuenta());
       
       switch(operacion.getTipo())
       {
           case Operacion.CERRAR:
               
               CuentaDao.cerrarCuenta(operacion.getIdCuenta());
               break;
           case Operacion.ABRIR:
               
               CuentaDao.abrirCuenta(operacion.getIdCuenta());
               break;
           case Operacion.RETIRAR:
               CuentaDao.registrarRetiro(operacion);  
               break;
           case Operacion.CONSIGNAR:    
               CuentaDao.registrarConsignacion(operacion);
               break;
                   
       }
            if(operacion.getTipo().equals(Operacion.CONSIGNAR) || operacion.getTipo().equals(Operacion.RETIRAR)  )   
            {
                //atributos que solo tienen las operaciones bancarias
            infoOperacion.put("ORIGEN", ""+operacion.getOrigen());
            infoOperacion.put("DESTINO", ""+operacion.getDestino());
            infoOperacion.put("MONTO", ""+operacion.getMonto());
            infoOperacion.put("METODO", ""+operacion.getMetodo());
            }
            
            
       Consultas.insertar(null,infoOperacion, Operacion.infoColumnas, Operacion.NOMBRE_TABLA);
               
       
   }
    
    
}
