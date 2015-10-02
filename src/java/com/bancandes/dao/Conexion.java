/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancandes.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Juan
 */
public class Conexion {
    
              private final static String URL = "jdbc:oracle:thin:@157.253.238.7:1521:prod";

	private final static String USER = "ISIS2304031520";

	private final static String PASSWORD = "p8vQJgypqTB5";

	private final static String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
        
        
        //---------------------------------------------------------------
	//Atributos
	//---------------------------------------------------------------

	/**
	 * 
	 */
	private  Connection conexion;

	/**
	 * 
	 */
	public String usuario;

	/**
	 * 
	 */
	public String pass;

	/**
	 * 
	 */
	public String URLConexion;

	/**
	 * 
	 */
    
   
        
        public Conexion() throws SQLException 
        {           
             try {
           Class.forName(DRIVER_NAME);
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e ) {
            System.out.println("Error");
        }
          
        }
        
        public void rollback()
        {
                  try {
                      if (conexion!=null);
                      conexion.rollback();
                  } catch (SQLException ex) {
                      Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                  }
        }
        
  
    public  Connection getConexion() {
        return conexion;
    }
        
        

        public static void main(String[] args)
        {
            
       
                
          /* // PreparedStatement ps=conexion.prepareStatement("SELECT COUNT(*) FROM ALL_TABLES WHERE OWNER='PARRANDEROS'");
            //ResultSet rs=ps.executeQuery();
            rs.next();
            System.out.println("NumTablas="+rs.getString(1));
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                }
             */   
        }
                
        
    
}