package controladores;

import dao.IEmpleadoDAO;
import dao.fabrica.DAOFabrica;
import entidades.Empleado;
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
@WebServlet(name="ActualizarEmpleadoControlador", urlPatterns={"/actualizarEmpleado"})
public class ActualizarEmpleadoControlador extends HttpServlet {
   
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
        
        DAOFabrica subFabrica = DAOFabrica.getDAOFabrica(DAOFabrica.MYSQL);
        IEmpleadoDAO dao = subFabrica.getEmpleadoDAO();         
        
        request.setAttribute("empleado", dao.getById(id));
                
        request.getRequestDispatcher("/WEB-INF/pages/actualizarEmpleado.jsp").forward(request, response);      
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
        String login = request.getParameter("login");
        
        int id;        
        Empleado empleado = new Empleado();      

        try {
            id = Integer.parseInt(request.getParameter("id"));          
            
            empleado.setId(id);
            empleado.setNombre(nombre);
            empleado.setApellido(apellido);
            empleado.setLogin(login); 
            

            DAOFabrica subFabrica = DAOFabrica.getDAOFabrica(DAOFabrica.MYSQL);
            IEmpleadoDAO dao = subFabrica.getEmpleadoDAO();

            if (dao.actualizar(empleado) != -1) {
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
