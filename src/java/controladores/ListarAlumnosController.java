/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.AlumnoDAO;
import entidades.Alumno;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "ListarAlumnoController", urlPatterns = {"/alumnos"})
public class ListarAlumnosController extends HttpServlet {

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

        String text = request.getParameter("text");
        String filtro = request.getParameter("filtro");

        if(filtro == null){
            request.setAttribute("text", "");
            request.getRequestDispatcher("/pages/alumnos.jsp").forward(request, response);
        }
        
        AlumnoDAO alumnoDAO = new AlumnoDAO();        
        List<Alumno> alumnos = null;
        
        if (filtro.equals("todos")) {
            alumnos = alumnoDAO.getAll();
            text = "";
        } else {
            alumnos = alumnoDAO.getAllByNameSurname(text, filtro);            
        }
        request.setAttribute("alumnos", alumnos);
        
        if (alumnos != null && !alumnos.isEmpty()) {
            request.setAttribute("result", true);            
        } else {
            request.setAttribute("result", false);
        }

        request.setAttribute("text", text);
        request.setAttribute("filtro", filtro);

        request.getRequestDispatcher("/pages/alumnos.jsp").forward(request, response);
    }

}
