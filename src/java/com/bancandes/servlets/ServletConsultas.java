/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancandes.servlets;

import com.bancandes.dao.CuentaDao;
import com.bancandes.dao.DaoException;
import com.bancandes.dao.FilaEnConsulta;
import com.bancandes.dao.UsuarioDao;
import com.bancandes.mb.Cuenta;
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
public class ServletConsultas extends HttpServlet {

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
            String consulta = request.getParameter("consulta");
            if (consulta != null) {
                mostrarConsulta(request, response, null, consulta);
            }

            request.getRequestDispatcher("menus/consultas.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void mostrarConsulta(HttpServletRequest request, HttpServletResponse response, Usuario usuario, String consulta) {
        ArrayList<FilaEnConsulta> resultado = null;

        try {
            switch (consulta) {
                case "CONSULTA_CUENTAS": 

                    resultado = CuentaDao.busquedaInfoCuentas(null, null);
                    break;
                case "CONSULTA_CLIENTE":
                    resultado= UsuarioDao.consultaUsuariosBancandes();
                break;
                case "CONSULTA__MAS ACTIVOS":
                    resultado= UsuarioDao.consultarUsariosMasActivos();
                    break;
            }

        } catch (DaoException ex) {
            Logger.getLogger(ServletConsultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("listaBusqueda", resultado);
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
