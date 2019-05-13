/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.AlumnoDAO;
import entidades.Alumno;
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
@WebServlet(name = "RegistrarAlumno", urlPatterns = {"/registrarAlumno"})
public class RegistrarAlumno extends HttpServlet {

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
        
        request.getRequestDispatcher("/pages/registrarAlumno.jsp").forward(request, response);
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
        
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        int edad = 0;
        try {
            edad = Integer.parseInt(request.getParameter("edad"));
            
            Alumno alumno = new Alumno(nombre, apellido, edad);

            AlumnoDAO alumnoDAO = new AlumnoDAO();

            request.setAttribute("success", alumnoDAO.store(alumno));
        } catch (Exception e) {
            request.setAttribute("success", false);
        }        
        
        request.getRequestDispatcher("/pages/registrarAlumno.jsp").forward(request, response);
    }

}
