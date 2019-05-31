package controladores;

import dao.IEmpleadoDAO;
import dao.fabrica.DAOFabrica;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
@WebServlet(name="EliminarEmpleadoControlador", urlPatterns={"/eliminarEmpleado"})
public class EliminarEmpleadoControlador extends HttpServlet {
   
    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        
        DAOFabrica subFabrica = DAOFabrica.getDAOFabrica(DAOFabrica.MYSQL);
        IEmpleadoDAO dao = subFabrica.getEmpleadoDAO();

        dao.eliminar(id);
        
        response.sendRedirect("empleados");
    }
}
