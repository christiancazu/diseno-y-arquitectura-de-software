package controladores;

import acciones.EstadisticaAccion;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
@WebServlet(name="EstadisticasControlador", urlPatterns={"/estadisticas/*"})
public class EstadisticasControlador extends HttpServlet {
   
    /** 
     * Handles the HTTP all methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        // getting accion depending request infoPath
        EstadisticaAccion estadisticaAccion = EstadisticaAccion.getAccion(request);
        
        // executing accion assigned from infoPath
        RequestDispatcher despachador = estadisticaAccion.ejecutar();
        
        despachador.forward(request, response);
    } 
}
