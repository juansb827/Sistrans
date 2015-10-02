/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancandes.dao;


import com.bancandes.mb.Metadata;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.joda.time.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Juan
 */
public class Consultas {


    
    public static void insertar(Conexion conn,HashMap<String, Object> datos, Metadata[] metadata, String tabla) throws DaoException 
    {
        
            try {
                if(conn==null) 
                conn=new Conexion();
                    String insert=crearSentenciaINSERT(datos, metadata, tabla);
            PreparedStatement ps=conn.getConexion().prepareStatement(insert);
            ps.execute();
            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
                conn.rollback();
                throw new DaoException();
            
            }
            
        
    }
    
    public static String crearSentenciaINSERT(HashMap<String, Object> datos, Metadata[] metadata, String tabla) {
        String inicio = "INSERT INTO " + tabla + " ";
        String columnas = "(";
        String valores = " VALUES (";
        boolean vacio=true;
        for (int i = 0; i < metadata.length; i++) {
            Metadata meta = metadata[i];
            String nombreCol=meta.getNombreColumna();
            Object dato = datos.get(nombreCol);
            
            int tipoColumna=meta.getTipoCampo();
            
            
            if ( dato != null ) {
                
                   if (!vacio) {
                    columnas += ",";
                    valores += ",";
                }
                vacio=false;
                columnas += meta.getNombreColumna();
                //Si es un numero no le pone comillas en el insert
                switch(tipoColumna)
                {
                    case Metadata.NUMERO:
                    valores += String.valueOf(dato);
                    break;
                    case Metadata.CADENA:
                    valores += "'" + dato + "'";
                    break;
                    case Metadata.FECHA:
                    valores += toDate((DateTime) dato) ;
                    break;    
                }
                //todatetime
             
            }
    

        }
        columnas+=") ";
        valores+=")";
        String sentencia = inicio + columnas + valores;
        return sentencia;

    }
    
    public static int darNumFilas(String tabla) throws DaoException
    {
       int numFilas=-1; 
        try {
            Conexion con=new Conexion();
            String sentencia="SELECT COUNT(*) FROM "+tabla;
            PreparedStatement ps=con.getConexion().prepareStatement(sentencia);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                numFilas=rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            throw new  DaoException();
        }
        return numFilas;
    }
    
    public static DateTime getCurrentDate()
    {
     DateTime date=null;   
        try {
            Conexion conn = new Conexion();
            String sentencia="SELECT TO_CHAR (SYSDATE, 'YYYY-MM-DD HH24:MI:SS') \"NOW\" FROM DUAL";
            PreparedStatement ps=conn.getConexion().prepareStatement(sentencia);
            ResultSet rs=ps.executeQuery();
            rs.next();
            String now=rs.getString(1);
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd H:mm:ss");
            date=formatter.parseDateTime(now);
            
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return date;        
    }
        
    public static String formatDate(DateTime dt)
    {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy/mm/dd HH:mm:ss");
        return dtf.print(dt);
    }
    
    public static String toDate(DateTime dt )
    {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss");
        String fecha=dtf.print(dt);                
        return "TO_DATE('"+fecha+"', 'yyyy/mm/dd hh24:mi:ss' )";
    }
    
    
public static ArrayList<FilaEnConsulta> hacerConsulta(String consulta, int limiteResultado) throws DaoException
    {
        ArrayList<FilaEnConsulta> tabla=new ArrayList<FilaEnConsulta>();
        Statement statement=null;
        ResultSet rs=null;
        try {
            Conexion con=new Conexion();
            Statement st=con.getConexion().createStatement();            
             rs=st.executeQuery(consulta);
            ResultSetMetaData rsmd=rs.getMetaData();
            int numColumnas=rsmd.getColumnCount();
            FilaEnConsulta cabecera=new FilaEnConsulta(numColumnas);

            for(int i=0;i<numColumnas;i++)
            {
                cabecera.setDato(i,rsmd.getColumnName(i+1) ) ;


            }
            tabla.add(cabecera);


            int filasAgregadas=0;
            while(rs.next() && filasAgregadas<limiteResultado)
            {
                FilaEnConsulta fila=new FilaEnConsulta(numColumnas);
                for(int i=0;i<numColumnas;i++)
                {
                    fila.setDato(i, rs.getString(i+1));
                }
                tabla.add(fila);
                filasAgregadas++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return tabla;

    }  
            
    
}

