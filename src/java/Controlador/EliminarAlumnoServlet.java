/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Entidad.Alumno;
import Modelo.ModelAlumno;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
@WebServlet(name = "EliminarAlumnoServlet", urlPatterns = {"/eliminarAlumno"})
public class EliminarAlumnoServlet extends HttpServlet {
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
        int id = Integer.parseInt(request.getParameter("id"));
        
        ModelAlumno modelAlumno = new ModelAlumno();
        List<Alumno> alumnos = modelAlumno.listaAlumno();
                
        try {        
            modelAlumno.eliminarAlumno(id);
        } catch (Exception ex) {
            Logger.getLogger(EliminarAlumnoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        alumnos = modelAlumno.listaAlumno();
        
        request.setAttribute("alumnos", alumnos);
        
        request.getRequestDispatcher("/listaAlumno.jsp").forward(request, response);
    }

}
