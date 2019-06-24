package controladores;

import acciones.PeliculaAccion;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
@MultipartConfig
@WebServlet(name="PeliculasControlador", urlPatterns={"/peliculas/*"})
public class PeliculasControlador extends HttpServlet {
   
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        // getting accion depending request infoPath
        PeliculaAccion peliculaAccion = PeliculaAccion.getAccion(request);   
        
        // executing accion assigned from infoPath
        RequestDispatcher despachador = peliculaAccion.ejecutar();
        
        despachador.forward(request, response);
    } 
    
}
