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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
@WebServlet(name = "ListaAlumnoServlet", urlPatterns = {"/alumnos"})
public class ListaAlumnoServlet extends HttpServlet {

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
        
        // 1 Se obtiene una arraylist con los alumnos de la base de datos
        ModelAlumno modelAlumno = new ModelAlumno();
        List<Alumno> alumnos = modelAlumno.listaAlumno();

        // 2 Se guarda el arraylist en request con el alias alumnos
        // El request es una memoria temporal
        request.setAttribute("alumnos", alumnos);

        // 3 Se reenvia el request al formulario 
        request.getRequestDispatcher("/listaAlumno.jsp").forward(request, response);
    }

}
