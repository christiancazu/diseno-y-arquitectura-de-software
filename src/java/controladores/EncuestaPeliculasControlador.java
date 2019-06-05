package controladores;

import dao.IEncuestaDAO;
import dao.IPeliculaDAO;
import dao.fabrica.DAOFabrica;
import entidades.Encuesta;
import entidades.Pelicula;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.GenerateFullPelicula;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
@WebServlet(name="EncuestaPeliculasControlador", urlPatterns={"/encuestas"})
public class EncuestaPeliculasControlador extends HttpServlet {
   
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        DAOFabrica subFabrica = DAOFabrica.getDAOFabrica(DAOFabrica.MYSQL);
        IPeliculaDAO iPeliculaDAO = subFabrica.getPeliculaDAO();
        IEncuestaDAO iEncuestaDAO = subFabrica.getEncuestaDAO();

        try {
            List<Pelicula> peliculas = iPeliculaDAO.getAll();
            List<Encuesta> encuestas = iEncuestaDAO.getAll();

            // assign data: Pelicula, likes, dislikes for each Pelicula
            request.setAttribute("fullPeliculas", GenerateFullPelicula.assignFullDataToPeliculas(peliculas, encuestas));
        } catch (Exception ex) {
            Logger.getLogger(ListarPeliculasControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher("WEB-INF/pages/encuestas.jsp").forward(request, response);
    } 

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
        
        DAOFabrica subFabrica = DAOFabrica.getDAOFabrica(DAOFabrica.MYSQL);
        IPeliculaDAO iPeliculaDAO = subFabrica.getPeliculaDAO();
        IEncuestaDAO iEncuestaDAO = subFabrica.getEncuestaDAO();
        
        Enumeration enumeration = request.getParameterNames();
        
        Map<String, Object> modelMap = new HashMap<>();
        while(enumeration.hasMoreElements()){
            String parameterName = (String) enumeration.nextElement();
            modelMap.put(parameterName, request.getParameter(parameterName));
            
            Encuesta encuesta = new Encuesta();
            encuesta.setPelicula(new Pelicula(Integer.parseInt(parameterName)));
            encuesta.setVoto(request.getParameter(parameterName).charAt(0));

            try {
                request.setAttribute("success", iEncuestaDAO.crear(encuesta));                
            } catch (Exception ex) {
                request.setAttribute("success", false);
                Logger.getLogger(EncuestaPeliculasControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            // System.out.println("parameter: " + parameterName + " val: " + request.getParameter(parameterName));
        }

        try {
            List<Pelicula> peliculas = iPeliculaDAO.getAll();
            List<Encuesta> encuestas = iEncuestaDAO.getAll();

            // assign data: Pelicula, likes, dislikes for each Pelicula
            request.setAttribute("fullPeliculas", GenerateFullPelicula.assignFullDataToPeliculas(peliculas, encuestas));
        } catch (Exception ex) {
            Logger.getLogger(ListarPeliculasControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("WEB-INF/pages/encuestas.jsp").forward(request, response);    
    }
}
