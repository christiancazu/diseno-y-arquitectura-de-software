package controladores;

import dao.IEmpleadoDAO;
import dao.fabrica.DAOFabrica;
import entidades.Empleado;
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
@WebServlet(name="ListarEmpleadosControlador", urlPatterns={"/empleados"})
public class ListarEmpleadosControlador extends HttpServlet {
   
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
        IEmpleadoDAO dao = subFabrica.getEmpleadoDAO();

        List<Empleado> empleados = dao.getAll();
        request.setAttribute("empleados", empleados);
        
        request.getRequestDispatcher("/WEB-INF/pages/empleados.jsp").forward(request, response);
    }
}
