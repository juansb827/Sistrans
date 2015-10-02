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

    //Si es efectivo el origen es null;                
    //Destino cero significa que le paga al banco
    public static void registrarOperacionPrestamo(Usuario autor, long idPrestamo, Operacion operacion) throws DaoException {
        String msg = null;
        Prestamo prestamo = PrestamoDao.findPrestamoById(idPrestamo, null);
        Conexion con = null;
        try {
            HashMap<String, String> infoPrestamo;
            HashMap<String, Object> infoOperacion = new HashMap<String, Object>();
            infoOperacion.put("ID", Consultas.darNumFilas(Operacion.NOMBRE_TABLA)+1);
            infoOperacion.put("FECHA", Consultas.getCurrentDate());
            infoOperacion.put("TIPO", operacion.getTipo());
            infoOperacion.put("ID_AUTOR", autor.getId());

            
            con=new Conexion();
            switch (operacion.getTipo()) {
                case Operacion.PAGAR_CUOTA:
                    PrestamoDao.registrarPagoCuota(prestamo,con);
                    infoOperacion.put("MONTO", prestamo.getValorCuota());
                    infoOperacion.put("DESTINO", "0");
                    infoOperacion.put("METODO", operacion.getMetodo());
                    infoOperacion.put("ID_PRESTAMO", prestamo.getId());                  
                    
                    break;
                case Operacion.PAGAR_CUOTA_EXTRAORDINARIA:
                    
                    //solo gasta lo necesario par apagar el prestamo
                    if (operacion.getMonto()>prestamo.getCantidadRestante())
                    {
                        throw new DaoException("La cuota supera lo que debe");
                    }
                    
                    infoOperacion.put("MONTO", operacion.getMonto());
                    infoOperacion.put("DESTINO","0" );
                    infoOperacion.put("METODO", operacion.getMetodo());
                    infoOperacion.put("ID_PRESTAMO", prestamo.getId());                  
                    PrestamoDao.registrarPagoCuotaExtraordinaria(prestamo,operacion,con);
                    break;
                case Operacion.CERRAR:
                    if (prestamo.getCantidadRestante() != 0) {
                        throw new DaoException( "El prestamo no se ha pagado completamente");
                    } else {
                        
                        Consultas.insertar(null, infoOperacion, Operacion.infoColumnas, Operacion.NOMBRE_TABLA);
                        String sentenciaCerrar = "UPDATE PRESTAMOS SET ESTADO='CERRADO' WHERE ID=" + prestamo.getId();
                        con.getConexion().prepareStatement(sentenciaCerrar).executeUpdate();
                        
                    }
            
            }
            
            Consultas.insertar(con, infoOperacion, Operacion.infoColumnas, Operacion.NOMBRE_TABLA);
        } catch (SQLException ex) {
            Logger.getLogger(OperacionDao.class.getName()).log(Level.SEVERE, null, ex);           
            con.rollback();
            throw new DaoException();
        }

        
    }

    public static void registrarOperacionCuenta(Usuario autor, Operacion operacion) throws DaoException {

        HashMap<String, Object> infoOperacion = new HashMap<>();
        //atributos que tiene toda operacion 
        infoOperacion.put("ID", Consultas.darNumFilas(Operacion.NOMBRE_TABLA) + 1);
        infoOperacion.put("FECHA", Consultas.getCurrentDate());
        infoOperacion.put("TIPO", operacion.getTipo());
        infoOperacion.put("ID_AUTOR", autor.getId());
        infoOperacion.put("ID_CUENTA", operacion.getIdCuenta());

        switch (operacion.getTipo()) {
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
        if (operacion.getTipo().equals(Operacion.CONSIGNAR) || operacion.getTipo().equals(Operacion.RETIRAR)) {
            //atributos que solo tienen las operaciones bancarias
            infoOperacion.put("ORIGEN", "" + operacion.getOrigen());
            infoOperacion.put("DESTINO", "" + operacion.getDestino());
            infoOperacion.put("MONTO", "" + operacion.getMonto());
            infoOperacion.put("METODO", "" + operacion.getMetodo());
        }

        Consultas.insertar(null, infoOperacion, Operacion.infoColumnas, Operacion.NOMBRE_TABLA);

    }

}
