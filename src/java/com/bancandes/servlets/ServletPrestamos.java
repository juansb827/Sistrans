/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancandes.servlets;

import com.bancandes.dao.CuentaDao;
import com.bancandes.dao.DaoException;
import com.bancandes.dao.OficinaDao;
import com.bancandes.dao.OperacionDao;
import com.bancandes.dao.PrestamoDao;
import com.bancandes.dao.SolicitudPrestamoDao;
import com.bancandes.dao.UsuarioDao;
import com.bancandes.mb.Operacion;
import com.bancandes.mb.Prestamo;
import com.bancandes.mb.SolicitudPrestamo;
import com.bancandes.mb.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
         if(menu!=null)
         {
            if( menu.equals("solicitudes"))
         { 
            verSolicitudesPrestamos(request, response, usuario);
         }else  if(menu.equals("info"))
         {
             verPrestamos(request, response, usuario);
         }
         }
            
         String buscarPrestamos=request.getParameter("buscarPrestamos");
         if(buscarPrestamos!=null)
         {
               
               verPrestamos(request, response, usuario);
         }
         String operacionPrestamo=request.getParameter("operacionPrestamo");
         if(operacionPrestamo!=null)
         {
             registrarOperacion(request, response, usuario);
         }
         String confirmarOperacion=request.getParameter("confirmarOperacion");
         if(confirmarOperacion!=null){
             confirmarOperacion(request, response, usuario);
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
             case Usuario.GERENTE_OFICINA:   
             case Usuario.CAJERO:
                 if(request.getParameter("buscarPrestamos")!=null)
                {
                   String correo=request.getParameter("correoPropietario");
                    
                    Usuario usuarioPrestamos=UsuarioDao.findUsuarioByCorreo(correo, null);                           
                    prestamos = PrestamoDao.findPrestamosByPropietario(usuarioPrestamos.getId(),null);
                    if(prestamos.size()==0) 
                    {
                        request.setAttribute("msgOperacion", "No se encontraron prestamos para "+correo);
                    }
                    //guarda el correo para mostrar las cuentas luego.
                    
                    request.setAttribute("correoPropietario", correo);
                    
                }
                break;
                            
             
                 
                 
         }
         request.setAttribute("listaPrestamos",prestamos);
         request.setAttribute("listaOperaciones", usuario.getOperacionesPrestamo());
         request.getRequestDispatcher("menus/operacionesPrestamos.jsp").forward(request, response);        
         
     }
     
     private void confirmarOperacion(HttpServletRequest request, HttpServletResponse response,Usuario usuario) throws ServletException, IOException
     {
         String msg="Se registro la operacion";
         String tipoOperacion=request.getParameter("tipoOperacion");
         int idPrestamo=Integer.parseInt(request.getParameter("idPrestamo"));
         
         Operacion operacion=new Operacion();
         operacion.setMetodo("EFECTIVO");         
         operacion.setTipo(tipoOperacion);       
         operacion.setIdPrestamo(idPrestamo);
         double monto= Double.parseDouble(request.getParameter("monto"));
         operacion.setMonto( monto);
         
         
        try {
            OperacionDao.registrarOperacionPrestamo(usuario,idPrestamo,operacion );
        } catch (DaoException ex) {
            Logger.getLogger(ServletPrestamos.class.getName()).log(Level.SEVERE, null, ex);
            msg="Error "+ex.getMessage();
        }
         
        request.setAttribute("msgOperacion", msg);
        request.getRequestDispatcher("menus/operacionesPrestamos.jsp").forward(request, response);
     }
     
      private void registrarOperacion(HttpServletRequest request, HttpServletResponse response,Usuario usuario) throws ServletException, IOException
     {
         String tipoOperacion=request.getParameter("tipoOperacion");
         int idPrestamo=Integer.parseInt(request.getParameter("idPrestamo"));
         Operacion operacion=new Operacion();
         operacion.setMetodo("EFECTIVO");         
         operacion.setTipo(tipoOperacion);       
         operacion.setIdPrestamo(idPrestamo);
         String msg=null;
         switch(tipoOperacion)
         {
             case Operacion.PAGAR_CUOTA:
                 try {
            OperacionDao.registrarOperacionPrestamo(usuario,idPrestamo,operacion );
            msg ="Se realizo la operacion";
        } catch (DaoException ex) {
            Logger.getLogger(ServletPrestamos.class.getName()).log(Level.SEVERE, null, ex);
            msg="Error "+ex.getMessage();
        }
             break;
             case Operacion.PAGAR_CUOTA_EXTRAORDINARIA:
                 request.setAttribute("idPrestamo", idPrestamo);
                 request.setAttribute("tipoOperacion", tipoOperacion);
                 
             
         }
         
         request.setAttribute("msgOperacion", msg);
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
