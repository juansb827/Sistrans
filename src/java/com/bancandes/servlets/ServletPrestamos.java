/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancandes.servlets;

import com.bancandes.dao.CuentaDao;
import com.bancandes.dao.OficinaDao;
import com.bancandes.dao.PrestamoDao;
import com.bancandes.dao.SolicitudPrestamoDao;
import com.bancandes.mb.Prestamo;
import com.bancandes.mb.SolicitudPrestamo;
import com.bancandes.mb.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.joda.time.DateTime;

/**
 *
 * @author Juan
 */
public class ServletPrestamos extends HttpServlet {

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
            
         String menu=request.getParameter("menu");
            if( menu.equals("solicitudes"))
         { 
            verSolicitudesPrestamos(request, response, usuario);
         }else  if(menu.equals("info"))
         {
             verPrestamos(request, response, usuario);
         }
            
   
        }
    }

     private void verSolicitudesPrestamos(HttpServletRequest request, HttpServletResponse response,Usuario usuario) throws ServletException, IOException
     {
         
         
             
         ArrayList<SolicitudPrestamo> solicitudesPrestamos=null;
                 switch(usuario.getRol())
            {
                case Usuario.GERENTE_OFICINA:
                     int id_oficina=OficinaDao.darIdOficina(usuario.getId());
                     solicitudesPrestamos=SolicitudPrestamoDao.findSolicitudesByOficina(id_oficina);
                    break;
                case Usuario.CLIENTE:    
                    solicitudesPrestamos=null;// solicitudes hechas
                    break;
          
            }
                DateTime dateTime=null;
                
            if(request.getParameter("aprobarPrestamo")!=null)
            {
                System.err.println("probar prestamo");  
            }
            request.setAttribute("solicitudesPrestamos",solicitudesPrestamos);
            request.getRequestDispatcher("menus/solicitudesPrestamos.jsp").forward(request, response);
         
    
     }

     
     private void verPrestamos(HttpServletRequest request, HttpServletResponse response,Usuario usuario) throws ServletException, IOException
     {
         ArrayList<Prestamo> prestamos=null;
         switch(usuario.getRol())
         {
             case Usuario.CLIENTE:
             prestamos=PrestamoDao.findPrestamosByPropietario(usuario.getId(), null);
             break;                 
             
                 
                 
         }
         request.setAttribute("listaPrestamos",prestamos);
         request.setAttribute("listaOperaciones", usuario.getOperacionesPrestamo());
         request.getRequestDispatcher("menus/operacionesPrestamos.jsp").forward(request, response);
         
         
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

}
