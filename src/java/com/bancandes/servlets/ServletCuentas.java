/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancandes.servlets;

import com.bancandes.dao.CuentaDao;
import com.bancandes.dao.DaoException;
import com.bancandes.dao.OperacionDao;
import com.bancandes.dao.UsuarioDao;
import com.bancandes.mb.Cuenta;
import com.bancandes.mb.Operacion;
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
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogeado");
            String pagina = request.getParameter("paginaCuenta");
            //paginaParaElResultado
            int paginaDeseada = 1;
            if (pagina != null) {

                paginaDeseada = Integer.parseInt(pagina);
            }
            
            String operacion=request.getParameter("tipoAccion");
            String confirmarOperacion = request.getParameter("confirmarOperacion");
            if(operacion!=null)
            {
            realizarOperacion(request, response, usuario);
            }else if(confirmarOperacion!=null)
            {
            confirmarOperacion(request, response, usuario);
            }else
            {
            mostrarListaCuentas(request, response, usuario);
            }

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

    private void confirmarOperacion(HttpServletRequest request, HttpServletResponse response, Usuario usuario) throws ServletException, IOException {

        String confirmarOperacion = request.getParameter("confirmarOperacion");
        
        String id = request.getParameter("idCuenta");
        int idCuenta = Integer.parseInt(id);
        double monto = Double.parseDouble(request.getParameter("monto"));
        String metodo = request.getParameter("metodo");

        Operacion op = new Operacion();

        
        op.setMonto(Double.parseDouble(request.getParameter("monto")));
        op.setMetodo("EFECTIVO");    
        op.setIdCuenta(idCuenta);
        op.setTipo(confirmarOperacion);
        switch(confirmarOperacion)
        {
            case Operacion.CONSIGNAR:
                op.setDestino(idCuenta);
                break;
            case Operacion.RETIRAR:
                op.setOrigen(idCuenta);
                break;
                
        }
            
        
        
        String msgOperacion = "Operacion exitosa";
        try {
            OperacionDao.registrarOperacionCuenta(usuario, op);
        } catch (DaoException ex) {
            Logger.getLogger(ServletCuentas.class.getName()).log(Level.SEVERE, null, ex);
            msgOperacion="error "+ex.getMessage();
        }
        request.setAttribute("msgOperacion",msgOperacion);
        request.getRequestDispatcher("menus/cuentas.jsp").forward(request, response);
    }

    private void realizarOperacion(HttpServletRequest request, HttpServletResponse response, Usuario usuario) throws ServletException, IOException {
        String operacion = request.getParameter("tipoAccion");        
        String id = request.getParameter("idCuenta");
        int idCuenta = Integer.parseInt(id);
        String mensaje = null;

        Operacion opera = new Operacion();
        opera.setTipo(operacion);
        opera.setIdCuenta(idCuenta);

        if (mensaje == null) {
            switch (operacion) {

                case Operacion.CERRAR:
                    mensaje = "LA CUENTA FUE CERRADA";
                     {
                        try {
                            OperacionDao.registrarOperacionCuenta(usuario, opera);
                        } catch (DaoException ex) {
                            Logger.getLogger(ServletCuentas.class.getName()).log(Level.SEVERE, null, ex);
                            mensaje = "ERROR AL CERRAR";
                        }
                    }
                    request.setAttribute("msgOperacion", mensaje);
                    request.setAttribute("idCuenta", idCuenta);
                    break;
                case Operacion.ABRIR:
                    mensaje = "LA CUENTA FUE ABIERTA";
                     {
                        try {
                            OperacionDao.registrarOperacionCuenta(usuario, opera);
                        } catch (DaoException ex) {
                            Logger.getLogger(ServletCuentas.class.getName()).log(Level.SEVERE, null, ex);
                            mensaje = "ERROR AL ABRIR";
                        }
                    }
                    request.setAttribute("msgOperacion", mensaje);
                    request.setAttribute("idCuenta", idCuenta);
                    break;
                case Operacion.RETIRAR:
                    request.setAttribute("tipoOperacion", Operacion.RETIRAR);
                    request.setAttribute("idCuenta", id);
                    request.getRequestDispatcher("menus/cuentas.jsp").forward(request, response);
                    break;
                case Operacion.CONSIGNAR:
                    request.setAttribute("tipoOperacion", Operacion.CONSIGNAR);
                    request.setAttribute("idCuenta", id);
                    request.getRequestDispatcher("menus/cuentas.jsp").forward(request, response);
                    break;
            }
        }

    }

    private void mostrarListaCuentas(HttpServletRequest request, HttpServletResponse response, Usuario usuario) throws ServletException, IOException {

        
        ArrayList<Cuenta> listaCuentas = null;
        switch (usuario.getRol()) {
            case Usuario.GERENTE_OFICINA:
                listaCuentas = CuentaDao.getCuentasbyCreador(usuario.getId());
                break;
            case Usuario.CLIENTE:
                listaCuentas = CuentaDao.getCuentasByPropietario(usuario.getId());
                break;
            case Usuario.GERENTE_GENERAL:
            case Usuario.ADMINISTRADOR:
                listaCuentas = CuentaDao.getAllCuentas(20, 0);
            case Usuario.CAJERO:
                if(request.getParameter("buscarCuentas")!=null)
                {
                   String correo=request.getParameter("correoPropietario");
                    
                    Usuario usuarioCuentas=UsuarioDao.findUsuarioByCorreo(correo, null);                           
                    listaCuentas = CuentaDao.getCuentasByPropietario(usuarioCuentas.getId());
                    if(listaCuentas==null || listaCuentas.isEmpty() ) 
                    {
                        request.setAttribute("msgOperacion", "No se encontraron cuentas para "+correo);
                    }
                    //guarda el correo para mostrar las cuentas luego
                    
                    request.setAttribute("correoPropietario", correo);
                    
                }
                break;
        }

        request.setAttribute("listaCuentas", listaCuentas);
        request.getRequestDispatcher("menus/cuentas.jsp").forward(request, response);
    }

    private ArrayList<Cuenta> TestListaCuentas() {
        ArrayList<Cuenta> listaCuentas = new ArrayList<>();
        Cuenta cuenta = new Cuenta();
        cuenta.setFechaCreacion(null);
        cuenta.setTipo("LOL");
        listaCuentas.add(cuenta);
        return listaCuentas;

    }

}
