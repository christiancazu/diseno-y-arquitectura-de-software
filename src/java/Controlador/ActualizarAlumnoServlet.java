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
import javax.xml.bind.ParseConversionEvent;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
@WebServlet(name = "ActualizarAlumnoServlet", urlPatterns = {"/actualizarAlumno"})
public class ActualizarAlumnoServlet extends HttpServlet {
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
        
        int id = Integer.parseInt(request.getParameter("id"));

        Alumno alumno = new Alumno();
        
        alumno = modelAlumno.buscarAlumno(id);
        
        request.setAttribute("alumno", alumno);

        request.getRequestDispatcher("/actualizarAlumno.jsp").forward(request, response);
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
        int id, edad;
        try {
            id = Integer.parseInt(request.getParameter("id")) | 0; 
            edad = Integer.parseInt(request.getParameter("txtEdad")) | 0; 
           
            Alumno alumno = new Alumno();
        
            alumno.setIdAlumno(id);
            alumno.setNombre(nombre);
            alumno.setApellido(apellido);
            alumno.setEdad(edad);

            ModelAlumno modelAlumno = new ModelAlumno();
            int resultado = modelAlumno.actualizarAlumno(alumno);
            
            request.setAttribute("alumno", alumno);
            
            if (resultado == 1) {
                alumno = modelAlumno.buscarAlumno(alumno.getIdAlumno());    
            } else {
                throw new Exception();
            }

            request.setAttribute("mensaje", "El alumno ha sido actualizado satisfactoriamente.");
        } catch (Exception e) {
            request.setAttribute("mensaje", "El alumno no ha podido ser actualizado.");
        }
        
        request.getRequestDispatcher("/actualizarAlumno.jsp").forward(request, response);
    }

}
