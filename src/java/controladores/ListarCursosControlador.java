/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.DAOFabrica;
import dao.ICursoDAO;
import entidades.Curso;
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
@WebServlet(name = "CursosControlador", urlPatterns = {"/cursos"})
public class ListarCursosControlador extends HttpServlet {

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

        DAOFabrica subFabrica = DAOFabrica.getDAOFabrica(DAOFabrica.MYSQL);
        ICursoDAO dao = subFabrica.getCursoDAO();

        List<Curso> cursos = dao.listarCursos();
        request.setAttribute("cursos", cursos);
        request.getRequestDispatcher("/pages/cursos.jsp").forward(request, response);
    } 
}
