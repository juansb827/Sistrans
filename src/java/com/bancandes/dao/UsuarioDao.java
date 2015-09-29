/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancandes.dao;

import com.bancandes.mb.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan
 */
public class UsuarioDao {
    
    private final static String USUARIO_POR_CORREO="SELECT * FROM USUARIOS WHERE CORREO=";
    private final static String USUARIO_POR_ID="SELECT * FROM USUARIOS WHERE ID=";
    
    
    public static Usuario getUsuario(String sentencia,Conexion con)
    {
      Usuario usuario=null;
      
        try {
            if (con==null) con=new Conexion();
            usuario=new Usuario();
            PreparedStatement ps=con.getConexion().prepareStatement(sentencia);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                usuario.setId(rs.getInt("ID"));
                usuario.setNombre(rs.getString("NOMBRE"));
                usuario.setCorreo(rs.getString("CORREO"));
                usuario.setRol(rs.getString("ROL"));
                usuario.setPass(rs.getString("PASSWORD"));
            }
                
                
          
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
      return usuario;
    }
    
    
        public static Usuario findUsuarioByCorreo(String correo,Conexion con)  {      
        return getUsuario(USUARIO_POR_CORREO+"'"+correo+"'",con); }
        
        public static Usuario findUsuarioById(long id,Conexion con)  {      
        return getUsuario(USUARIO_POR_ID+id,con); }
        
        
        
}
