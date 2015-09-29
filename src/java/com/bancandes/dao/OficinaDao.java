/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancandes.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan
 */
public class OficinaDao {

    
    public static int darIdOficina(int idGerente)
    {
        int idOficina=-1;
        String sentencia="SELECT ID FROM OFICINAS WHERE ID_GERENTE="+idGerente;
        try {
            Conexion con= new Conexion();
            ResultSet rs = con.getConexion().prepareStatement(sentencia).executeQuery();
            rs.next();
            idOficina=rs.getInt("ID");
            
        } catch (SQLException ex) {
            Logger.getLogger(OficinaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idOficina;
        
    }
    
}
