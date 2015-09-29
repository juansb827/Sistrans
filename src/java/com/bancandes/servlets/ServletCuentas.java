/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancandes.servlets;

import com.bancandes.dao.CuentaDao;
import com.bancandes.dao.OperacionDao;
import com.bancandes.mb.Cuenta;
import com.bancandes.mb.Operacion;
import com.bancandes.mb.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Juan
 */
public class ServletCuentas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            Usuario usuario=(Usuario) request.getSession().getAttribute("usuarioLogeado");
            String pagina=request.getParameter("paginaCuenta");
            //paginaParaElResultado
            int paginaDeseada=1;
            if(pagina!=null)
            {
                
                paginaDeseada=Integer.parseInt(pagina);
            }
            
            
                    
            
                realizarOperacion(request, response,usuario);
                confirmarOperacion(request, response, usuario);
                mostrarListaCuentas(request, response, usuario);
               
            
            
            
        }
    }

    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    private void confirmarOperacion(HttpServletRequest request, HttpServletResponse response,Usuario usuario)
    {   
        
        String confirmarOperacion=request.getParameter("confirmarOperacion");
        if(confirmarOperacion==null) return;
        String id=request.getParameter("idCuenta" );
        int idCuenta=Integer.parseInt(id);
        int monto=Integer.parseInt( request.getParameter("monto" ));
        String metodo=request.getParameter("metodo");
        
        Operacion op=new Operacion();
        
                op.setAutor(usuario);
                op.setMonto(Double.parseDouble(request.getParameter("monto")));
                op.setMetodo(request.getParameter("metodo"));                
                op.setOrigen(idCuenta);
                String msgOperacion=null;
        switch(confirmarOperacion)
        {
            case  Operacion.RETIRAR:
                
                msgOperacion=OperacionDao.registrarRetiro(op);
                break;
            case    
                Operacion.CONSIGNAR:                                  
                msgOperacion=OperacionDao.registrarConsignacion(op);
                break;
        }
        request.setAttribute("msgOperacion", msgOperacion );
    }
            
    
    private void realizarOperacion(HttpServletRequest request, HttpServletResponse response,Usuario usuario) throws ServletException, IOException
    {
        String operacion=  request.getParameter("tipoAccion");
        if(operacion==null) return;
        String id=request.getParameter("idCuenta");
        int idCuenta=Integer.parseInt( id);
        switch(operacion)
        {
            
            
            case Operacion.CERRAR:                
                request.setAttribute("msgOperacion", CuentaDao.cerrarCuenta(idCuenta) );
                request.setAttribute("idCuenta",idCuenta);
                break;
            case Operacion.ABRIR:
                request.setAttribute("msgOperacion", CuentaDao.abrirCuenta(idCuenta) );
                request.setAttribute("idCuenta",idCuenta);
                break;
            case Operacion.RETIRAR:
                request.setAttribute("tipoOperacion",Operacion.RETIRAR);
                request.setAttribute("idCuenta", id);
                request.getRequestDispatcher("menus/cuentas.jsp").forward(request, response);
                break;
            case Operacion.CONSIGNAR:    
                request.setAttribute("tipoOperacion",Operacion.CONSIGNAR);
                request.setAttribute("idCuenta", id);
                request.getRequestDispatcher("menus/cuentas.jsp").forward(request, response);
                break;
    }
                
        }
        
    
    
    private void mostrarListaCuentas(HttpServletRequest request, HttpServletResponse response,Usuario usuario) throws ServletException, IOException
    {
        
        ArrayList<Cuenta> listaCuentas=null;
            switch(usuario.getRol())
            {
                case Usuario.GERENTE_OFICINA:
                    listaCuentas=CuentaDao.getCuentasbyCreador(usuario.getId());
                    break;
                case Usuario.CLIENTE:    
                    listaCuentas=CuentaDao.getCuentasByPropietario(usuario.getId());
                    break;
                case Usuario.GERENTE_GENERAL:
                case Usuario.ADMINISTRADOR:
                    listaCuentas=CuentaDao.getAllCuentas(20, 0);
                    case Usuario.CAJERO:
                    listaCuentas=CuentaDao.getAllCuentas(20, 0);
                    break;
            }
            
       
            
            
            request.setAttribute("listaCuentas", listaCuentas);
            request.getRequestDispatcher("menus/cuentas.jsp").forward(request, response);
    }
    private ArrayList<Cuenta> TestListaCuentas()
    {
        ArrayList<Cuenta> listaCuentas=new ArrayList<>();
        Cuenta cuenta=new Cuenta();
        cuenta.setFechaCreacion(null);
        cuenta.setTipo("LOL");
        listaCuentas.add(cuenta);
        return listaCuentas;
                
        
    }

}
