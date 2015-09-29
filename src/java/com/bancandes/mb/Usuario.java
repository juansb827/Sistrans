/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancandes.mb;

import com.bancandes.dao.Consultas;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;

/**
 *
 * @author Juan
 */

public class Usuario {
    
    //TODO-
    public final static Metadata[] infoColumnas={
     new Metadata("ID",  Metadata.NUMERO) ,
     new Metadata("NOMBRE",  Metadata.CADENA) ,
     new Metadata("CORREO",  Metadata.CADENA) ,
     new Metadata("TELEFONO",  Metadata.NUMERO) ,
     new Metadata("CIUDAD",  Metadata.CADENA) ,
     new Metadata("PASSWORD",  Metadata.CADENA) ,
     new Metadata("ROL",  Metadata.CADENA) ,
     new Metadata("TIPO_DOC",  Metadata.CADENA) ,
     new Metadata("NUM_DOC",  Metadata.NUMERO) ,
     new Metadata("PAIS",  Metadata.CADENA) ,
     new Metadata("DEPARTAMENTO",  Metadata.CADENA) ,
     new Metadata("DIRECCION",  Metadata.CADENA) ,
     new Metadata("CODIGO_POSTAL",  Metadata.NUMERO) ,
     
        
        
    };
    
    
    public final static String USUARIOS="USUARIOS";
    
    
    //Tipos de rol
    public final static String ADMINISTRADOR="ADMINISTRADOR";
    public final static String CLIENTE="CLIENTE";
    public final static String GERENTE_GENERAL="GERENTE GENERAL";
    public final static String GERENTE_OFICINA="GERENTE OFICINA";
    public final static String CAJERO="CAJERO";
    
    //Operaciones
    
    
    String nombre;
    String pass;
    String rol;
    String correo;
    int id;
            

    /**
     * Creates a new instance of Usuario
     */
    public Usuario() {
    }  

    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public ArrayList<String> getQuePuedeRegistrar()
    {
         ArrayList<String> puedeRegistrar=new ArrayList<>();
       
         switch(rol)
         {
             case ADMINISTRADOR:
                 puedeRegistrar.add(GERENTE_GENERAL);
                 puedeRegistrar.add(GERENTE_OFICINA);
                 puedeRegistrar.add(CAJERO);    
                 break;
             case GERENTE_OFICINA:
                 puedeRegistrar.add(CLIENTE);
                 break;
             case CLIENTE:
                 //puedeRegistrar.add(" A SU MADRE :V");
                 break;                         
                 
         } 
         return puedeRegistrar;
    }
    
    public ArrayList<String> getOperacionesCuenta()
    {
         ArrayList<String> operacionesCuenta=new ArrayList<>();
       
         switch(rol)
         {
             
                case GERENTE_OFICINA:
                    operacionesCuenta.add(Operacion.ABRIR);
                    operacionesCuenta.add(Operacion.CERRAR);
             case CLIENTE:
                 
                 break;  
             case  CAJERO:
                 operacionesCuenta.add(Operacion.CONSIGNAR);
                 operacionesCuenta.add(Operacion.RETIRAR);
                 
                 break;
              
                 
         } 
         return operacionesCuenta;
    }
    
     public ArrayList<String> getOperacionesPresmo()
    {
         ArrayList<String> operacionesPrestamo=new ArrayList<>();
       
         switch(rol)
         {
             
             case GERENTE_OFICINA:
                 operacionesPrestamo.add(Operacion.APROBAR);
                 operacionesPrestamo.add(Operacion.RECHAZAR);
                 operacionesPrestamo.add(Operacion.CERRAR);
                         ;
             case CLIENTE:    
                 operacionesPrestamo.add(Operacion.SOLICITAR);
             case  CAJERO:
                 operacionesPrestamo.add(Operacion.PAGAR_CUOTA);
                 operacionesPrestamo.add(Operacion.PAGAR_CUOTA_EXTRAORDINARIA);                
            break;
              
                 
         } 
         return operacionesPrestamo;
    }
    
    
    //Todo pasar a usuarioDao
    public static String crear(HashMap<String,Object> datos)
    {
        
        
        //Todo- crear algo que valdie los datos
        String bien="El usuario: "+datos.get("NOMBRE")+" fue creado exitosamente";
        String error="Error,no se creo el usuario";
        
        
        int numFilas=Consultas.darNumFilas(USUARIOS);        
        if (numFilas==-1) return error;
        
        datos.put("ID", ""+numFilas+1);
        if(Consultas.insertar(null,datos,infoColumnas, USUARIOS)==null) return bien;
        else return error;
        
    }
    
    public  boolean validar(String password)
    {
     if (1==1) return true;
        return password.equals(pass);
    }
    
        
    
}
