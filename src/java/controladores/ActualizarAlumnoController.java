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
@WebServlet(name = "ActualizarAlumnoController", urlPatterns = {"/actualizarAlumno"})
public class ActualizarAlumnoController extends HttpServlet {

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
        
        int id = Integer.parseInt(request.getParameter("id"));

        AlumnoDAO alumnoDAO = new AlumnoDAO();        
        
        Alumno alumno = alumnoDAO.getById(id);

        request.setAttribute("alumno", alumno);

        request.getRequestDispatcher("/pages/actualizarAlumno.jsp").forward(request, response);
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
        
        int id, edad;
        Alumno alumno = new Alumno();
        
        try {
            id = Integer.parseInt(request.getParameter("id")) | 0;
            edad = Integer.parseInt(request.getParameter("edad")) | 0;
                        
            alumno.setNombre(nombre);
            alumno.setApellido(apellido);
            alumno.setEdad(edad);

            AlumnoDAO alumnoDAO = new AlumnoDAO();
            
            Alumno alumnoActualizado = alumnoDAO.update(id, alumno); 
            
            if (alumnoActualizado != null) {
                request.setAttribute("success", true);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            request.setAttribute("success", false);
        }
        
        doGet(request, response);
    }

}
