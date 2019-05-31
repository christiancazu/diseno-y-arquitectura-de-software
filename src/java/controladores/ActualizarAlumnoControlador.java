package controladores;

import dao.IAlumnoDAO;
import dao.fabrica.DAOFabrica;
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
@WebServlet(name="ActualizarAlumnoControlador", urlPatterns={"/actualizarAlumno"})
public class ActualizarAlumnoControlador extends HttpServlet {
   
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
        IAlumnoDAO dao = subFabrica.getAlumnoDAO();         
        
        request.setAttribute("alumno", dao.getById(id));
                
        request.getRequestDispatcher("/WEB-INF/pages/actualizarAlumno.jsp").forward(request, response);      
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
        int edad = Integer.parseInt(request.getParameter("edad"));
        
        int id;        
        Alumno alumno = new Alumno();      

        try {
            id = Integer.parseInt(request.getParameter("id"));          
            
            alumno.setId(id);
            alumno.setNombre(nombre);
            alumno.setApellido(apellido);
            alumno.setEdad(edad);             

            DAOFabrica subFabrica = DAOFabrica.getDAOFabrica(DAOFabrica.MYSQL);
            IAlumnoDAO dao = subFabrica.getAlumnoDAO();

            if (dao.actualizar(alumno) != -1) {
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
