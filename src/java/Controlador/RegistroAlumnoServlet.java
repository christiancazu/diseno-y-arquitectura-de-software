/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Entidad.Alumno;
import Modelo.ModelAlumno;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
@WebServlet(name = "RegistroAlumnoServlet", urlPatterns = {"/registroAlumno"})
public class RegistroAlumnoServlet extends HttpServlet {   
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
        request.getRequestDispatcher("/registroAlumno.jsp").forward(request, response);
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
        String nombre = request.getParameter("txtNombres");
        String apellido = request.getParameter("txtApellidos");
        int edad = 0;
        try {
            edad = Integer.parseInt(request.getParameter("txtEdad")); 
           
            Alumno alumno = new Alumno();
        
            alumno.setNombre(nombre);
            alumno.setApellido(apellido);
            alumno.setEdad(edad);

            ModelAlumno modelAlumno = new ModelAlumno();
            modelAlumno.insertaAlumno(alumno);

            request.setAttribute("mensaje", "El alumno ha sido registrado satisfactoriamente.");
        } catch (Exception e) {
            request.setAttribute("mensaje", "El alumno no ha podido ser registrado.");
        }
        
        request.getRequestDispatcher("/registroAlumno.jsp").forward(request, response);
    }

}
