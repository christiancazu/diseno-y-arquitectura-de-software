/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.AlumnoDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
@WebServlet(name = "EliminarAlumnoController", urlPatterns = {"/eliminarAlumno"})
public class EliminarAlumnoController extends HttpServlet {

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

        String text = request.getParameter("text");
        String filtro = request.getParameter("filtro");
        int id = Integer.parseInt(request.getParameter("id"));

        AlumnoDAO alumnoDAO = new AlumnoDAO();

        alumnoDAO.delete(id);     
        
        response.sendRedirect("alumnos?filtro=" + filtro + "&text=" + text);
    }

}
