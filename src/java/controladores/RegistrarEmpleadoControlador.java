package controladores;

import dao.IEmpleadoDAO;
import dao.fabrica.DAOFabrica;
import entidades.Empleado;
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
@WebServlet(name="RegistrarEmpleadoControlador", urlPatterns={"/registrarEmpleado"})
public class RegistrarEmpleadoControlador extends HttpServlet {
   
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

        request.getRequestDispatcher("WEB-INF/pages/registrarEmpleado.jsp").forward(request, response);
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
        
        try {
            Empleado empleado = new Empleado();
            empleado.setNombre(nombre);
            empleado.setApellido(apellido);

            DAOFabrica subFabrica = DAOFabrica.getDAOFabrica(DAOFabrica.MYSQL);
            IEmpleadoDAO dao = subFabrica.getEmpleadoDAO();

            request.setAttribute("success", dao.crear(empleado) != -1);
        } catch (Exception e) {
            request.setAttribute("success", false);
        } 

        request.getRequestDispatcher("WEB-INF/pages/registrarEmpleado.jsp").forward(request, response);
    }
}
